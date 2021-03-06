package com.ascrud.cloud.samples.user.server.controller;

import com.ascrud.cloud.samples.core.utils.ResultVOUtils;
import com.ascrud.cloud.samples.core.vo.ResultVO;
import com.ascrud.cloud.samples.user.common.entity.PermissionInput;
import com.ascrud.cloud.samples.user.common.entity.PermissionOutput;
import com.ascrud.cloud.samples.user.server.entity.Permission;
import com.ascrud.cloud.samples.user.server.service.PermissionService;
import com.ascrud.cloud.samples.user.server.utils.BeanCreators;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.ascrud.cloud.samples.core.constant.Constants.DELETED_STATUS;
import static com.ascrud.cloud.samples.core.enums.ResultEnum.DATA_EXISTED;
import static com.ascrud.cloud.samples.core.enums.ResultEnum.DATA_NOT_EXIST;
import static com.ascrud.cloud.samples.core.enums.ResultEnum.PARAM_ERROR;

/**
 *
 *
 * @author walkman
 */
@Slf4j
@RestController
@RequestMapping("/permissions")
public class PermissionController {

    @Autowired
    private PermissionService permissionService;

    /**
     * 查询所有权限
     *
     * @return
     */
    @GetMapping
    public ResultVO getPermissionList() {
        List<Permission> permissions = permissionService.findAll();
        List<PermissionOutput> outputList = permissions.stream().map(p -> {
            PermissionOutput output = BeanCreators.createPermissionOutput();
            BeanUtils.copyProperties(p, output);
            return output;
        }).collect(Collectors.toList());
        return ResultVOUtils.success(outputList);
    }

    /**
     * 根据权限Id, 获取权限详情
     *
     * @param id
     * @return
     */
    @GetMapping("/{id:\\w{8}(?:-\\w{4}){3}-\\w{12}}")
    public ResultVO getPermissionById(@PathVariable("id") String id) {
        Permission permission = permissionService.findByPermissionId(id);
        if (permission == null) {
            log.error("PermissionController: getPermissionById(id={})",id);
            return ResultVOUtils.error(DATA_NOT_EXIST);
        }
        PermissionOutput output = BeanCreators.createPermissionOutput();
        BeanUtils.copyProperties(permission, output);
        return ResultVOUtils.success(output);
    }

    /**
     * 创建权限
     *
     * @param input
     * @return
     */
    @PostMapping
    public ResultVO createPermission(@RequestBody @Validated PermissionInput input, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            Map<String, String> errorsMap = bindingResult.getFieldErrors().stream()
                    .collect(Collectors.toMap(FieldError::getField, FieldError::getDefaultMessage));
            return ResultVOUtils.error(PARAM_ERROR, errorsMap);
        }
        Permission permission = BeanCreators.createInitPermission();
        BeanUtils.copyProperties(input, permission);

        Permission existedPermission = permissionService.findByPermissionName(permission.getPermissionName());
        if (existedPermission != null) {
            log.error("PermissionController: createPermission(permission={})", permission);
            return ResultVOUtils.error(DATA_EXISTED);
        }
        Permission resultPermission = permissionService.saveOne(permission);
        PermissionOutput resultOutput = BeanCreators.createPermissionOutput();
        BeanUtils.copyProperties(resultPermission, resultOutput);
        return ResultVOUtils.success(resultOutput);
    }

    /**
     * 更新权限全部信息
     *
     * @param id 权限Id
     * @param input
     * @return
     */
    @PutMapping("/{id:\\w{8}(?:-\\w{4}){3}-\\w{12}}")
    public ResultVO updatePermission(@PathVariable String id,
                                     @RequestBody @Validated PermissionInput input, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            Map<String, String> errorsMap = bindingResult.getFieldErrors().stream()
                    .collect(Collectors.toMap(FieldError::getField, FieldError::getDefaultMessage));
            return ResultVOUtils.error(PARAM_ERROR, errorsMap);
        }

        Permission permission = permissionService.findByPermissionId(id);
        if (permission == null) {
            log.error("PermissionController: updatePermission(id={}, input={})", id, input);
            return ResultVOUtils.error(DATA_NOT_EXIST);
        }

        BeanUtils.copyProperties(input, permission);
        Permission resultPermission = permissionService.saveOne(permission);
        PermissionOutput permissionOutput = BeanCreators.createPermissionOutput();
        BeanUtils.copyProperties(resultPermission, permissionOutput);
        return ResultVOUtils.success(permissionOutput);
    }

    /**
     * 根据权限Id, 删除权限
     *
     * @param id
     * @return
     */
    @DeleteMapping("/{id:\\w{8}(?:-\\w{4}){3}-\\w{12}}")
    public ResultVO deletePermissionById(@PathVariable("id") String id) {
        Permission permission = permissionService.findByPermissionId(id);
        if (permission == null) {
            log.error("PermissionController: deletePermissionById(id={})",id);
            return ResultVOUtils.error(DATA_NOT_EXIST);
        }
        permission.setDelFlag(DELETED_STATUS);
        permissionService.saveOne(permission);
        return ResultVOUtils.success();
    }
}
