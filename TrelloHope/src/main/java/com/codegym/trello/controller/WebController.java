package com.codegym.trello.controller;

import com.codegym.trello.model.Messenger;
import com.codegym.trello.model.MessengerEntity;
import com.codegym.trello.model.User;
import com.codegym.trello.service.board.BoardService;
import com.codegym.trello.service.messenger.IMessengerService;
import com.codegym.trello.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
public class WebController {
    @Autowired
    SimpMessagingTemplate simpMessagingTemplate;

    @Autowired
    IMessengerService iMessengerService;
    @Autowired
    private UserService userService;
    @Autowired
    private BoardService boardService;

    @MessageMapping("/chat")
    public void greeting(@Payload Messenger messenger) {
        User user = userService.findByUserName(messenger.getName());
        MessengerEntity messengerEntity = new MessengerEntity();
        messengerEntity.setUser(user);
        messengerEntity.setBoard(boardService.findById(messenger.getIdBoard()).get());
        messengerEntity.setMessenger(messenger.getMessage());
        iMessengerService.save(messengerEntity);

        simpMessagingTemplate.convertAndSend("/topic/public/"+ messenger.getIdBoard(), messenger.getName() +" : " + messenger.getMessage());
    }
}
