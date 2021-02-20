package com.leo.springcloud.controller;

import com.leo.springcloud.service.PaymentService;

import entities.CommonResult;
import entities.Payment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
public class PaymentController {
    @Autowired
    private PaymentService paymentService;

    @Resource
    private DiscoveryClient discoveryClient;

    @Value("${server.port}")
    private String serverPort;

    @PostMapping("/payment/create")
    public CommonResult create(Payment payment) {
        int insert = paymentService.create(payment);
        if (insert>0){
            return new CommonResult<Payment>("200","success port:"+serverPort,null);
        }
        return new CommonResult<Payment>("500","failure",null);
    }

    @GetMapping("/payment/get/{id}")
    public CommonResult getPaymentById(@PathVariable("id") Long id) {
        Payment payment = paymentService.getPaymentById(id);
        if (payment==null){
            return new CommonResult<Payment>("500","null",null);
        }
        return new CommonResult<Payment>("200","success port:"+serverPort,payment);
    }

    @GetMapping("/payment/discovery")
    public CommonResult discovery() {
        List<String> services = discoveryClient.getServices();
        for (String e :services){
            System.out.println(e);
        }
        List<ServiceInstance> instances = discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");
        for (ServiceInstance s :instances){
            System.out.println(s.getHost()+":"+s.getPort());
        }
        return new CommonResult("200","success port:"+serverPort,services);
    }

    @GetMapping("/payment/gets")
    public String demo() throws InterruptedException {
        Thread.sleep(3000);
        return "8001";
    }
}
