package tech.devictor.devictor.services;

import tech.devictor.devictor.domain.dtos.CategoryResponseDto;

import java.util.List;

public interface CategoryService {
    List<CategoryResponseDto> listCategories();
}
