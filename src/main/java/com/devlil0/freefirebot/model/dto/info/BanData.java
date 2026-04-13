package com.devlil0.freefirebot.model.dto.info;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data

//this json ignore all properties that i dont want implement
@JsonIgnoreProperties(ignoreUnknown = true)
public class BanData {

    @JsonProperty("is_banned")
    private Integer isBanned;
    private Integer period;

}
