package ru.usov.followme.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import ru.usov.followme.domain.keys.SubscriptionPlanId;
import ru.usov.followme.domain.subscription.Subscription;
import ru.usov.followme.domain.subscription.SubscriptionPlan;
import ru.usov.followme.repository.SubscriptionPlanRepository;
import ru.usov.followme.repository.SubscriptionRepository;

import java.time.Instant;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class SubscriptionService {
    private final SubscriptionRepository repository;
    private final SubscriptionPlanRepository planRepository;

    @Scheduled(cron = "0 0 0 * * *")
    public void deactivateSubscriptions(){
        LocalDate today = LocalDate.now();

        List<Subscription> expired = repository.findAllByActiveTrueAndExpiresAtBefore(today);

        for (Subscription s : expired) {
            s.setActive(false);
        }

        repository.saveAll(expired);
    }

    @Transactional
    public Subscription createInactiveSubscription(UUID userId, UUID hostId, Integer level) {
        var subscription = Subscription.builder()
            .active(false)
            .subscriptionPlanId(new SubscriptionPlanId(hostId, level))
            .userId(userId)
            .build();

        return repository.save(subscription);
    }

    public void activateSubscription(UUID subscriptionId) {
        var subscription = getById(subscriptionId);
        subscription.setActive(true);
        subscription.setExpiresAt(LocalDate.now().plusMonths(1));
        repository.save(subscription);
    }

    public SubscriptionPlan getPlanById(UUID hostId, Integer level) {
        return planRepository.findById(hostId, level).orElseThrow(); //TODO
    }

    private Subscription getById(UUID subscriptionId) {
        return repository.findById(subscriptionId).orElseThrow(); //TODO
    }

    public Integer getSubscriptionLevel(UUID userId, UUID hostId) {
        return repository.findCurrentLevelByUserAndHost(userId, hostId).orElse(0);
    }
}
