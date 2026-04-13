package com.devlil0.freefirebot.model.dto.stats;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class PlayerCsStats {

    @JsonProperty("detailedstats")
    private DetailedStats detailedStats;

    @JsonProperty("gamesplayed")
    private Integer gamesPlayed;

    private Integer kills;
    private Integer wins;
}
