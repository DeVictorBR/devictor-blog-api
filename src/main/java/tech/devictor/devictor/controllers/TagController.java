package tech.devictor.devictor.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tech.devictor.devictor.domain.dtos.TagResponseDto;
import tech.devictor.devictor.services.TagService;

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
}
