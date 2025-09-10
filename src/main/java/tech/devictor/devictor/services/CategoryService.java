package tech.devictor.devictor.services;

import tech.devictor.devictor.domain.dtos.CategoryResponseDto;
import tech.devictor.devictor.domain.dtos.CreateCategoryDto;

import java.util.List;

public interface CategoryService {
    List<CategoryResponseDto> listCategories();
    Long createCategory(CreateCategoryDto createCategoryDto);
}
