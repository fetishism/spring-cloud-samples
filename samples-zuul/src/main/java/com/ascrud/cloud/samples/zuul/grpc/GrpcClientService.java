package com.ascrud.cloud.samples.zuul.grpc;

import com.ascrud.cloud.samples.user.common.proto.GreeterGrpc;
import com.ascrud.cloud.samples.user.common.proto.HelloReply;
import com.ascrud.cloud.samples.user.common.proto.HelloRequest;
import io.grpc.StatusRuntimeException;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.stereotype.Service;

/**
 * @author walkman
 */
@Service
public class GrpcClientService {

    @GrpcClient("user")
    private GreeterGrpc.GreeterBlockingStub greeterStub;

    public String sendMessage(final String name) {
        try {
            final HelloReply response = this.greeterStub.sayHello(HelloRequest.newBuilder().setName(name).build());
            return response.getMessage();
        } catch (final StatusRuntimeException e) {
            return "FAILED with " + e.getStatus().getCode();
        }
    }
}
