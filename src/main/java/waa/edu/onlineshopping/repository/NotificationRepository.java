package waa.edu.onlineshopping.repository;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import waa.edu.onlineshopping.domain.Notification;

@Repository
public interface NotificationRepository extends CrudRepository<Notification,Long> {

}
