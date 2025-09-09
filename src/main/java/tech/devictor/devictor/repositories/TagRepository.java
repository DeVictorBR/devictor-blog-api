package tech.devictor.devictor.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tech.devictor.devictor.domain.entities.Tag;

@Repository
public interface TagRepository extends JpaRepository<Tag, Long> {
}
