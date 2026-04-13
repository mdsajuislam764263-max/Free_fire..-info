package com.devlil0.freefirebot.model.dto.response;

import com.devlil0.freefirebot.model.dto.stats.PlayerStatsData;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class PlayerStatsResponse {

    private PlayerStatsData data;
    private boolean success;
}
