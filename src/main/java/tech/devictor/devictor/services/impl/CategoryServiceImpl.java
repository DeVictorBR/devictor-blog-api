package tech.devictor.devictor.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tech.devictor.devictor.domain.dtos.CategoryResponseDto;
import tech.devictor.devictor.domain.dtos.CreateCategoryDto;
import tech.devictor.devictor.domain.entities.Category;
import tech.devictor.devictor.exceptions.CategoryNameAlreadyExistException;
import tech.devictor.devictor.exceptions.CategoryNotFoundException;
import tech.devictor.devictor.exceptions.CategoryWithAssociatedPostsException;
import tech.devictor.devictor.repositories.CategoryRepository;
import tech.devictor.devictor.services.CategoryService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    @Override
    public List<CategoryResponseDto> listCategories() {
        return categoryRepository.findAllCategoriesWithPostCount();
    }

    @Override
    public Long createCategory(CreateCategoryDto createCategoryDto) {
        if (categoryRepository.existsByNameIgnoreCase(createCategoryDto.name())) {
            throw new CategoryNameAlreadyExistException(String.format("Category already exists with name: %s", createCategoryDto.name()));
        }

        Category category = createCategoryDto.toEntity();
        Category savedCategory = categoryRepository.save(category);
        return savedCategory.getId();
    }

    @Override
    public void deleteCategory(Long id) {
        Category category = getCategoryEntityById(id);
        if (categoryRepository.existsByPostsCategoryId(id)) {
            throw new CategoryWithAssociatedPostsException(String.format("Category with name: %s has posts associated with it", category.getName()));
        }
        categoryRepository.delete(category);
    }

    private Category getCategoryEntityById(Long id) {
        return categoryRepository.findById(id)
                .orElseThrow(() -> new CategoryNotFoundException(String.format("Category with id: %d not found", id)));
    }
}
