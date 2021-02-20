package com.leo.springcloud.service.impl;

import com.leo.springcloud.dao.PaymentDao;
import com.leo.springcloud.service.PaymentService;
import entities.Payment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class PaymentServiceImpl implements PaymentService {
    @Autowired
    private PaymentDao paymentDao;

    @Override
    public int create(Payment payment) {
        int insert = paymentDao.insert(payment);
        if (insert>0){
            return 1;
        }
        return 0;
    }

    @Override
    public Payment getPaymentById(Long id) {
        Payment payment = paymentDao.selectByPrimaryKey(id);
        return payment;
    }
}
