package com.example.server.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.firebase.FirebaseApp;
import com.google.firebase.messaging.*;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import lombok.RequiredArgsConstructor;
import okhttp3.*;
import org.springframework.http.*;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.client.RestTemplate;




@Component
@RequiredArgsConstructor
public class FirebaseCloudMsgService {
//    private final String url="https://fcm.googleapis.com/vi/projects/khuthon-a8366/fcm/send";
//    private final ObjectMapper objectMapper;

    public void sendMessage(String token,String title,String body) throws FirebaseMessagingException{

        Aps aps= Aps.builder().setSound("default").build();
        ApnsConfig apnsConfig= ApnsConfig.builder().setAps(aps).build();

        String imageUrl="https://image.tmdb.org/t/p/w300/670x9sf0Ru8y6ezBggmYudx61yB.jpg";
        Notification notification=Notification.builder().setTitle(title).setBody(body).setBody(imageUrl).build();

        String registrationToken = token;
        Message message=Message.builder()
                .setToken(registrationToken)
                .setApnsConfig(apnsConfig)
                .putData("title",title)
                .putData("body",body)
                .putData("imageUrl",imageUrl)
                .setNotification(notification)
                .build();

        String response = FirebaseMessaging.getInstance().send(message);

//        String response = FirebaseMessaging.getInstance().send(message);

        System.out.println("=====Notification====="+response);
    }
}

