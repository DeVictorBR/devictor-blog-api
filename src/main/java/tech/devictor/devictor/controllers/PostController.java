package tech.devictor.devictor.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import tech.devictor.devictor.domain.dtos.CreatePostRequestDto;
import tech.devictor.devictor.domain.dtos.PostResponseDto;
import tech.devictor.devictor.services.PostService;

import java.net.URI;

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

    @GetMapping("/drafts")
    public ResponseEntity<Slice<PostResponseDto>> getDrafts(Pageable pageable) {
        Slice<PostResponseDto> drafts = postService.getAllDrafts(pageable);
        return ResponseEntity.ok(drafts);
    }

    @PostMapping
    public ResponseEntity<PostResponseDto> createPost(@RequestBody CreatePostRequestDto dto) {
        PostResponseDto responseDto = postService.createPost(dto);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(responseDto.id())
                .toUri();
        return ResponseEntity.created(location).body(responseDto);
    }
}
