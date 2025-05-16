package ru.usov.followme.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.usov.followme.domain.subscription.Subscription;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface SubscriptionRepository extends JpaRepository<Subscription, UUID> {
    List<Subscription> findAllByActiveTrueAndExpiresAtBefore(LocalDate today);

    @Query("SELECT s.subscriptionPlanId.level " +
        "FROM Subscription s " +
        "WHERE s.userId = :userId " +
        "AND s.subscriptionPlanId.hostId = :hostId " +
        "AND s.active = true " +
        "AND s.expiresAt > CURRENT_DATE")
    Optional<Integer> findCurrentLevelByUserAndHost(
        @Param("userId") UUID userId,
        @Param("hostId") UUID hostId
    );
}
