package com.codegym.trello.controller;

import com.codegym.trello.model.Notification;
import com.codegym.trello.model.User;
import com.codegym.trello.service.notification.INotificationService;
import com.codegym.trello.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
public class NotificationControllerSocket {

    @Autowired
    SimpMessagingTemplate simpMessagingTemplate;

    @Autowired
    private INotificationService notificationService;


    @MessageMapping("/notification")
    public void create(@Payload Notification notification) {
        notificationService.save(notification);
        simpMessagingTemplate.convertAndSend("/topic/public/"+ notification.getIdBoard(), notification);
    }
}
