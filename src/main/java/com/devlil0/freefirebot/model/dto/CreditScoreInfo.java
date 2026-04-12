package com.devlil0.freefirebot.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class CreditScoreInfo {

    private String creditscore;
    private String rewardstate;

}
