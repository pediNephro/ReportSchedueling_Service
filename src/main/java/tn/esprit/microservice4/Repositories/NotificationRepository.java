package tn.esprit.microservice4.Repositories;

import tn.esprit.microservice4.Entities.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, Long> {
    List<Notification> findByReceiverIdOrderByCreatedAtDesc(Long receiverId);
    List<Notification> findByReceiverIdAndSeenFalse(Long receiverId);
}
