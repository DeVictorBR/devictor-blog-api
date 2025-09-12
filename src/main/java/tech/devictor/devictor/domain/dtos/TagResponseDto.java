package tech.devictor.devictor.domain.dtos;

import tech.devictor.devictor.domain.entities.Tag;

import java.util.Set;
import java.util.stream.Collectors;

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
    public static Set<TagResponseDto> toDto(Set<Tag> tags) {
        return tags.stream()
                .map(TagResponseDto::toDto)
                .collect(Collectors.toSet());
    }
}
