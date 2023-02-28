package com.pan.grpc_consumer.client;
import com.pan.grpc.HelloResponse;
import com.pan.grpc.HelloServiceGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

import java.util.concurrent.TimeUnit;

/**
 * @author pan
 * @date 2/27/23
 */



public class GreeterClient {
    public static void send() {
           String name="aaaaa";
        ManagedChannel channel = null;
        try {
            channel = ManagedChannelBuilder.forAddress("localhost", 9191).usePlaintext().build();
            HelloServiceGrpc.HelloServiceBlockingStub helloServiceBlockingStub = HelloServiceGrpc.newBlockingStub(channel);
            com.pan.grpc.HelloRequest hello = com.pan.grpc.HelloRequest.newBuilder().setName(name).build();
            HelloResponse helloResponse = helloServiceBlockingStub.sayHello(hello);
            System.out.println(helloResponse);
        } finally {
            // 关闭 channel
            assert channel != null;
            channel.shutdown();
            try {
                channel.awaitTermination(5, TimeUnit.SECONDS);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
