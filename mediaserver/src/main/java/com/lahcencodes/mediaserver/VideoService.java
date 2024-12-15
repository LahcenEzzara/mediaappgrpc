package com.lahcencodes.mediaserver;

import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;
import com.lahcencodes.proto.UploadVideoRequest;
import com.lahcencodes.proto.Video;
import com.lahcencodes.proto.VideoServiceGrpc;

@GrpcService
public class VideoService extends VideoServiceGrpc.VideoServiceImplBase {

    @Override
    public void uploadVideo(UploadVideoRequest request, StreamObserver<Video> responseObserver) {

        Video video = Video.newBuilder().setId("1234").setTitle(request.getTitle()).setDescription(request.getDescription()).setUrl(request.getUrl()).setDurationSeconds(request.getDurationSeconds()).setCreator(request.getCreator()).build();
        responseObserver.onNext(video);
        responseObserver.onCompleted();

    }
}
