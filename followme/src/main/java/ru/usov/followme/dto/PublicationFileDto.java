package ru.usov.followme.dto;

import ru.usov.followme.enums.PublicationFileType;

public record PublicationFileDto(String fileId, PublicationFileType fileType) {
}
