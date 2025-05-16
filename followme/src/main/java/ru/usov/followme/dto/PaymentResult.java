package ru.usov.followme.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record PaymentResult(
    String type,
    PaymentObject object
) {
    @JsonIgnoreProperties(ignoreUnknown = true)
    public record PaymentObject(
        String id,
        String status
    ) {}
}
