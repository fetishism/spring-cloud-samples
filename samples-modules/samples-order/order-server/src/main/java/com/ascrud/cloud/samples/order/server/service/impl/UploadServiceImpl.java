package com.ascrud.cloud.samples.order.server.service.impl;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.ascrud.cloud.samples.product.client.FileUploadClient;
import com.ascrud.cloud.samples.core.exception.FallbackException;
import com.ascrud.cloud.samples.order.server.service.UploadService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author walkman
 */
@Service
@Slf4j
public class UploadServiceImpl implements UploadService {

    @Autowired
    private FileUploadClient fileUploadClient;

    @Override
    @HystrixCommand(fallbackMethod = "uploadFallback")
    public String upload(MultipartFile file) {
        String fileName = fileUploadClient.fileUpload(file);
        if (fileName == null) {
            throw new FallbackException();
        }
        return fileName;
    }

    public String uploadFallback(MultipartFile file, Throwable throwable) {
        log.error("[OrderController]: error = {}", throwable.getMessage());
        return null;
    }
}
