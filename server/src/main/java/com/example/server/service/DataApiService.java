package com.example.server.service;

import com.example.server.dto.PictureDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class DataApiService {
    private PictureDto data=new PictureDto();

    // GET  data
    public String getData(){
//        System.out.println("get >>> "+ photo);
        JsonObject jsonObject=new JsonObject();
        jsonObject.addProperty("token",data.getToken());

        return jsonObject.toString();
    }

    // SET  data
    public PictureDto setData(PictureDto pictureDto){
        System.out.println("set Data >>> " + pictureDto);
        data.setId(pictureDto.getId());
        data.setToken(pictureDto.getToken());
        data.setNowDate(pictureDto.getNowDate());
        data.setIsClose(pictureDto.getIsClose());
        return data;
    }

    // python 으로 사진만 POST
    // 감고 있는지 아닌지 확인
    public String getResult()  {
        // result
        String result="open";

        // URL 설정
        String workerIP = "localhost";
        String workerPort = "5000";
        String path = "/opencv";
        String url = "http://"+workerIP+":"+workerPort+path;

        System.out.println("\n==============flask==============\n");

        // Body 설정 (JSON 형태)
        JsonObject params=new JsonObject();
        //params.addProperty("name",data.getName());
        //params.addProperty("nowDate",data.getNowDate());
        //params.addProperty("nowTime",data.getNowTime());
        params.addProperty("isClose",data.getIsClose());


        // Header 설정 ,TYPE=json
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        // 요청하기 위해 header와 body 합치기
        HttpEntity<String> entity=new HttpEntity<String>(params.toString(),headers);

        // POST 요청
        RestTemplate template = new RestTemplate();
        ResponseEntity<String> response = template.exchange(
                url,
                HttpMethod.POST,
                entity,
                String.class
        );
        System.out.println("response.getBody() = " + response.getBody());

        JsonParser jsonParser=new JsonParser();
        JsonObject jsonObject=(JsonObject) jsonParser.parse(response.getBody()).getAsJsonObject();

        String is_close=jsonObject.get("is_close").getAsString();

        System.out.println(data.getId() + " is_close : "+is_close);
        result=is_close;

        return result;
    }
}
