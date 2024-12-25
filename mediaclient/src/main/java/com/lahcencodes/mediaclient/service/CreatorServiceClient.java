package com.lahcencodes.mediaclient.service;

import com.lahcencodes.mediaclient.dto.CreatorDto;
import com.lahcencodes.mediaclient.dto.VideoDto;
import com.lahcencodes.mediaclient.mapper.CreatorMapper;
import com.lahcencodes.mediaclient.mapper.VideoMapper;
import com.lahcencodes.proto.CreatorIdRequest;
import com.lahcencodes.proto.CreatorServiceGrpc;
import com.lahcencodes.proto.VideoStream;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CreatorServiceClient {

    @GrpcClient("mediaserver")
    private CreatorServiceGrpc.CreatorServiceBlockingStub creatorServiceStub;

    private final CreatorMapper creatorMapper;
    private final VideoMapper videoMapper;

    public CreatorServiceClient(CreatorMapper creatorMapper, VideoMapper videoMapper) {
        this.creatorMapper = creatorMapper;
        this.videoMapper = videoMapper;
    }

    public CreatorDto getCreator(String creatorId) {
        var request = CreatorIdRequest.newBuilder().setId(creatorId).build();
        var creator = creatorServiceStub.getCreator(request);
        return creatorMapper.fromProto(creator);
    }

    public List<VideoDto> getCreatorVideos(String creatorId) {
        var request = CreatorIdRequest.newBuilder().setId(creatorId).build();
        VideoStream videoStream = creatorServiceStub.getCreatorVideos(request);
        return videoStream.getVideosList().stream()
                .map(videoMapper::fromProto)
                .collect(Collectors.toList());
    }
}