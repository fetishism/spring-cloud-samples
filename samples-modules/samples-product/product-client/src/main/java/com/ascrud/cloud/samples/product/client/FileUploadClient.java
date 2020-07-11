package com.ascrud.cloud.samples.product.client;

import feign.codec.Encoder;
import feign.form.spring.SpringFormEncoder;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.support.SpringEncoder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author walkman
 */
@FeignClient(value = "product",
        fallback = FileUploadClient.FileUploadClientFallback.class,
        configuration = FileUploadClient.MultipartSupportConfig.class)
public interface FileUploadClient {

    /***
     * 1.produces,consumes必填
     * 2.注意区分@RequestPart和RequestParam，不要将
     * //@RequestPart(value = "file") 写成@RequestParam(value = "file")
     * @param file file
     * @return 文件路径
     */
    @PostMapping(value = "/uploadFile/server",
            produces = {MediaType.APPLICATION_JSON_UTF8_VALUE},
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    String fileUpload(@RequestPart(value = "file") MultipartFile file);

    @Component
    static class FileUploadClientFallback implements FileUploadClient {

        @Override
        public String fileUpload(MultipartFile file) {
            return null;
        }
    }

    /**
     * See @link <a href="https://github.com/OpenFeign/feign-form"></a>
     */
    @Component
    static class MultipartSupportConfig {

        @Autowired
        private ObjectFactory<HttpMessageConverters> messageConverters;

        @Bean
        public Encoder feignFormEncoder() {
            return new SpringFormEncoder(new SpringEncoder(messageConverters));
        }

//        @Bean
//        public Encoder feignFormEncoder () {
//            return new SpringFormEncoder();
//        }

//        @Bean
//        public Decoder feignDecoder () {
//            List<HttpMessageConverter<?>> springConverters =
//                    messageConverters.getObject().getConverters();
//
//            List<HttpMessageConverter<?>> decoderConverters =
//                    new ArrayList<HttpMessageConverter<?>>(springConverters.size() + 1);
//
//            decoderConverters.addAll(springConverters);
//            decoderConverters.add(new SpringManyMultipartFilesReader(4096));
//
//            HttpMessageConverters httpMessageConverters = new HttpMessageConverters(decoderConverters);
//
//            return new SpringDecoder(() -> httpMessageConverters);
//        }
    }

}
