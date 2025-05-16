package ru.usov.followme.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.usov.followme.client.YookassaClient;
import ru.usov.followme.dto.PaymentRequest;
import ru.usov.followme.dto.PaymentResponse;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class YookassaService {
    private final YookassaClient yookassaClient;
    @Value("${app.payments.shopId}")
    private Long shopId;

    @Value("${app.payments.secretKey}")
    private String secretKey;

    public PaymentResponse createPayment(PaymentRequest paymentRequest){
        String auth = shopId + ":" + secretKey;

        String encodedAuth = Base64.getEncoder().encodeToString(auth.getBytes(StandardCharsets.UTF_8));
        String authorizationHeader = "Basic " + encodedAuth;

        return yookassaClient.createPayment(paymentRequest, authorizationHeader, UUID.randomUUID().toString());
    }
}