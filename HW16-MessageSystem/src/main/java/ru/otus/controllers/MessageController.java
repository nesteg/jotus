package ru.otus.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import ru.otus.core.service.DBServiceUser;
import ru.otus.dto.UserDto;
import ru.otus.dto.UserListDto;
import ru.otus.messagesystemapp.wsfront.WsFrontService;


@Controller
public class MessageController {
    private static final Logger logger = LoggerFactory.getLogger(MessageController.class);

    private final SimpMessagingTemplate template;
    private final WsFrontService wsFrontService;

    public MessageController(SimpMessagingTemplate template, WsFrontService wsFrontService) {
        this.template = template;
        this.wsFrontService = wsFrontService;
    }

    @MessageMapping("/message.getUsers")
    public void getUserList() {
        logger.info("got message:'getUsers'");
        wsFrontService.getAllUsers(new UserListDto(),userListDto ->
                this.template.convertAndSend("/topic/response.users", userListDto)
        );
    }

    @MessageMapping("/message.getUser.{id}")
    public void getUserById(@DestinationVariable Long id) {
        logger.info("got message:'getUserById' {}", id);
        wsFrontService.getUser(id,userDto ->
                this.template.convertAndSend("/topic/response.user", userDto)
        );

    }

    @MessageMapping("/message.saveUser")
    public void saveUser(UserDto userDto) {
        logger.info("got message:'saveUser'{}", userDto);
        wsFrontService.saveUser(userDto, user ->
                this.template.convertAndSend("/topic/response.user", user));
    }

}
