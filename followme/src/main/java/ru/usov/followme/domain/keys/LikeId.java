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
public class LikeId implements Serializable {
    private UUID userId;
    private UUID publicationId;
}
