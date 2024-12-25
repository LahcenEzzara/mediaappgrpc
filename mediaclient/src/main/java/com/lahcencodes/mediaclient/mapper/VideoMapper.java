package com.lahcencodes.mediaclient.mapper;

import com.lahcencodes.mediaclient.dto.VideoDto;
import com.lahcencodes.proto.UploadVideoRequest;
import com.lahcencodes.proto.Video;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class VideoMapper {

    private static CreatorMapper creatorMapper = new CreatorMapper();

    public VideoMapper(CreatorMapper creatorMapper) {
        VideoMapper.creatorMapper = creatorMapper;
    }

    public VideoDto fromProto(Video video) {
        VideoDto dto = new VideoDto();
        dto.setId(video.getId());
        dto.setTitle(video.getTitle());
        dto.setDescription(video.getDescription());
        dto.setUrl(video.getUrl());
        dto.setDurationSeconds(video.getDurationSeconds());
        dto.setCreator(creatorMapper.fromProto(video.getCreator()));
        return dto;
    }

    public static UploadVideoRequest toProto(VideoDto videoDto) {
        return UploadVideoRequest.newBuilder().setTitle(videoDto.getTitle()).setDescription(videoDto.getDescription()).setUrl(videoDto.getUrl()).setDurationSeconds(videoDto.getDurationSeconds()).setCreator(creatorMapper.toProto(videoDto.getCreator())).build();
    }
}