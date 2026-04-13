package com.devlil0.freefirebot.model.dto.stats;

import com.devlil0.freefirebot.model.dto.stats.modes.DuoStats;
import com.devlil0.freefirebot.model.dto.stats.modes.QuadStats;
import com.devlil0.freefirebot.model.dto.stats.modes.SoloStats;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class PlayerStatsData {

    private SoloStats solostats;
    private DuoStats duostats;
    private QuadStats quadstats;
    private PlayerCsStats csstats;

}
