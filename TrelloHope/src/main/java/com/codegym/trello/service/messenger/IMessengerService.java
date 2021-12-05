package com.codegym.trello.service.messenger;


import com.codegym.trello.model.MessengerEntity;

public interface IMessengerService {
    void save(MessengerEntity messengerEntity);

    Iterable<MessengerEntity> findAllByBoardId(Long id);
}
