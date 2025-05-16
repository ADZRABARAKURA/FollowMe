package ru.usov.followme.domain.subscription;

import jakarta.persistence.*;
import lombok.*;
import ru.usov.followme.domain.keys.SubscriptionPlanId;
import ru.usov.followme.util.Money;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Entity
@IdClass(SubscriptionPlanId.class)
public class SubscriptionPlan {
    @Id
    private UUID hostId;

    @Id
    private Integer level;

    private String title;
    private String description;
    private Money price;
}
