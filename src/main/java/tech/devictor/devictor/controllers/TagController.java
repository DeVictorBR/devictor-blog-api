package tech.devictor.devictor.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import tech.devictor.devictor.domain.dtos.CreateTagsRequestDto;
import tech.devictor.devictor.domain.dtos.TagResponseDto;
import tech.devictor.devictor.domain.entities.Tag;
import tech.devictor.devictor.services.TagService;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(path = "/api/v1/tags")
@RequiredArgsConstructor
public class TagController {

    private final TagService tagService;

    @GetMapping
    public ResponseEntity<List<TagResponseDto>> listTags() {
        List<TagResponseDto> tags = tagService.listTags();
        return ResponseEntity.ok(tags);
    }

    @PostMapping
    public ResponseEntity<List<TagResponseDto>> createTags(@RequestBody CreateTagsRequestDto dto) {
        List<Tag> savedTags = tagService.createTags(dto.names());
        List<TagResponseDto> responseDtos = savedTags.stream().map(TagResponseDto::toDto).toList();

        return ResponseEntity.status(HttpStatus.CREATED).body(responseDtos);
    }
}
