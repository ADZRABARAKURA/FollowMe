package ru.usov.followme.dto;

public record PaymentRequest(
    Amount amount,
    Boolean capture,
    Confirmation confirmation,
    String description
) {
    public record Amount(
        Double value,
        String currency
    ) {}

    public record Confirmation(
        String type,
        String return_url
    ) {}
}
