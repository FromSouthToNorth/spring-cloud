package com.hy.srpingcloud.controller;

import com.hy.springcloid.entity.CommonResult;
import com.hy.springcloid.entity.Payment;
import com.hy.srpingcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@Slf4j
public class PaymentController {

    @Resource
    private PaymentService paymentService;

    @Value("${server.port}")
    private String serverPort;

    @PostMapping(value = "/payment/create")
    public CommonResult create(@RequestBody Payment payment) {
        int save = paymentService.save(payment);
        if (save > 0) {
            return new CommonResult(200, "插入数据成功！serverPort:" + serverPort, save);
        } else {
            return new CommonResult(444, "插入数据失败", null);
        }
    }

    @GetMapping(value = "/payment/get/{id}")
    public CommonResult create(@PathVariable("id") Long id) {
        Payment byIdPayment = paymentService.findByIdPayment(id);
        if (byIdPayment != null) {
            return new CommonResult(200, "查询成功！serverPort:" + serverPort, byIdPayment);
        } else {
            return new CommonResult(444, "没有对应记录，查询id：" + id, null);
        }
    }

}
