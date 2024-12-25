package com.lahcencodes.mediaclient.dto;

import lombok.Data;

@Data
public class VideoDto {
    private String id;
    private String title;
    private String description;
    private String url;
    private int durationSeconds;
    private CreatorDto creator;
}