package com.devlil0.freefirebot.model.dto.response;


import com.devlil0.freefirebot.model.dto.info.BanData;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class BanResponse {

    private String status;
    private String msg;
    private BanData data;

}
