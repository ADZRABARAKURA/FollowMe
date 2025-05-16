package ru.usov.followme.domain.subscription;

import jakarta.persistence.*;
import lombok.*;
import ru.usov.followme.domain.keys.SubscriptionPlanId;

import java.time.LocalDate;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Entity
public class Subscription {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID subscriptionId;

    @Column(nullable = false)
    private UUID userId;

    @Column(nullable = false)
    private SubscriptionPlanId subscriptionPlanId;

    @Column(nullable = false)
    private LocalDate expiresAt;

    @Column(nullable = false)
    private Boolean active;
}
