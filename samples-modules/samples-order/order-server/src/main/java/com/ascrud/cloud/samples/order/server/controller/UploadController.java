package com.ascrud.cloud.samples.order.server.controller;

import com.ascrud.cloud.samples.core.utils.ResultVOUtils;
import com.ascrud.cloud.samples.core.vo.ResultVO;
import com.ascrud.cloud.samples.order.server.service.UploadService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 *
 * @author walkman
 */
@RestController
@RequestMapping("/files")
@Slf4j
public class UploadController {

    @Autowired
    private UploadService uploadService;

    @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @ApiOperation(value = "文件上传", notes = "请选择文件上传")
    public ResultVO imageUpload(@ApiParam(value="文件上传",required = true) MultipartFile file) {
        String path = uploadService.upload(file);
        if (path == null) {
            String msg = "文件上传失败";
            log.error("order: error=[msg:{}]", msg);
            return ResultVOUtils.error(msg);
        }
        return ResultVOUtils.success(path);
    }

}
