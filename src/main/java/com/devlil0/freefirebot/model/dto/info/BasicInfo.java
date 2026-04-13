package com.devlil0.freefirebot.model.dto.info;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class BasicInfo {

    private String accountid;
    private String nickname;
    private String region;
    private int level;
    private int rank;
    private int liked;
    private String lastloginat;
    private String createat;




}
