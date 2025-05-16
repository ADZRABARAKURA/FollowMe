package ru.usov.followme.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.usov.followme.domain.user.User;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
    @Query("select u.userId from User u where u.keycloakSub = :sub")
    Optional<UUID> findUserIdByKeycloakSub(@Param("sub") String sub);

}
