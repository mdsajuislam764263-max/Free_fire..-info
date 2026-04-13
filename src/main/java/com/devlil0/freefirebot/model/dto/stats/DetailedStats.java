package com.devlil0.freefirebot.model.dto.stats;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class DetailedStats {

    //private Integer headShotKills;
    private Integer doubleKills;
    private Integer tripleKills;
    private Integer fourKills;
    private Integer assists;
    private Integer damage;
    private Integer deaths;
    private Double distanceTravelled;

    @JsonAlias({"headshotKills", "headShotKills"})
    private Integer headshotKills;
    private Integer headshots;
    private Integer highestKills;
    private Integer knockDown;
    private Integer knockDowns;
    private Integer pickUps;
    private Integer revives;
    private Integer topNTimes;
    private Integer mvpCount;
    private Integer revivals;
    private Double survivalTime;

}
