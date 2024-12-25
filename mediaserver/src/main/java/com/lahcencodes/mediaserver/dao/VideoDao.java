package com.lahcencodes.mediaserver.dao;

import com.lahcencodes.mediaserver.entity.VideoEntity;
import com.lahcencodes.mediaserver.repository.VideoRepository;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class VideoDao {

    private final VideoRepository videoRepository;

    public VideoDao(VideoRepository videoRepository) {
        this.videoRepository = videoRepository;
    }

    public VideoEntity saveVideo(VideoEntity videoEntity) {
        return videoRepository.save(videoEntity);
    }

    public VideoEntity getVideoById(String id) {
        return videoRepository.findById(UUID.fromString(id)).orElse(null);
    }
}