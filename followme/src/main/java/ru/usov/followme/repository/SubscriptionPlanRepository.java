package ru.usov.followme.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.usov.followme.domain.keys.SubscriptionPlanId;
import ru.usov.followme.domain.subscription.SubscriptionPlan;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface SubscriptionPlanRepository extends JpaRepository<SubscriptionPlan, SubscriptionPlanId> {
    @Query("select sp from SubscriptionPlan sp where sp.hostId = :hostId")
    List<SubscriptionPlan> findAllByHostId(UUID hostId);

    @Query("select sp from SubscriptionPlan sp where sp.hostId = :hostId and sp.level = :level")
    Optional<SubscriptionPlan> findById(UUID hostId, Integer level);
}
