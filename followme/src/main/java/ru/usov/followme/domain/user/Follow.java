package ru.usov.followme.domain.user;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import lombok.*;

import java.util.UUID;

@Entity
@IdClass(Follow.class)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Follow {
    @Id
    private UUID hostId;

    @Id
    private UUID followerId;
}
