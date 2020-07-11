package com.ascrud.cloud.samples.order.server.service;

import org.springframework.web.multipart.MultipartFile;

/**
 *
 *
 * @author walkman
 */
public interface UploadService {

    String upload(MultipartFile file);

}
