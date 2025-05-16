package ru.usov.followme.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record PaymentResponse(
    String id,
    String status,
    PaymentRequest.Amount amount,
    ConfirmationResponse confirmation,
    String created_at,
    String description,
    Recipient recipient,
    Boolean refundable,
    Boolean test
) {

    @JsonIgnoreProperties(ignoreUnknown = true)
    public record ConfirmationResponse(
        String type,
        String confirmation_url
    ) {}

    @JsonIgnoreProperties(ignoreUnknown = true)
    public record Recipient(
        String account_id,
        String gateway_id
    ) {}
}
