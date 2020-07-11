package com.ascrud.cloud.samples.user.client.grpc;

import com.ascrud.cloud.samples.user.common.proto.GreeterGrpc;
import com.ascrud.cloud.samples.user.common.proto.HelloReply;
import com.ascrud.cloud.samples.user.common.proto.HelloRequest;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;

/**
 * @author walkman
 */
@GrpcService
public class GRpcUserServerService extends GreeterGrpc.GreeterImplBase {

    @Override
    public void sayHello(HelloRequest request, StreamObserver<HelloReply> responseObserver) {
        HelloReply reply = HelloReply.newBuilder().setMessage("Hello ==> " + request.getName()).build();
        responseObserver.onNext(reply);
        responseObserver.onCompleted();
    }
}
