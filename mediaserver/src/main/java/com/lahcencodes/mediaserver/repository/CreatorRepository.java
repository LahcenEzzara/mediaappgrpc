package com.lahcencodes.mediaserver.repository;

import com.lahcencodes.mediaserver.entity.CreatorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CreatorRepository extends JpaRepository<CreatorEntity, UUID> {
}