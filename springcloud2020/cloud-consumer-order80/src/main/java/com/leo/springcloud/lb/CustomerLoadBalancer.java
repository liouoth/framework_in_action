package com.leo.springcloud.lb;

import com.netflix.loadbalancer.Server;
import org.springframework.cloud.client.ServiceInstance;

import java.util.List;

public interface CustomerLoadBalancer {
    public ServiceInstance choose( List<ServiceInstance> instances);
}
