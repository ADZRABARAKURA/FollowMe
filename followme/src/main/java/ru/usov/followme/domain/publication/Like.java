package ru.usov.followme.domain.publication;

import jakarta.persistence.*;
import lombok.*;
import ru.usov.followme.domain.keys.LikeId;

import java.util.UUID;

@Entity
@IdClass(LikeId.class)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Like {

    @Id
    private UUID userId;

    @Id
    private UUID publicationId;
}
