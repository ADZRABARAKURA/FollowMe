package ru.usov.followme.domain.publication;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Entity
public class Publication {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID publicationId;

    private String title;
    private String description;

    @Column(nullable = false)
    private UUID hostId;

    @OneToMany
    private List<PublicationFile> files;

    @OneToMany
    private List<Like> likes;

    private Integer subscriptionLevel;
}
