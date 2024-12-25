package com.lahcencodes.mediaserver.service;

import io.grpc.Status;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;
import com.lahcencodes.proto.Creator;
import com.lahcencodes.proto.CreatorIdRequest;
import com.lahcencodes.proto.Video;
import com.lahcencodes.proto.VideoStream;
import com.lahcencodes.proto.CreatorServiceGrpc;
import com.lahcencodes.mediaserver.dao.CreatorDao;
import com.lahcencodes.mediaserver.mapper.CreatorMapper;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@GrpcService
public class CreatorService extends CreatorServiceGrpc.CreatorServiceImplBase {

    private final CreatorDao creatorDao;
    private final CreatorMapper creatorMapper;

    public CreatorService(CreatorDao creatorDao, CreatorMapper creatorMapper) {
        this.creatorDao = creatorDao;
        this.creatorMapper = creatorMapper;
    }

    @Override
    public void getCreator(CreatorIdRequest request, StreamObserver<Creator> responseObserver) {
        try {
            // Fetch creator from the database
            var creatorEntity = creatorDao.getCreatorById(request.getId());

            if (creatorEntity != null) {
                Creator creator = creatorMapper.toProto(creatorEntity);
                responseObserver.onNext(creator);
                responseObserver.onCompleted();
            } else {
                // Handle creator not found
                responseObserver.onError(Status.NOT_FOUND.withDescription("Creator not found with ID: " + request.getId()).asRuntimeException());
            }
        } catch (Exception e) {
            responseObserver.onError(Status.INTERNAL.withDescription("Failed to retrieve creator: " + e.getMessage()).asRuntimeException());
        }
    }

    @Override
    @Transactional
    public void getCreatorVideos(CreatorIdRequest request, StreamObserver<VideoStream> responseObserver) {
        try {
            // Fetch creator and their videos from the database
            var creatorEntity = creatorDao.getCreatorById(request.getId());

            if (creatorEntity != null) {
                List<Video> videos = creatorEntity.getVideos().stream().map(video -> Video.newBuilder().setId(video.getId().toString()).setTitle(video.getTitle()).setDescription(video.getDescription()).setUrl(video.getUrl()).setDurationSeconds(video.getDurationSeconds()).setCreator(creatorMapper.toProto(creatorEntity)).build()).collect(Collectors.toList());

                VideoStream videoStream = VideoStream.newBuilder().addAllVideos(videos).build();
                responseObserver.onNext(videoStream);
                responseObserver.onCompleted();
            } else {
                // Handle creator not found
                responseObserver.onError(Status.NOT_FOUND.withDescription("Creator not found with ID: " + request.getId()).asRuntimeException());
            }
        } catch (Exception e) {
            responseObserver.onError(Status.INTERNAL.withDescription("Failed to retrieve creator videos: " + e.getMessage()).asRuntimeException());
        }
    }
}
