package waa.edu.onlineshopping.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import reactor.core.publisher.Flux;
import waa.edu.onlineshopping.domain.Notification;
import waa.edu.onlineshopping.dto.NotifyHelper;
import waa.edu.onlineshopping.service.NotificationService;

import java.util.ArrayList;
import java.util.List;

@Controller
public class NotificationController {
    @Autowired
    private NotificationService notificationService;

    @Secured("ROLE_BUYER")
       @GetMapping("/buyer/notification")
    public String getNotification(){
        return "buyer/notify";
       }


    @GetMapping(path = "/buyer/notify",
            produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<NotifyHelper> feed() {
        return notificationService.findAll();
    }
}
