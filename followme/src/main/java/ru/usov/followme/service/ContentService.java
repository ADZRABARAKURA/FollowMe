package ru.usov.followme.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import ru.usov.followme.domain.publication.Publication;
import ru.usov.followme.dto.PublicationDto;
import ru.usov.followme.dto.PublicationFileDto;
import ru.usov.followme.repository.PublicationRepository;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ContentService {
    private static final Integer MAX_PUBLICATIONS_PER_PAGE = 10;
    private final SubscriptionService subscriptionService;
    private final PublicationRepository publicationRepository;
    private final FollowService followService;

    public List<PublicationDto> getAvailableContent(UUID userId, UUID hostId, Integer page) {
        Pageable pageable = PageRequest.of(
            page,
            MAX_PUBLICATIONS_PER_PAGE,
            Sort.by("publishDate").descending()
        );

        var publications = publicationRepository.findAllByHostId(pageable, hostId);
        return preparePublications(publications, userId);
    }

    public List<PublicationDto> getAvailableFollowContent(UUID userId, Integer page) {
        Pageable pageable = PageRequest.of(
            page,
            MAX_PUBLICATIONS_PER_PAGE,
            Sort.by("publishDate").descending()
        );

        var follows = followService.getFollows(userId);

        var publications = publicationRepository.findAllByHostIds(follows, pageable);
        return preparePublications(publications, userId);
    }

    private List<PublicationDto> preparePublications(Page<Publication> publications, UUID userId) {
        return publications.map((p) -> {

            var userSubscriptionLevel = subscriptionService.getSubscriptionLevel(
                userId,
                p.getHostId()
            );

            return new PublicationDto(
                p.getTitle(),
                userSubscriptionLevel >= p.getSubscriptionLevel() ?
                    p.getDescription() :
                    "Контент скрыт.",
                p.getLikes().size(),
                userSubscriptionLevel >= p.getSubscriptionLevel(),
                p.getSubscriptionLevel(),
                userSubscriptionLevel >= p.getSubscriptionLevel() ?
                    p.getFiles().stream().map((pf) -> new PublicationFileDto(pf.getFileId(), pf.getType())).toList() :
                    null
            );
        }).stream().toList();
    }
}
