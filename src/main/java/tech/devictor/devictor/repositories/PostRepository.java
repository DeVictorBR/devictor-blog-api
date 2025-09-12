package tech.devictor.devictor.repositories;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import tech.devictor.devictor.domain.PostStatus;
import tech.devictor.devictor.domain.entities.Category;
import tech.devictor.devictor.domain.entities.Post;
import tech.devictor.devictor.domain.entities.Tag;

public interface PostRepository extends JpaRepository<Post, Long> {
    Slice<Post> findAllByStatusAndCategoryAndTagsContaining(PostStatus status, Category category, Tag tag, Pageable pageable);
    Slice<Post> findAllByStatusAndCategory(PostStatus status, Category category, Pageable pageable);
    Slice<Post> findAllByStatusAndTagsContaining(PostStatus status, Tag tag, Pageable pageable);
    Slice<Post> findAllByStatus(PostStatus status, Pageable pageable);
}
