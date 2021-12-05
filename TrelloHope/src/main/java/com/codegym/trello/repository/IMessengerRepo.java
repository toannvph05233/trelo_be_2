package com.codegym.trello.repository;

import com.codegym.trello.model.MessengerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IMessengerRepo extends JpaRepository<MessengerEntity, Long> {
    public Iterable<MessengerEntity> findAllByBoardId(Long id);

}
