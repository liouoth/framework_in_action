package com.leo.springcloud.controller;

import com.leo.springcloud.service.PaymentService;
import entities.CommonResult;
import entities.Payment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

@RestController
public class PaymentController {
    @Autowired
    private PaymentService paymentService;

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

    @GetMapping("/payment/gets")
    public String demo() throws InterruptedException {
        Thread.sleep(3000);
        return "8002";
    }
}
