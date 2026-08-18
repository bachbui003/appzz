package com.appzz.helpdesk.repository;

import com.appzz.helpdesk.model.Notification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotificationRepository extends JpaRepository<Notification, Long> {
}
