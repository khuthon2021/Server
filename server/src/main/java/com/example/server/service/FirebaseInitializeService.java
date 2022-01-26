package com.example.server.service;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.io.FileInputStream;

@Configuration
public class FirebaseInitializeService {
    @PostConstruct
    public void initialize(){
        try{
            FileInputStream refreshToken = new FileInputStream("./src/main/resources/khuthon-a8366-firebase-adminsdk-de8ke-7005a77c42.json");

            FirebaseOptions options = new FirebaseOptions.Builder()
                    .setCredentials(GoogleCredentials.fromStream(refreshToken))
                    .setDatabaseUrl("https://khuthon-a8366-default-rtdb.firebaseio.com/")
                    .build();

            FirebaseApp.initializeApp(options);

        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}
