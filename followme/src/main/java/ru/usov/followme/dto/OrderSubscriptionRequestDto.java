package ru.usov.followme.dto;

import java.util.UUID;

public record OrderSubscriptionRequestDto(UUID hostId, Integer level) {
}
