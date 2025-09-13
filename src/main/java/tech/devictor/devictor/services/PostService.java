package tech.devictor.devictor.services;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import tech.devictor.devictor.domain.dtos.CreatePostRequestDto;
import tech.devictor.devictor.domain.dtos.PostResponseDto;
import tech.devictor.devictor.domain.dtos.UpdatePostRequestDto;

public interface PostService {
    Slice<PostResponseDto> getAllPosts(Long categoryId, Long tagId, Pageable pageable);
    Slice<PostResponseDto> getAllDrafts(Pageable pageable);
    PostResponseDto createPost(CreatePostRequestDto dto);
    PostResponseDto updatePost(Long id , UpdatePostRequestDto dto);
}
