package com.lahcencodes.mediaserver.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "creators")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreatorEntity {

    @Id
    private UUID id;

    private String name;

    private String email;

    public CreatorEntity(UUID id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }

    @OneToMany(mappedBy = "creator", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<VideoEntity> videos;
}