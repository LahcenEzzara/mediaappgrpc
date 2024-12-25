package com.lahcencodes.mediaclient.mapper;

import com.lahcencodes.mediaclient.dto.CreatorDto;
import com.lahcencodes.proto.Creator;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class CreatorMapper {

    private final ModelMapper modelMapper = new ModelMapper();

    public CreatorDto fromProto(Creator creator) {
        return modelMapper.map(creator, CreatorDto.class);
    }

    public Creator toProto(CreatorDto creatorDto) {
        return Creator.newBuilder()
                .setId(creatorDto.getId())
                .setName(creatorDto.getName())
                .setEmail(creatorDto.getEmail())
                .build();
    }
}