package ru.usov.followme.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.usov.followme.domain.keys.LikeId;
import ru.usov.followme.domain.publication.Like;

public interface LikeRepository extends JpaRepository<Like, LikeId> {
}
