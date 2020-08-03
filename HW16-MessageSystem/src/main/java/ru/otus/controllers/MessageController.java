package ru.otus.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.util.HtmlUtils;
import ru.otus.core.model.User;
import ru.otus.core.service.DBServiceUser;
import ru.otus.dto.UserDto;
import ru.otus.dto.UserListDto;
import ru.otus.messagesystemapp.wsfront.WsFrontService;


import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Controller
public class MessageController {
    private static final Logger logger = LoggerFactory.getLogger(MessageController.class);

    @Autowired
    private SimpMessagingTemplate template;

    @Autowired
    private DBServiceUser dbserviceUser;

    @Autowired
    private WsFrontService wsFrontService;


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
