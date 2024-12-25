package com.lahcencodes.mediaclient.service;

import com.lahcencodes.mediaclient.dto.VideoDto;
import com.lahcencodes.mediaclient.mapper.VideoMapper;
import com.lahcencodes.proto.VideoIdRequest;
import com.lahcencodes.proto.UploadVideoRequest;
import com.lahcencodes.proto.VideoServiceGrpc;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.stereotype.Service;

@Service
public class VideoServiceClient {

    @GrpcClient("mediaserver")
    private VideoServiceGrpc.VideoServiceBlockingStub videoServiceStub;

    private final VideoMapper videoMapper;

    public VideoServiceClient(VideoMapper videoMapper) {
        this.videoMapper = videoMapper;
    }

    public VideoDto uploadVideo(UploadVideoRequest request) {
        var video = videoServiceStub.uploadVideo(request);
        return videoMapper.fromProto(video);
    }

    public VideoDto getVideo(String videoId) {
        var request = VideoIdRequest.newBuilder().setId(videoId).build();
        var video = videoServiceStub.getVideo(request);
        return videoMapper.fromProto(video);
    }
}