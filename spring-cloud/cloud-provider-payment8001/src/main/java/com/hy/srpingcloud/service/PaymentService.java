package com.hy.srpingcloud.service;

import com.hy.srpingcloud.entity.Payment;

public interface PaymentService {

    int save(Payment payment);

    Payment findByIdPayment(Long id);

}
