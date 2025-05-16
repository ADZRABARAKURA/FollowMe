package ru.usov.followme.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.usov.followme.domain.publication.PublicationFile;

import java.util.UUID;

public interface PublicationFileRepository extends JpaRepository<PublicationFile, UUID> {
}
