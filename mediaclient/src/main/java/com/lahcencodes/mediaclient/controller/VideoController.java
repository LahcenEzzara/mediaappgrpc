package com.lahcencodes.mediaclient.controller;

import com.lahcencodes.mediaclient.dto.VideoDto;
import com.lahcencodes.mediaclient.service.VideoServiceClient;
import org.springframework.web.bind.annotation.*;
import com.lahcencodes.proto.Creator;
import com.lahcencodes.proto.UploadVideoRequest;

@RestController
public class VideoController {

    private final VideoServiceClient videoService;

    public VideoController(VideoServiceClient videoService) {
        this.videoService = videoService;
    }

    @PostMapping("addVideo")
    public VideoDto uploadVideo() {
        Creator creator = Creator.newBuilder().setName("Robert C. Martin").setEmail("unclebob@cleancode.com").setId("2024").build();

        UploadVideoRequest request = UploadVideoRequest.newBuilder().setTitle("Clean Code: A Handbook of Agile Software Craftsmanship").setDescription("A guide to producing readable, reusable, and refactorable software in Java.").setUrl("https://www.goodreads.com/book/show/3735293-clean-code").setDurationSeconds(600)
                .setCreator(creator).build();

        VideoDto videoDto = videoService.uploadVideo(request);
        System.out.println(videoDto);
        return videoDto;
    }

}
