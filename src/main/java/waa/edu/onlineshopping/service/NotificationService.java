package waa.edu.onlineshopping.service;

import reactor.core.publisher.Flux;
import waa.edu.onlineshopping.domain.Notification;
import waa.edu.onlineshopping.dto.NotifyHelper;

import java.util.List;

public interface NotificationService {

           Flux<NotifyHelper> findAll();
            Notification save(Notification notification);


}
