package com.extensionlab.jinropartybackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.extensionlab.jinropartybackend.model.entity.GameData;

public interface GameDataRepository extends JpaRepository<GameData, String> {
}
