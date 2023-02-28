package com.pan.grpc_provider.entrance;

import com.pan.grpc_provider.server.GrpcServer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.io.IOException;


/**
 * @author pan
 * @date 2023年01月10
 */
@Component
@Slf4j
public class Initialization implements ApplicationListener<ContextRefreshedEvent>, DisposableBean {
    /**
     * 实例化后执行任务
     * @param event
     */
    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        try {
            GrpcServer.start();
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 退出勾子
     */
    @Override
    public void destroy() {

    }
}
