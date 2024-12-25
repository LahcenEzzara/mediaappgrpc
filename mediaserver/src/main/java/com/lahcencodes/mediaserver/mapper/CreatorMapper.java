package com.lahcencodes.mediaserver.mapper;

import com.lahcencodes.mediaserver.entity.CreatorEntity;
import com.lahcencodes.proto.Creator;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class CreatorMapper {

    public CreatorEntity toEntity(Creator creator) {
        return new CreatorEntity(
                UUID.fromString(creator.getId()),
                creator.getName(),
                creator.getEmail(),
                null // Videos will be managed separately
        );
    }

    public Creator toProto(CreatorEntity entity) {
        return Creator.newBuilder()
                .setId(entity.getId().toString())
                .setName(entity.getName())
                .setEmail(entity.getEmail())
                .build();
    }
}