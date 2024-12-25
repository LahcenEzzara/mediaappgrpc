package com.lahcencodes.mediaserver.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Table(name = "videos")
@Data // Lombok annotation for getters, setters, toString, equals, hashCode
@NoArgsConstructor // Lombok no-args constructor
@AllArgsConstructor // Lombok all-args constructor
public class VideoEntity {

    @Id
    @GeneratedValue
    private UUID id;

    private String title;

    private String description;

    private String url;

    private int durationSeconds;

    public VideoEntity(String title, String description, String url, int durationSeconds, CreatorEntity creator) {
        this.title = title;
        this.description = description;
        this.url = url;
        this.durationSeconds = durationSeconds;
        this.creator = creator;
    }

    @ManyToOne
    @JoinColumn(name = "creator_id", nullable = false)
    private CreatorEntity creator;
}