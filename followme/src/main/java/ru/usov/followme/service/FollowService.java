package ru.usov.followme.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.usov.followme.domain.keys.FollowId;
import ru.usov.followme.domain.user.Follow;
import ru.usov.followme.repository.FollowRepository;

import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FollowService {
    private final FollowRepository followRepository;

    public void follow(UUID userId, UUID hostId) {
        var follow = Follow.builder()
            .followerId(userId)
            .hostId(hostId)
            .build();

        followRepository.save(follow);
    }

    public void unfollow(UUID userId, UUID hostId) {
        followRepository.deleteById(new FollowId(hostId, userId));
    }

    public Set<UUID> getFollows(UUID userId) {
        return followRepository.findByFollowerId(userId).stream().map(Follow::getHostId).collect(Collectors.toSet());
    }
}
