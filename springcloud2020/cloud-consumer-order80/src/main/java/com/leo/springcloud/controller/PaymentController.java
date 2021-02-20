package com.leo.springcloud.controller;

import com.leo.springcloud.lb.CustomerLoadBalancerImpl;
import entities.CommonResult;
import entities.Payment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.List;

/**
 * 调用8001中的订单服务
 * restTemplate
 */
@RestController
@Slf4j
public class PaymentController {
    //这里需要进行负载均衡，是8001或者8002
//    private static final String provider_url = "http://localhost:8001";
    private static final String provider_url = "http://CLOUD-PAYMENT-SERVICE";
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private CustomerLoadBalancerImpl balancer;

    @Resource
    private DiscoveryClient discoveryClient;

    @PostMapping("/payment/create")
    public CommonResult create(Payment payment) {
        return restTemplate.postForObject(provider_url + "/payment/create"
                , payment, CommonResult.class);
    }

//    @GetMapping("/payment/get/{id}")
//    public CommonResult getPaymentById(@PathVariable("id") Long id) {
//        return restTemplate.getForObject(provider_url + "/payment/get/"+id,
//                  CommonResult.class);
//    }
    @GetMapping("/payment/get/{id}")
    public CommonResult getPaymentById(@PathVariable("id") Long id) {
        ResponseEntity<CommonResult> forEntity = restTemplate.getForEntity(provider_url + "/payment/get/" + id,
                CommonResult.class);
        return forEntity.getBody();
    }

    @GetMapping("/payment/random")
    public CommonResult getRandom() {
        List<ServiceInstance> instances = discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");
        ServiceInstance choose = balancer.choose(instances);
        if (choose==null){
            return new CommonResult("500","失败了！！！实例为空！！",null);
        }
        CommonResult commonResult = new CommonResult();
        commonResult.setCode("200");
        commonResult.setMsg("获取到的服务是，"+choose.getHost()+":"+choose.getPort());
        return commonResult;
    }



}
