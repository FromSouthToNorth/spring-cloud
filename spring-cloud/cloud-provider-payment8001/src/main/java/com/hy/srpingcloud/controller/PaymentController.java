package com.hy.srpingcloud.controller;

import com.hy.srpingcloud.entity.CommonResult;
import com.hy.srpingcloud.entity.Payment;
import com.hy.srpingcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@Slf4j
public class PaymentController {

    @Resource
    private PaymentService paymentService;

    @PostMapping(value = "/payment/create")
    public CommonResult create(Payment payment) {
        int save = paymentService.save(payment);
        log.info("*****插入结果：" + save);
        if (save > 0) {
            return new CommonResult(200, "插入数据成功！", save);
        } else {
            return new CommonResult(444, "插入数据失败", null);
        }
    }

    @GetMapping(value = "/payment/get/{id}")
    public CommonResult create(@PathVariable("id") Long id) {
        Payment byIdPayment = paymentService.findByIdPayment(id);
        log.info("*****插入结果：" + byIdPayment);
        if (byIdPayment != null) {
            return new CommonResult(200, "查询成功！", byIdPayment);
        } else {
            return new CommonResult(444, "没有对应记录，查询id：" + id, null);
        }
    }

}
