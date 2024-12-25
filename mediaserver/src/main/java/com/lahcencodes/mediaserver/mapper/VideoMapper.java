package com.lahcencodes.mediaserver.mapper;

import com.lahcencodes.mediaserver.entity.VideoEntity;
import com.lahcencodes.proto.Video;
import com.lahcencodes.proto.Creator;
import org.springframework.stereotype.Component;

@Component
public class VideoMapper {

    public VideoEntity toEntity(Video video) {
        return new VideoEntity(
                null, // ID is auto-generated
                video.getTitle(),
                video.getDescription(),
                video.getUrl(),
                video.getDurationSeconds(),
                null // Creator is managed separately
        );
    }

    public Video toProto(VideoEntity entity) {
        Creator creator = Creator.newBuilder()
                .setId(entity.getCreator().getId().toString())
                .setName(entity.getCreator().getName())
                .setEmail(entity.getCreator().getEmail())
                .build();

        return Video.newBuilder()
                .setId(entity.getId().toString())
                .setTitle(entity.getTitle())
                .setDescription(entity.getDescription())
                .setUrl(entity.getUrl())
                .setDurationSeconds(entity.getDurationSeconds())
                .setCreator(creator)
                .build();
    }
}