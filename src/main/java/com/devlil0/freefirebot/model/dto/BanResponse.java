package com.devlil0.freefirebot.model.dto;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class BanResponse {

    private String status;
    private String msg;
    private BanData data;

}
