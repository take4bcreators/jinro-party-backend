package com.extensionlab.jinropartybackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.extensionlab.jinropartybackend.model.entity.NewGameData;

public interface NewGameDataRepository extends JpaRepository<NewGameData, String> {
}
