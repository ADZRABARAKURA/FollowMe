package ru.usov.followme.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.usov.followme.dto.PaymentResult;
import ru.usov.followme.exceptions.IllegalPaymentStateException;
import ru.usov.followme.service.SubscriptionOrderService;

@RestController
@RequestMapping("/payments")
@RequiredArgsConstructor
public class PaymentController {
    private final SubscriptionOrderService service;
    @PostMapping
    public void updatePaymentStatus(@RequestBody PaymentResult paymentResult) throws IllegalPaymentStateException {
        service.handleSubscriptionPayment(paymentResult);
    }
}
