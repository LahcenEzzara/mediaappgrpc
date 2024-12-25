package com.lahcencodes.mediaclient.controller;

import com.lahcencodes.mediaclient.dto.VideoDto;
import com.lahcencodes.mediaclient.service.VideoServiceClient;
import com.lahcencodes.mediaclient.mapper.VideoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/videos")
@RequiredArgsConstructor
public class VideoController {

    private final VideoServiceClient videoServiceClient;

    @PostMapping("/upload")
    public ResponseEntity<?> uploadVideo(@RequestBody VideoDto videoDto) {
        try {
            var request = VideoMapper.toProto(videoDto);
            VideoDto response = videoServiceClient.uploadVideo(request);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Failed to upload video: " + e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getVideo(@PathVariable String id) {
        try {
            VideoDto video = videoServiceClient.getVideo(id);
            return ResponseEntity.ok(video);
        } catch (Exception e) {
            return ResponseEntity.status(404).body("Video not found: " + e.getMessage());
        }
    }
}