package com.lahcencodes.mediaserver.dao;

import com.lahcencodes.mediaserver.entity.CreatorEntity;
import com.lahcencodes.mediaserver.repository.CreatorRepository;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class CreatorDao {

    private final CreatorRepository creatorRepository;

    public CreatorDao(CreatorRepository creatorRepository) {
        this.creatorRepository = creatorRepository;
    }

    public CreatorEntity getCreatorById(String id) {
        return creatorRepository.findById(UUID.fromString(id)).orElse(null);
    }

    public CreatorEntity saveCreator(CreatorEntity creatorEntity) {
        return creatorRepository.save(creatorEntity);
    }
}