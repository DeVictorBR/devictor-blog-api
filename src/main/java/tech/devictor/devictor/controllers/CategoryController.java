package tech.devictor.devictor.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tech.devictor.devictor.domain.dtos.CategoryResponseDto;
import tech.devictor.devictor.services.CategoryService;

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
}
