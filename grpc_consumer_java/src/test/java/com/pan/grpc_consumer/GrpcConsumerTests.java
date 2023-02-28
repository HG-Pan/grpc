package com.pan.grpc_consumer;

import com.pan.grpc_consumer.client.GreeterClient;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class GrpcConsumerTests {

    @Test
    void testSend() {
        GreeterClient.send();
    }

}
