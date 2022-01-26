package com.example.server.service;

import com.example.server.dto.FirebaseCountDto;
import com.example.server.dto.FirebaseDbDto;
import com.example.server.dto.PictureDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.firebase.database.*;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class FirebaseServiceImplement implements FirebaseService{
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference ref;
    private String currentSnapshot;

    private final CloseableHttpClient httpClient= HttpClients.createDefault();
    ObjectMapper objectMapper=new ObjectMapper();


    public int getCount(String u_id,String nowData) throws IOException {
        int ret=0;
        HttpGet request = new HttpGet("https://khuthon-a8366-default-rtdb.firebaseio.com/users/"+u_id+ ".json");


        try(CloseableHttpResponse response = httpClient.execute(request)){
            System.out.println("===Post Request sent to Firebase : "+response);
            HttpEntity entity=response.getEntity();
            String body= EntityUtils.toString(entity);
            if(body.equals("null")){
                System.out.println("새로운 회원 추가");
                ret=0;
            }
            else
            {
                System.out.println("이미 존재하는 회원 업데이트");
                FirebaseDbDto firebaseDbDto=objectMapper.readValue(body,FirebaseDbDto.class);
                String databaseDate=firebaseDbDto.getDate();
                if(databaseDate.equals(nowData)){
                    ret=firebaseDbDto.getCount();
                }
                else
                {
                    ret=0;
                }
            }
        }catch (Exception e){
            System.out.println("Exception = "+e);
        }
        return ret;
    }
    public void updateCount(DatabaseReference ref){
        System.out.println("접근 DB URL = "+ref);
        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                FirebaseDbDto value=snapshot.getValue(FirebaseDbDto.class);
                int _count=value.getCount();
                String _id=value.getU_id();
                String date=value.getDate();

                DatabaseReference countRef= firebaseDatabase.getReference("count");
                countRef=countRef.child(_id);
                countRef=countRef.child(date);

                FirebaseCountDto data=new FirebaseCountDto(_count);

                countRef.setValueAsync(data);
            }

            @Override
            public void onCancelled(DatabaseError error) {

            }
        });
    }
    @Override
    public String insertData(PictureDto data) throws IOException {
        firebaseDatabase=FirebaseDatabase.getInstance();
        ref=firebaseDatabase.getReference("users");
        String id=data.getId();
        String nowDate=data.getNowDate();
        String u_id=data.getToken();

        int count=getCount(u_id,nowDate);

        DatabaseReference newRef=ref.child(u_id);

        FirebaseDbDto firebaseDbDto=new FirebaseDbDto(id,count+1,u_id,nowDate);

        newRef.setValueAsync(firebaseDbDto);

        updateCount(newRef);

        return id;
    }

    @Override
    public PictureDto selectData(String id) throws Exception {
        return null;
    }

    @Override
    public String updateData(PictureDto data) throws Exception {
        return null;
    }

    @Override
    public String deleteData(String id) throws Exception {
        return null;
    }


}
