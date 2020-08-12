package com.hy.srpingcloud.service;

import com.hy.springcloid.entity.Payment;

public interface PaymentService {

    int save(Payment payment);

    Payment findByIdPayment(Long id);

}
