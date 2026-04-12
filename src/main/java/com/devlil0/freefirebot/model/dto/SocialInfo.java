package com.devlil0.freefirebot.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class SocialInfo {

    private String signature;
    private String gender;
    private String timeactive;
    private String rankshow;

}
