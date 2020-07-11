package com.ascrud.cloud.samples.mdm.client;

import com.ascrud.cloud.samples.mdm.common.entity.PermissionOutput;
import com.ascrud.cloud.samples.core.vo.ResultVO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * 此接口供其他服务调用
 *
 * @author walkman
 */
@FeignClient(name = "mdm", fallback = MdmClient.MdmClientFallback.class)
public interface MdmClient {

    /**
     * 根据用户名查询用户所有权限
     *
     * @return
     */
    @GetMapping("/mdm/{id}/permissions")
    @ResponseBody
    ResultVO<List<PermissionOutput>> getUserPermissionByUserId(@PathVariable("id") String id);

    @Component
    static class MdmClientFallback implements MdmClient {

        /**
         * 根据用户名查询用户所有权限
         *
         * @param id
         * @return
         */
        @Override
        public ResultVO<List<PermissionOutput>> getUserPermissionByUserId(String id) {
            return null;
        }
    }
}
