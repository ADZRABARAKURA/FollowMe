package ru.usov.followme.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.usov.followme.domain.publication.Publication;

import java.util.Set;
import java.util.UUID;

public interface PublicationRepository extends JpaRepository<Publication, UUID> {
    Page<Publication> findAll(Pageable pageable);

    Page<Publication> findAllByHostId(Pageable pageable, UUID hostId);

    @Query("SELECT p FROM Publication p WHERE p.hostId IN :hostIds")
    Page<Publication> findAllByHostIds(
        @Param("hostIds") Set<UUID> hostIds,
        Pageable pageable
    );
}
