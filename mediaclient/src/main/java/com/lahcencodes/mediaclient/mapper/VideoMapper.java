package com.lahcencodes.mediaclient.mapper;

import com.lahcencodes.mediaclient.dto.VideoDto;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import com.lahcencodes.proto.Video;

@Component
public class VideoMapper {

    ModelMapper mapper = new ModelMapper();

    public VideoDto fromVideoProtoTovideoDto(Video video) {
        return mapper.map(video, VideoDto.class);
    }
}
