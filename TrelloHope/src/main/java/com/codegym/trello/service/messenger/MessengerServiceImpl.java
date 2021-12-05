package com.codegym.trello.service.messenger;


import com.codegym.trello.model.MessengerEntity;
import com.codegym.trello.repository.IMessengerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MessengerServiceImpl implements IMessengerService {
    @Autowired
    IMessengerRepo iMessengerRepo;

    @Override
    public void save(MessengerEntity messengerEntity) {
        iMessengerRepo.save(messengerEntity);
    }

    @Override
    public Iterable<MessengerEntity> findAllByBoardId(Long id) {
        return iMessengerRepo.findAllByBoardId(id);
    }
}
