package tech.devictor.devictor.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tech.devictor.devictor.domain.PostStatus;
import tech.devictor.devictor.domain.dtos.CreatePostRequestDto;
import tech.devictor.devictor.domain.dtos.PostResponseDto;
import tech.devictor.devictor.domain.dtos.UpdatePostRequestDto;
import tech.devictor.devictor.domain.entities.Category;
import tech.devictor.devictor.domain.entities.Post;
import tech.devictor.devictor.domain.entities.Tag;
import tech.devictor.devictor.exceptions.PostNotFoundException;
import tech.devictor.devictor.repositories.PostRepository;
import tech.devictor.devictor.services.CategoryService;
import tech.devictor.devictor.services.PostService;
import tech.devictor.devictor.services.TagService;

import java.util.HashSet;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;
    private final CategoryService categoryService;
    private final TagService tagService;

    @Override
    @Transactional(readOnly = true)
    public Slice<PostResponseDto> getAllPosts(Long categoryId, Long tagId, Pageable pageable) {
        Slice<Post> posts;

        Category category = null;
        if (categoryId != null) {
            category = categoryService.getCategoryById(categoryId);
        }

        Tag tag = null;
        if (tagId != null) {
            tag = tagService.getTagById(tagId);
        }

        if (category != null && tag != null) {
            posts = postRepository.findAllByStatusAndCategoryAndTagsContaining(
                    PostStatus.PUBLISHED,
                    category,
                    tag,
                    pageable
            );
        } else if (category != null) {
            posts = postRepository.findAllByStatusAndCategory(
                    PostStatus.PUBLISHED,
                    category,
                    pageable
            );
        } else if (tag != null) {
            posts = postRepository.findAllByStatusAndTagsContaining(
                    PostStatus.PUBLISHED,
                    tag,
                    pageable
            );
        } else {
            posts = postRepository.findAllByStatus(
                    PostStatus.PUBLISHED,
                    pageable
            );
        }

        return posts.map(PostResponseDto::toDto);
    }

    @Override
    public Slice<PostResponseDto> getAllDrafts(Pageable pageable) {
        Slice<Post> posts = postRepository.findAllByStatus(
                PostStatus.DRAFT,
                pageable
        );
        return posts.map(PostResponseDto::toDto);
    }

    @Override
    public PostResponseDto createPost(CreatePostRequestDto dto) {
        Category category = categoryService.getCategoryById(dto.categoryId());
        Set<Tag> tags = tagService.getTagsByIds(dto.tagIds());
        Post post = dto.toEntity();
        post.setCategory(category);
        post.setTags(tags);

        Post savedPost = postRepository.save(post);

        return PostResponseDto.toDto(savedPost);
    }

    @Override
    @Transactional
    public PostResponseDto updatePost(Long id, UpdatePostRequestDto dto) {
        Post post = getPostEntityById(id);

        if (dto.title() != null) {
            post.setTitle(dto.title());
        }
        if (dto.content() != null) {
            post.setContent(dto.content());
        }
        if (dto.status() != null) {
            post.setStatus(dto.status());
        }

        Category category = null;
        if (dto.categoryId() != null) {
            category = categoryService.getCategoryById(dto.categoryId());
        }
        post.setCategory(category);

        Set<Tag> tags = new HashSet<>();
        if (dto.tagIds() != null) {
            tags = tagService.getTagsByIds(dto.tagIds());
        }
        post.setTags(tags);

        Post savedPost = postRepository.save(post);
        return PostResponseDto.toDto(savedPost);
    }

    private Post getPostEntityById(Long id) {
        return postRepository.findById(id)
                .orElseThrow(() -> new PostNotFoundException(String.format("Post not found with id: %d", id)));
    }
}
