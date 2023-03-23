package com.supabase.index.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.firebase.messaging.FirebaseMessagingException;
import com.supabase.index.service.FirebaseMessagingService;

@RestController
public class NotificationController {

	private final FirebaseMessagingService firebaseMessagingService;

    public NotificationController(FirebaseMessagingService firebaseMessagingService) {
        this.firebaseMessagingService = firebaseMessagingService;
    }

    @GetMapping("/sendNotification")
    public String sendNotification() {
        try {
        	firebaseMessagingService.sendNotification("Test Title", "Test Message");
            return "Notification sent";
        } catch (FirebaseMessagingException e) {
            e.printStackTrace();
            return "Failed to send notification: " + e.getMessage();
        }
    }
}
