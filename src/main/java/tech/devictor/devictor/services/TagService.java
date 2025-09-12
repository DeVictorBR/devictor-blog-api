package tech.devictor.devictor.services;

import tech.devictor.devictor.domain.dtos.TagResponseDto;
import tech.devictor.devictor.domain.entities.Tag;

import java.util.List;
import java.util.Set;

public interface TagService {
    List<TagResponseDto> listTags();
    List<Tag> createTags(Set<String> tagNames);
    void deleteTag(Long id);
}
