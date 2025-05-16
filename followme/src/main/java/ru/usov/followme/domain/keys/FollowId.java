package ru.usov.followme.domain.keys;

import jakarta.persistence.Embeddable;
import lombok.*;

import java.util.UUID;

@Embeddable
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class FollowId {
    private UUID hostId;
    private UUID followerId;
}
