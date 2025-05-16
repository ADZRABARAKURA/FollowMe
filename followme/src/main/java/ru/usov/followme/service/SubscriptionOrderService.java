package ru.usov.followme.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ru.usov.followme.dto.PaymentResult;
import ru.usov.followme.exceptions.IllegalPaymentStateException;

import java.net.URI;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class SubscriptionOrderService {
    private final PaymentService paymentService;
    private final SubscriptionService subscriptionService;

    public ResponseEntity<?> createSubscriptionOrder(UUID userId, UUID hostId, Integer level) {
        var inactiveSubscription = subscriptionService.createInactiveSubscription(userId, hostId, level);
        var subscriptionPlan = subscriptionService.getPlanById(hostId, level);

        var payment = paymentService.createPayment(
            inactiveSubscription.getSubscriptionId(),
            subscriptionPlan.getPrice()
        );

        return ResponseEntity.status(HttpStatus.FOUND)
            .location(URI.create(payment.getPaymentLink()))
            .build();
    }

    public void handleSubscriptionPayment(PaymentResult paymentResult) throws IllegalPaymentStateException {
        switch (paymentResult.object().status()){
            case "success" -> {
                var payment = paymentService.getById(paymentResult.object().id()).orElseThrow(); //TODO
                subscriptionService.activateSubscription(payment.getSubscriptionId());
            }
            case "cancelled" ->{
                paymentService.cancelPayment(paymentResult.object().id());
            }
            default -> {
                throw new IllegalPaymentStateException(paymentResult.object().status());
            }
        }
    }
}
