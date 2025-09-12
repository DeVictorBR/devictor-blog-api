package tech.devictor.devictor.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import tech.devictor.devictor.domain.dtos.PostResponseDto;
import tech.devictor.devictor.services.PostService;

@RestController
@RequestMapping(path = "/api/v1/posts")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @GetMapping
    public ResponseEntity<Slice<PostResponseDto>> getAllPosts(
            @RequestParam(required = false) Long categoryId,
            @RequestParam(required = false) Long tagId,
            Pageable pageable) {
        Slice<PostResponseDto> allPosts = postService.getAllPosts(categoryId, tagId, pageable);
        return ResponseEntity.ok(allPosts);
    }
}
