package tech.devictor.devictor.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tech.devictor.devictor.domain.dtos.TagResponseDto;
import tech.devictor.devictor.domain.entities.Tag;
import tech.devictor.devictor.exceptions.TagNotFoundException;
import tech.devictor.devictor.repositories.TagRepository;
import tech.devictor.devictor.services.TagService;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TagServiceImpl implements TagService {

    private final TagRepository tagRepository;

    @Override
    public List<TagResponseDto> listTags() {
        return tagRepository.findAllTagsWithPostCount();
    }

    @Transactional
    @Override
    public List<Tag> createTags(Set<String> tagNames) {
        List<Tag> existingTags = tagRepository.findByNameIn(tagNames);
        Set<String> existingTagNames = existingTags.stream()
                .map(Tag::getName)
                .collect(Collectors.toSet());

        List<Tag> newTags = tagNames.stream().filter(name -> !existingTagNames.contains(name))
                .map(name -> Tag.builder()
                        .name(name)
                        .posts(new HashSet<>())
                        .build()).toList();

        List<Tag> savedTags = new ArrayList<>();
        if (!newTags.isEmpty()) {
            savedTags = tagRepository.saveAll(newTags);
        }

        savedTags.addAll(existingTags);
        return savedTags;
    }

    @Override
    public void deleteTag(Long id) {
        Tag tag = getTagEntityById(id);
        tagRepository.delete(tag);
    }

    private Tag getTagEntityById(Long id) {
        return tagRepository.findById(id)
                .orElseThrow(() -> new TagNotFoundException(String.format("Tag with id: %d not found", id)));
    }
}
