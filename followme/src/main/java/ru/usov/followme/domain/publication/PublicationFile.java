package ru.usov.followme.domain.publication;

import jakarta.persistence.*;
import lombok.*;
import ru.usov.followme.enums.PublicationFileType;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Entity
public class PublicationFile {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID publicationFileId;

    @Column(nullable = false)
    private UUID publicationId;

    @Column(nullable = false)
    private String fileId;

    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    private PublicationFileType type;
}
