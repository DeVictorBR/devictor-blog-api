package tech.devictor.devictor.domain.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import tech.devictor.devictor.domain.PostStatus;
import tech.devictor.devictor.domain.entities.Post;

import java.util.Set;

public record CreatePostRequestDto(
        @NotBlank(message = "Title is required")
        @Size(min = 3, max = 200, message = "Title must be between {min} and {max} characters")
        String title,
        @NotBlank(message = "Content is required")
        @Size(min = 10, max = 50000, message = "Content must be between {min} and {max} characters")
        String content,

        @NotNull(message = "Category ID is required")
        Long categoryId,

        @Size(min = 10, message = "Maximum {max} tags allowed")
        Set<Long> tagIds,

        @NotNull(message = "Status is required")
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
