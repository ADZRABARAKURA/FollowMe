package ru.usov.followme.dto;

import java.util.List;

public record PublicationDto (String title, String availableBody, Integer likesCount, Boolean fullContent, Integer requiredLevel, List<PublicationFileDto> files){
}
