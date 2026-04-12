package com.devlil0.freefirebot.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class PlayerInfoResponse {

    @JsonProperty("basicinfo")
    private BasicInfo basicInfo;

    @JsonProperty("socialinfo")
    private SocialInfo socialInfo;

    @JsonProperty("clanbasicinfo")
    private ClanBasicInfo clanBasicInfo;

    @JsonProperty("creditscoreinfo")
    private CreditScoreInfo creditScoreInfo;


}
