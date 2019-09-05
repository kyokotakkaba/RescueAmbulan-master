package com.example.abyandafa.rescueambulance.baseclass;

import com.example.abyandafa.rescueambulance.service.APIClient;
import com.example.abyandafa.rescueambulance.service.BaseRestService;
import com.google.gson.Gson;

public class BaseModel {
    protected APIClient apiClient;
    protected Gson gson;

    public BaseModel() {
        apiClient = BaseRestService.initializeRetrofit().create(APIClient.class);
        gson = new Gson();
    }

}
