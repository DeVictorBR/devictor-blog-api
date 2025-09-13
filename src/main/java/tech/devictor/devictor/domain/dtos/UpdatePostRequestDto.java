package tech.devictor.devictor.domain.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import tech.devictor.devictor.domain.PostStatus;
import tech.devictor.devictor.domain.entities.Post;

import java.util.Set;

public record UpdatePostRequestDto(
        String title,
        String content,
        Long categoryId,
        Set<Long> tagIds,
        PostStatus status
) {
    public Post toEntity() {
        Post post = new Post();
        post.setTitle(title);
        post.setContent(content);
        post.setStatus(status);
        return post;
    }
}
