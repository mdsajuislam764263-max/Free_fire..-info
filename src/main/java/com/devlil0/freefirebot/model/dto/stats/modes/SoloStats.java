package com.devlil0.freefirebot.model.dto.stats.modes;

import com.devlil0.freefirebot.model.dto.stats.DetailedStats;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties
public class SoloStats {

    @JsonProperty("detailedstats")
    private DetailedStats detailedStats;

    @JsonProperty("gamesplayed")
    private Integer gamesPlayed;

    private Integer kills;
    private Integer wins;

}
