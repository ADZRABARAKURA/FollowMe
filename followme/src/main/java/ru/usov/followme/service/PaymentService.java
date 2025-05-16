package ru.usov.followme.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.usov.followme.domain.payment.Payment;
import ru.usov.followme.dto.PaymentRequest;
import ru.usov.followme.enums.PaymentStatus;
import ru.usov.followme.repository.PaymentRepository;
import ru.usov.followme.util.Money;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PaymentService {
    private final YookassaService yookassaService;
    private final PaymentRepository paymentRepository;

    @Value("${app.frontend.baseurl}")
    private String frontendBaseurl;

    public Payment createPayment(UUID subscriptionId, Money price) {
        var paymentRequest = new PaymentRequest(new PaymentRequest.Amount(price.toBigDecimal().doubleValue(), "RUB"),
            true,
            (new PaymentRequest.Confirmation("redirect", frontendBaseurl)),
            "Заказ на оплату подписки."
        );
        var paymentResponse = yookassaService.createPayment(paymentRequest);

        return paymentRepository.save(Payment.builder()
                .subscriptionId(subscriptionId)
                .paymentLink(paymentResponse.confirmation().confirmation_url())
                .paymentId(paymentResponse.id())
                .status(PaymentStatus.PENDING)
                .price(price)
            .build());
    }

    public Optional<Payment> getById(String id) {
        return paymentRepository.findById(id);
    }

    public void cancelPayment(String id) {
        var payment = getById(id).orElseThrow(); //TODO
        payment.setStatus(PaymentStatus.CANCELLED);
        paymentRepository.save(payment);
    }
}
