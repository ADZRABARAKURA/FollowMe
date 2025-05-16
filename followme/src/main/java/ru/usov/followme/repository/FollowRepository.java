package ru.usov.followme.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.usov.followme.domain.keys.FollowId;
import ru.usov.followme.domain.user.Follow;

import java.util.List;
import java.util.Set;
import java.util.UUID;

public interface FollowRepository extends JpaRepository<Follow, FollowId> {
    List<Follow> findByFollowerId(UUID followerId);
}
