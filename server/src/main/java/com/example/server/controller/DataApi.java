package com.example.server.controller;

import com.example.server.dto.PictureDto;
import com.example.server.service.DataApiService;
import com.example.server.service.FirebaseCloudMsgService;
import com.example.server.service.FirebaseService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@Data
public class DataApi {

    @Autowired
    FirebaseService firebaseService;

    @Autowired
    FirebaseCloudMsgService firebaseCloudMsgService;

    private final DataApiService dataApiService=new DataApiService();

    private PictureDto data=new PictureDto();

    @GetMapping("/data")
    public String getData(){
        return dataApiService.getData();
    }

    @PostMapping("/data")
    public void postData(@RequestBody PictureDto pictureDto) throws Exception {
        System.out.println("\n ====== post request recevied from python data ====\n");
        data=dataApiService.setData(pictureDto);

        String isClose=dataApiService.getResult();
        if(isClose.equals("yes"))
        {
            firebaseCloudMsgService.sendMessage(
                    TOKENKEY,
                    "정신 차리세요!!", "제발 눈을 떠요!");
            String insertResult=firebaseService.insertData(data);
        }

    }



}
