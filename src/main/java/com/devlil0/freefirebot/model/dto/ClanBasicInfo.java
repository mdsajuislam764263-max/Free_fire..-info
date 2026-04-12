package com.devlil0.freefirebot.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ClanBasicInfo {

    private String clanname;
    private String membernum;

}
