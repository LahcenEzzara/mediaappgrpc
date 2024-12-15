package com.lahcencodes.mediaclient.service;

import net.devh.boot.grpc.client.inject.GrpcClient;
import com.lahcencodes.mediaclient.dto.VideoDto;
import com.lahcencodes.mediaclient.mapper.VideoMapper;
import org.springframework.stereotype.Service;
import com.lahcencodes.proto.UploadVideoRequest;
import com.lahcencodes.proto.Video;
import com.lahcencodes.proto.VideoServiceGrpc;

@Service
public class VideoServiceClient {

    @GrpcClient("mediaserver")
    VideoServiceGrpc.VideoServiceBlockingStub stub;

    private final VideoMapper mapper;

    public VideoServiceClient(VideoMapper mapper) {
        this.mapper = mapper;
    }

    public VideoDto uploadVideo(UploadVideoRequest videoRequest) {
        Video video = stub.uploadVideo(videoRequest);
        return mapper.fromVideoProtoTovideoDto(video);
    }

}
