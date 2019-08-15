package waa.edu.onlineshopping.service.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import waa.edu.onlineshopping.domain.Notification;
import waa.edu.onlineshopping.dto.NotifyHelper;
import waa.edu.onlineshopping.repository.NotificationRepository;
import waa.edu.onlineshopping.service.NotificationService;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
@Service
public class NotficationServiceImpl implements NotificationService {

   @Autowired
    NotificationRepository notificationRepository;

    @Override
    public Flux<NotifyHelper> findAll() {
        List<NotifyHelper> notifyHelpers=new ArrayList<>();
        int i=1;
        for(Notification notification:notificationRepository.findAll()){
            NotifyHelper notifyHelper=new NotifyHelper();
            notifyHelper.setDescription(notification.getDescription());
            notifyHelper.setId(notification.getProduct().getId());
            notifyHelper.setCompany(notification.getCompany().getName());
            notifyHelper.setProductName(notification.getProduct().getName());
            notifyHelper.setSize(i++);
            System.out.println(notifyHelper);
            notifyHelpers.add(notifyHelper);

        }
        return Flux.fromIterable(notifyHelpers) ;
    }

    @Override
    public Notification save(Notification notification) {
        return notificationRepository.save(notification);
    }
}
