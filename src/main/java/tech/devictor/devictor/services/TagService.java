package tech.devictor.devictor.services;

import tech.devictor.devictor.domain.dtos.TagResponseDto;

import java.util.List;

public interface TagService {
    List<TagResponseDto> listTags();
}
