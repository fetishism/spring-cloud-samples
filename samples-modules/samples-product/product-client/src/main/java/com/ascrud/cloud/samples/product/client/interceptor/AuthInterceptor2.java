package com.ascrud.cloud.samples.product.client.interceptor;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;

/**
 *
 *
 * @author walkman
 */
public class AuthInterceptor2 implements Interceptor {

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();

        //before , request.body()

        try {
            Response response = chain.proceed(request);
            //after
            return response;
        }catch (Exception e) {
            //log error
            throw e;
        } finally {
            //clean up
        }
    }
}
