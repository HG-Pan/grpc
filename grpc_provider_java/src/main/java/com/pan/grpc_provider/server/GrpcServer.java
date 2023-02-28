package com.pan.grpc_provider.server;

import com.pan.grpc.HelloResponse;
import com.pan.grpc.HelloServiceGrpc;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.stub.StreamObserver;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;


/**
 * @author pan
 * @date 2/27/23
 */
@Slf4j
public class GrpcServer {

    public static void start() throws IOException, InterruptedException {
        Server server = ServerBuilder.forPort(9191).addService( new HelloServiceImpl()).build().start();

        log.info("Server started, listening on " + server.getPort());

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            log.info("Shutting down gRPC server since JVM is shutting down");
            server.shutdown();
            log.info("Server shut down");
        }));
        server.awaitTermination();
    }


    static class HelloServiceImpl extends HelloServiceGrpc.HelloServiceImplBase {
        @Override
        public void sayHello(com.pan.grpc.HelloRequest request, StreamObserver<HelloResponse> responseObserver) {
            String message = "Hello, " + request.getName() + "!";
            System.out.println(message);
            com.pan.grpc.HelloResponse response = com.pan.grpc.HelloResponse.newBuilder().setMessage(message).build();
            responseObserver.onNext(response);
            responseObserver.onCompleted();
        }
    }
}
