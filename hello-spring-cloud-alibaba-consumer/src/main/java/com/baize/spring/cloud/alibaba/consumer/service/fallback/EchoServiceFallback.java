package com.baize.spring.cloud.alibaba.consumer.service.fallback;

import com.baize.spring.cloud.alibaba.consumer.service.EchoService;
import org.springframework.stereotype.Component;

@Component
public class EchoServiceFallback implements EchoService {

    @Override
    public String echo(String string) {
        return "echo fallback";
    }

    @Override
    public String lb() {
        return "lb fallback";
    }
}