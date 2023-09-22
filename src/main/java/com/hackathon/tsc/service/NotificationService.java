package com.hackathon.tsc.service;

import com.hackathon.tsc.pojo.Notification;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class NotificationService {

    public void sendNotification(Notification notification) {
        log.info("Sent Notification from {}-{} to {}-{}", notification.getSource(), notification.getSourceID(), notification.getTarget(), notification.getTargetID());
    }
}
