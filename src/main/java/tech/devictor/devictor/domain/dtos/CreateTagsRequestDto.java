package tech.devictor.devictor.domain.dtos;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import java.util.Set;

public record CreateTagsRequestDto(
        @NotEmpty(message = "At least one tag name is required")
        @Size(max = 10, message = "Maximum {max} tags allowed")
        Set<
                @Size(min = 2, max = 30, message = "Tag name must be between {min} and {max} characters")
                @Pattern(regexp = "^[\\w\\s-]+$", message = "Tag name can only contain letters, number, spaces and hyphens")
                String> names
) {
}
