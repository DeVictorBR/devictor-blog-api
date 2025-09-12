package tech.devictor.devictor.services;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import tech.devictor.devictor.domain.dtos.PostResponseDto;

public interface PostService {
    Slice<PostResponseDto> getAllPosts(Long categoryId, Long tagId, Pageable pageable);
}
