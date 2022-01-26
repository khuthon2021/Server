package com.example.server.service;

import com.example.server.dto.PictureDto;

public interface FirebaseService {
    public String insertData(PictureDto data) throws Exception;
    public PictureDto selectData(String id) throws Exception;
    public String updateData(PictureDto data) throws Exception;
    public String deleteData(String id) throws Exception;

}
