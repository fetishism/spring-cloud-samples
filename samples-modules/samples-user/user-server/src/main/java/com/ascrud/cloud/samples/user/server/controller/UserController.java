package com.ascrud.cloud.samples.user.server.controller;

import com.ascrud.cloud.samples.core.annotation.Idempotent;
import com.ascrud.cloud.samples.core.annotation.IdempotentParam;
import com.ascrud.cloud.samples.user.server.assembler.UserOutputResourceAssembler;
import com.ascrud.cloud.samples.core.common.ValidList;
import com.ascrud.cloud.samples.core.enums.ResultEnum;
import com.ascrud.cloud.samples.core.utils.JwtUtil;
import com.ascrud.cloud.samples.core.utils.ResultVOUtils;
import com.ascrud.cloud.samples.core.vo.ResultVO;
import com.ascrud.cloud.samples.user.common.entity.PermissionOutput;
import com.ascrud.cloud.samples.user.common.entity.UserInput;
import com.ascrud.cloud.samples.user.common.entity.UserOutput;
import com.ascrud.cloud.samples.user.common.entity.UserPermissionInput;
import com.ascrud.cloud.samples.user.common.entity.UserPermissionOutput;
import com.ascrud.cloud.samples.user.server.entity.Permission;
import com.ascrud.cloud.samples.user.server.entity.RolePermission;
import com.ascrud.cloud.samples.user.server.entity.UserPermission;
import com.ascrud.cloud.samples.user.server.service.PermissionService;
import com.ascrud.cloud.samples.user.server.service.RolePermissionService;
import com.ascrud.cloud.samples.user.server.service.UserPermissionService;
import com.ascrud.cloud.samples.user.server.service.impl.UserIdempotentImpl;
import com.ascrud.cloud.samples.user.server.utils.BeanCreators;
import com.ascrud.cloud.samples.user.server.entity.User;
import com.ascrud.cloud.samples.user.server.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.ListUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

import static com.ascrud.cloud.samples.core.constant.Constants.DELETED_STATUS;
import static com.ascrud.cloud.samples.core.enums.ResultEnum.PARAM_ERROR;
import static com.ascrud.cloud.samples.core.enums.ResultEnum.USER_EXISTED;
import static com.ascrud.cloud.samples.core.enums.ResultEnum.USER_NOT_EXIST;

/**
 *
 *
 * @author walkman
 */
@RestController
@RequestMapping("/users")
@Slf4j
public class UserController {

    @Autowired
    private UserOutputResourceAssembler assembler;

    @Autowired
    private UserService userService;

    @Autowired
    private UserPermissionService userPermissionService;

    @Autowired
    private RolePermissionService rolePermissionService;

    @Autowired
    private PermissionService permissionService;

    /**
     * 查询所有用户
     *
     * @return
     */
    @GetMapping
    public ResultVO getUserList() {
        List<User> users = userService.findUserList();
        List<UserOutput> outputList = users.stream().map(u -> {
            UserOutput output = BeanCreators.createUserOutput();
            output.setUserId(u.getUserId());
            output.setUsername(u.getUsername());
            output.setUserDesc(u.getUserDesc());
            return output;
        }).collect(Collectors.toList());
        return ResultVOUtils.success(outputList);
    }

    /**
     * 根据用户名查询用户所有角色
     *
     * @param id
     * @return
     */
    @GetMapping("/{id:\\w{8}(?:-\\w{4}){3}-\\w{12}}/roles")
    @Idempotent
    public ResultVO getUserRoleByUserId(@PathVariable("id") String id) {
        List<UserPermission> userPermissionList = userPermissionService.findAllByUserId(id);
        if (CollectionUtils.isEmpty(userPermissionList)) {
            String msg = "用户权限为空";
            log.warn("UserController: getUserRoleByUserId(id={}, msg={})", id, msg);
            return ResultVOUtils.success(msg);
        }

        List<UserPermissionOutput> userPermissionOutputs = userPermissionList.stream().map(userPermission -> {
            UserPermissionOutput output = BeanCreators.createUserPermissionOutput();
            BeanUtils.copyProperties(userPermission, output);
            return output;
        }).collect(Collectors.toList());

        return ResultVOUtils.success(userPermissionOutputs);
    }

    /**
     * 更新用户下所有权限
     *
     * @param id
     * @return
     */
    @PostMapping("/{id:\\w{8}(?:-\\w{4}){3}-\\w{12}}/roles")
    public ResultVO updateUserPermission(@PathVariable("id") String id,
                                         @Valid @RequestBody ValidList<UserPermissionInput> userPermissionInputList, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            Map<String, String> errorsMap = bindingResult.getFieldErrors().stream()
                    .collect(Collectors.toMap(FieldError::getField, FieldError::getDefaultMessage));
            return ResultVOUtils.error(ResultEnum.PARAM_ERROR, errorsMap);
        }

        if (CollectionUtils.isEmpty(userPermissionInputList)) {
            log.error("UserController: updateUserPermission(userPermissionInputList={})", userPermissionInputList);
            return ResultVOUtils.error(PARAM_ERROR);
        }

        List<UserPermission> deletedUserPermissionList = userPermissionService.findAllByUserId(id).stream()
                .map(userPermission -> {
                    userPermission.setDelFlag(DELETED_STATUS);
                    return userPermission;
                }).collect(Collectors.toList());
        userPermissionService.saveAll(deletedUserPermissionList);

        List<UserPermission> userPermissionList = userPermissionInputList.stream().map(userPermissionInput -> {
            UserPermission userPermission = BeanCreators.createInitUserPermission();
            BeanUtils.copyProperties(userPermissionInput, userPermission);
            userPermission.setUserPermissionId(UUID.randomUUID().toString());
            userPermission.setUserId(id);
            return userPermission;
        }).collect(Collectors.toList());

        userPermissionService.saveAll(userPermissionList);
        return ResultVOUtils.success();
    }

    /**
     * 根据用户名查询用户所有权限
     *
     * @param id
     * @return
     */
    @GetMapping("/{id:\\w{8}(?:-\\w{4}){3}-\\w{12}}/permissions")
    public ResultVO<List<PermissionOutput>> getUserPermissionByUserId(@PathVariable("id") String id) {
        List<UserPermission> userPermissionList = userPermissionService.findAllByUserId(id);
        if (CollectionUtils.isEmpty(userPermissionList)) {
            String msg = "用户权限为空";
            log.error("UserController: getUserPermissionByUserId(id={}, msg={})", id, msg);
            return ResultVOUtils.error(msg);
        }

        /**
         * 用户权限关系表中, 既可以存储用户与角色的对应关系, 也可以存储用户与权限的对应关系.
         * 最终用户的实际权限为两者的合集
         */
        // 1. 获取该用户下配置的角色id
        List<String> roleIds = userPermissionList.stream()
                .filter(userPermission -> StringUtils.isNotBlank(userPermission.getRoleId()))
                .map(UserPermission::getRoleId).collect(Collectors.toList());

        // 2. 获取该用户下配置的权限id
        List<String> permissionIds = userPermissionList.stream()
                .filter(userPermission -> StringUtils.isNotBlank(userPermission.getPermissionId()))
                .map(UserPermission::getPermissionId).collect(Collectors.toList());

        // 3. 根据该用户下配置的角色id, 获取对应的权限id
        List<RolePermission> rolePermissionList = rolePermissionService.findAllByRoleIds(roleIds);
        List<String> permissionIdByRoles = rolePermissionList.stream()
                .map(RolePermission::getPermissionId).collect(Collectors.toList());

        // 4. 将2和3进行合并, 查询用户的所有权限列表
        List<String> userPermissionIds = ListUtils.union(permissionIdByRoles, permissionIds);
        List<Permission> permissionList = permissionService.findAllByPermissionIds(userPermissionIds);
        List<PermissionOutput> permissionOutputList = permissionList.stream().map(permission -> {
            PermissionOutput permissionOutput = BeanCreators.createPermissionOutput();
            BeanUtils.copyProperties(permission, permissionOutput);
            return permissionOutput;
        }).collect(Collectors.toList());

        return ResultVOUtils.success(permissionOutputList);
    }

    /**
     * 根据用户名和密码查找用户, 返回Token
     *
     * @param username
     * @param password
     * @return
     */
    @PostMapping("/token")
    @Idempotent(fallback = UserIdempotentImpl.class, params = {
            @IdempotentParam(key = "", val = ""),
            @IdempotentParam(key = "", val = ""),
    })
    public ResultVO getUserTokenByUsername(@RequestParam("username") String username, @RequestParam("password") String password) {
        if (StringUtils.isBlank(username) || StringUtils.isBlank(password)) {
            log.error("UserController: getUserTokenByUsername(username={}, password={})", username, password);
            return ResultVOUtils.error(PARAM_ERROR);
        }

        // 数据库中密码已做加密处理
        // todo password需要加密处理后进行比对
        User user = userService.findUserByUsername(username);
        if (user == null || !password.equals(user.getPassword())) {
            log.error("UserController: getUserTokenByUsername(username={}, password={})", username, password);
            return ResultVOUtils.error(USER_NOT_EXIST);
        }
        UserOutput output = BeanCreators.createUserOutput();
        BeanUtils.copyProperties(user, output);
        return ResultVOUtils.success(JwtUtil.generateToken(output.getUserId(), output.getUsername()));
    }

    /**
     * 根据用户名和密码查找用户, 返回Token
     *
     * @param request
     * @return
     */
    @PostMapping("/token/refresh")
    public ResultVO refreshToken(HttpServletRequest request) {
        String userId = request.getHeader("request_attribute_user_id");
        User user = userService.findByUserId(userId);
        if (user == null) {
            log.error("UserController: refreshToken(id={})", userId);
            return ResultVOUtils.error(USER_NOT_EXIST);
        }
        UserOutput output = BeanCreators.createUserOutput();
        BeanUtils.copyProperties(user, output);
        return ResultVOUtils.success(JwtUtil.generateToken(output.getUserId(), output.getUsername()));
    }

    /**
     * 根据用户Id, 获取用户明细
     *
     * @param id
     * @return
     */
    @GetMapping("/{id:\\w{8}(?:-\\w{4}){3}-\\w{12}}")
    public ResultVO getUserById(@PathVariable("id") String id) {
        User user = userService.findByUserId(id);
        if (user == null) {
            log.error("UserController: getUserById(id={})", id);
            return ResultVOUtils.error(USER_NOT_EXIST);
        }
        UserOutput output = BeanCreators.createUserOutput();
        BeanUtils.copyProperties(user, output);
        return ResultVOUtils.success(assembler.toResource(output));
    }

    /**
     * 创建用户
     *
     * @param input
     * @return
     */
    @PostMapping
    public ResultVO createUser(@Validated @RequestBody UserInput input, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            Map<String, String> errorsMap = bindingResult.getFieldErrors().stream()
                    .collect(Collectors.toMap(FieldError::getField, FieldError::getDefaultMessage));
            return ResultVOUtils.error(ResultEnum.PARAM_ERROR, errorsMap);
        }
        User user = BeanCreators.createInitUser();
        BeanUtils.copyProperties(input, user);
        // todo 密码需要加密处理
        User existedUser = userService.findUserByUsername(user.getUsername());
        if (existedUser != null) {
            log.error("UserController: createUser(user={})", user);
            return ResultVOUtils.error(USER_EXISTED);
        }
        User resultUser = userService.createOrUpdateUser(user);
        UserOutput resultOutput = BeanCreators.createUserOutput();
        BeanUtils.copyProperties(resultUser, resultOutput);
        return ResultVOUtils.success(resultOutput);
    }

    /**
     * 根据用户Id, 删除用户
     *
     * @param id
     * @return
     */
    @DeleteMapping("/{id:\\w{8}(?:-\\w{4}){3}-\\w{12}}")
    public ResultVO deleteUserById(@PathVariable("id") String id) {
        User user = userService.findByUserId(id);
        if (user == null) {
            log.error("UserController: deleteUserById(id={})", id);
            return ResultVOUtils.error(USER_NOT_EXIST);
        }
        user.setDelFlag(DELETED_STATUS);
        User resultUser = userService.createOrUpdateUser(user);
        UserOutput resultOutput = BeanCreators.createUserOutput();
        BeanUtils.copyProperties(resultUser, resultOutput);
        return ResultVOUtils.success(resultOutput);
    }

}
