package ru.usov.followme.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.*;
import ru.usov.followme.dto.OrderSubscriptionRequestDto;
import ru.usov.followme.repository.UserRepository;
import ru.usov.followme.service.SubscriptionOrderService;
import ru.usov.followme.service.SubscriptionService;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/subscription")
public class SubscriptionController {
    private final SubscriptionOrderService service;
    private final UserRepository userRepository;

    @PostMapping
    public ResponseEntity<?> orderSubscription(@AuthenticationPrincipal OAuth2User principal, @RequestBody OrderSubscriptionRequestDto orderSubscriptionRequestDto){

        var userId = userRepository.findUserIdByKeycloakSub(principal.getAttribute("sub"));

        return userId.map(uuid -> service.createSubscriptionOrder(uuid, orderSubscriptionRequestDto.hostId(), orderSubscriptionRequestDto.level())).orElse(null);
    }
}
