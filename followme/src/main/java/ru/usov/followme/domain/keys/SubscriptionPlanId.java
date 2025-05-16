package ru.usov.followme.domain.keys;

import jakarta.persistence.Embeddable;
import lombok.*;

import java.io.Serializable;
import java.util.UUID;

@Embeddable
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class SubscriptionPlanId implements Serializable {
    private UUID hostId;
    private Integer level;
}
