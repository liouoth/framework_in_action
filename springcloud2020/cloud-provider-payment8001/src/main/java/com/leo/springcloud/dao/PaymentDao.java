package com.leo.springcloud.dao;

import entities.Payment;
import org.apache.ibatis.annotations.Mapper;

//@Repository有可能会有问题
@Mapper
public interface PaymentDao {
    int deleteByPrimaryKey(Long id);

    int insert(Payment record);

    int insertSelective(Payment record);

    Payment selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Payment record);

    int updateByPrimaryKey(Payment record);
}