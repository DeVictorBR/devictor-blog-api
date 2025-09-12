package tech.devictor.devictor.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import tech.devictor.devictor.domain.dtos.TagResponseDto;
import tech.devictor.devictor.domain.entities.Tag;

import java.util.List;
import java.util.Set;

@Repository
public interface TagRepository extends JpaRepository<Tag, Long> {

    @Query("SELECT new tech.devictor.devictor.domain.dtos.TagResponseDto(t.id, t.name, COUNT(p.id)) " +
            "FROM Tag t LEFT JOIN t.posts p GROUP BY t.id, t.name")
    List<TagResponseDto> findAllTagsWithPostCount();

    List<Tag> findByNameIn(Set<String> names);
    boolean existsByPostsTagId(Long tagId);
}
