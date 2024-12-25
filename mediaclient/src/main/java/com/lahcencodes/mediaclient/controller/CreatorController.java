package com.lahcencodes.mediaclient.controller;

import com.lahcencodes.mediaclient.dto.CreatorDto;
import com.lahcencodes.mediaclient.dto.VideoDto;
import com.lahcencodes.mediaclient.service.CreatorServiceClient;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/creators")
@RequiredArgsConstructor
public class CreatorController {

    private final CreatorServiceClient creatorServiceClient;

    @GetMapping("/{id}")
    public ResponseEntity<?> getCreator(@PathVariable String id) {
        try {
            CreatorDto creator = creatorServiceClient.getCreator(id);
            return ResponseEntity.ok(creator);
        } catch (Exception e) {
            return ResponseEntity.status(404).body("Creator not found: " + e.getMessage());
        }
    }

    @GetMapping("/{id}/videos")
    public ResponseEntity<?> getCreatorVideos(@PathVariable String id) {
        try {
            List<VideoDto> videos = creatorServiceClient.getCreatorVideos(id);
            return ResponseEntity.ok(videos);
        } catch (Exception e) {
            return ResponseEntity.status(404).body("Failed to retrieve videos: " + e.getMessage());
        }
    }
}