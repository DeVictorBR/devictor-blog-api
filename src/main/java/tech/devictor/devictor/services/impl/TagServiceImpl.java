package tech.devictor.devictor.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tech.devictor.devictor.domain.dtos.TagResponseDto;
import tech.devictor.devictor.repositories.TagRepository;
import tech.devictor.devictor.services.TagService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TagServiceImpl implements TagService {

    private final TagRepository tagRepository;

    @Override
    public List<TagResponseDto> listTags() {
        return tagRepository.findAllTagsWithPostCount();
    }
}
