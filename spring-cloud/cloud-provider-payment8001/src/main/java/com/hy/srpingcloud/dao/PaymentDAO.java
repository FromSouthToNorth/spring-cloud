package com.hy.srpingcloud.dao;

import com.hy.srpingcloud.entity.Payment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface PaymentDAO {

    int save(Payment payment);

    Payment findByIdPayment(@Param("id") Long id);

}
