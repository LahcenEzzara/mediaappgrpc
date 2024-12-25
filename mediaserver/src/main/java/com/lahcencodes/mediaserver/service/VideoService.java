package com.lahcencodes.mediaserver.service;

import io.grpc.Status;
import io.grpc.stub.StreamObserver;
import lombok.RequiredArgsConstructor;
import net.devh.boot.grpc.server.service.GrpcService;
import com.lahcencodes.proto.UploadVideoRequest;
import com.lahcencodes.proto.Video;
import com.lahcencodes.proto.VideoIdRequest;
import com.lahcencodes.proto.VideoServiceGrpc;
import com.lahcencodes.mediaserver.dao.CreatorDao;
import com.lahcencodes.mediaserver.dao.VideoDao;
import com.lahcencodes.mediaserver.entity.CreatorEntity;
import com.lahcencodes.mediaserver.entity.VideoEntity;
import com.lahcencodes.mediaserver.mapper.VideoMapper;

import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@GrpcService
@RequiredArgsConstructor // Lombok annotation for constructor injection
@Transactional
public class VideoService extends VideoServiceGrpc.VideoServiceImplBase {

    private final VideoDao videoDao;
    private final CreatorDao creatorDao;
    private final VideoMapper videoMapper;

    @Override
    public void uploadVideo(UploadVideoRequest request, StreamObserver<Video> responseObserver) {
        try {
            // Fetch or create the creator
            CreatorEntity creatorEntity = creatorDao.getCreatorById(request.getCreator().getId());
            if (creatorEntity == null) {
                creatorEntity = new CreatorEntity(
                        UUID.fromString(request.getCreator().getId()),
                        request.getCreator().getName(),
                        request.getCreator().getEmail()
                );
                creatorEntity = creatorDao.saveCreator(creatorEntity);
            }

            // Create the video
            VideoEntity videoEntity = new VideoEntity(
                    request.getTitle(),
                    request.getDescription(),
                    request.getUrl(),
                    request.getDurationSeconds(),
                    creatorEntity
            );

            // Save the video
            videoEntity = videoDao.saveVideo(videoEntity);

            // Respond with the created video
            Video videoResponse = videoMapper.toProto(videoEntity);
            responseObserver.onNext(videoResponse);
            responseObserver.onCompleted();
        } catch (Exception e) {
            responseObserver.onError(Status.INTERNAL.withDescription("Failed to upload video: " + e.getMessage()).asRuntimeException());
        }
    }

    @Override
    public void getVideo(VideoIdRequest request, StreamObserver<Video> responseObserver) {
        try {
            VideoEntity videoEntity = videoDao.getVideoById(request.getId());
            if (videoEntity != null) {
                Video video = videoMapper.toProto(videoEntity);
                responseObserver.onNext(video);
                responseObserver.onCompleted();
            } else {
                responseObserver.onError(Status.NOT_FOUND.withDescription("Video not found with ID: " + request.getId()).asRuntimeException());
            }
        } catch (Exception e) {
            responseObserver.onError(Status.INTERNAL.withDescription("Failed to retrieve video: " + e.getMessage()).asRuntimeException());
        }
    }
}