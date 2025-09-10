package tech.devictor.devictor.domain.dtos;

import tech.devictor.devictor.domain.entities.Tag;

public record TagResponseDto(Long id,
                             String name,
                             Long postCount) {
    public static TagResponseDto toDto(Tag tag) {
        return new TagResponseDto(
                tag.getId(),
                tag.getName(),
                (long) tag.getPosts().size()
        );
    }
}
