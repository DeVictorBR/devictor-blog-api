package tech.devictor.devictor.services;

import tech.devictor.devictor.domain.dtos.CategoryResponseDto;
import tech.devictor.devictor.domain.dtos.CreateCategoryDto;
import tech.devictor.devictor.domain.entities.Category;

import java.util.List;

public interface CategoryService {
    List<CategoryResponseDto> listCategories();
    Long createCategory(CreateCategoryDto createCategoryDto);
    void deleteCategory(Long id);
    Category getCategoryById(Long id);
}
