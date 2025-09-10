package tech.devictor.devictor.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import tech.devictor.devictor.domain.dtos.CategoryResponseDto;
import tech.devictor.devictor.domain.entities.Category;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

    @Query("SELECT new tech.devictor.devictor.domain.dtos.CategoryResponseDto(c.id, c.name, COUNT(p.id)) " +
            "FROM Category c LEFT JOIN c.posts p GROUP BY c.id, c.name")
    List<CategoryResponseDto> findAllCategoriesWithPostCount();

    boolean existsByNameIgnoreCase(String name);
}
