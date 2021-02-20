package com.leo.springcloud.lb;

import com.netflix.loadbalancer.Server;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Component
public class CustomerLoadBalancerImpl implements CustomerLoadBalancer {
    private static AtomicInteger count = new AtomicInteger(0);

    @Override
    public ServiceInstance choose(List<ServiceInstance> instances) {
        System.out.println("服务数量："+instances.size());
       Integer index = getIndex(instances.size());
        System.out.println("获取到的index："+index);
       if (index==null){
           return null;
       }
       return instances.get(index);
    }

    private static Integer getIndex(int mode) {
        for (;;){
            Integer now = count.get();
            if (!count.compareAndSet(now,(now+1)>=Integer.MAX_VALUE?0:(now+1)%mode)) {
                now = count.get();
                continue;
            }
            return now;
        }
    }
}
