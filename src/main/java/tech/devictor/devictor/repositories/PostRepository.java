package tech.devictor.devictor.repositories;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import tech.devictor.devictor.domain.entities.Post;

public interface PostRepository extends JpaRepository<Post, Long> {
    Slice<Post> findBy(Pageable pageable);
}
