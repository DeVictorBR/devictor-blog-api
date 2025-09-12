package tech.devictor.devictor.domain.dtos;

import tech.devictor.devictor.domain.PostStatus;
import tech.devictor.devictor.domain.entities.Post;

import java.time.Instant;
import java.util.Set;

public record PostResponseDto(
        Long id,
        String title,
        String content,
        PostStatus status,
        CategoryResponseDto category,
        Set<TagResponseDto> tags,
        Instant createdAt,
        Instant updatedAt
) {
    public static PostResponseDto toDto(Post post) {
        return new PostResponseDto(
                post.getId(),
                post.getTitle(),
                post.getContent(),
                post.getStatus(),
                CategoryResponseDto.toDto(post.getCategory()),
                TagResponseDto.toDto(post.getTags()),
                post.getCreatedAt(),
                post.getUpdatedAt()
        );
    }
}
