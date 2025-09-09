package tech.devictor.devictor.domain.dtos;

import tech.devictor.devictor.domain.entities.Category;

public record CategoryResponseDto(Long id,
                                  String name,
                                  Long postCount) {
    public static CategoryResponseDto toDto(Category category) {
        return new CategoryResponseDto(
                category.getId(),
                category.getName(),
                (long) category.getPosts().size()
        );
    }
}
