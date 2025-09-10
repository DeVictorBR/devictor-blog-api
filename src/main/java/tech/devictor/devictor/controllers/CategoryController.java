package tech.devictor.devictor.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import tech.devictor.devictor.domain.dtos.CategoryResponseDto;
import tech.devictor.devictor.domain.dtos.CreateCategoryDto;
import tech.devictor.devictor.services.CategoryService;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(path = "/api/v1/categories")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping
    public ResponseEntity<List<CategoryResponseDto>> listCategories() {
        List<CategoryResponseDto> categories = categoryService.listCategories();
        return ResponseEntity.ok(categories);
    }

    @PostMapping
    public ResponseEntity<Void> createCategory(@Valid @RequestBody CreateCategoryDto createCategoryDto) {
        Long categoryId = categoryService.createCategory(createCategoryDto);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(categoryId)
                .toUri();
        return ResponseEntity.created(location).build();
    }
}
