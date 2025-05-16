package ru.usov.followme.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.usov.followme.repository.PublicationRepository;

@Service
@RequiredArgsConstructor
public class PublicationService {
    private final PublicationRepository repository;
}
