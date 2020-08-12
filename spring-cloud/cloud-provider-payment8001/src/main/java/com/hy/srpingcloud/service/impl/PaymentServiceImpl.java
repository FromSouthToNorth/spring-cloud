package com.hy.srpingcloud.service.impl;

import com.hy.srpingcloud.dao.PaymentDAO;
import com.hy.springcloid.entity.Payment;
import com.hy.srpingcloud.service.PaymentService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class PaymentServiceImpl implements PaymentService {

    @Resource
    private PaymentDAO paymentDAO;

    @Override
    public int save(Payment payment) {
        return paymentDAO.save(payment);
    }

    @Override
    public Payment findByIdPayment(Long id) {
        return paymentDAO.findByIdPayment(id);
    }
}
