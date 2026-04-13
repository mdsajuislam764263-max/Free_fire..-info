package com.devlil0.freefirebot.service;

import com.devlil0.freefirebot.model.dto.response.BanResponse;
import com.devlil0.freefirebot.model.dto.response.PlayerInfoResponse;
import com.devlil0.freefirebot.model.dto.response.PlayerStatsResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service

public class FreeFireService {

    //send requisitions for apis
    private WebClient webClient;

    private String playerInfoUrl;

    private String banCheckUrl;

    private String playerStats;

    public FreeFireService(
            @Value("${freefire.api.player-info-url}") String playerInfoUrl,
            @Value("${freefire.api.ban-check-url}") String banCheckUrl
    ) {
        this.webClient = WebClient.builder().build();
        this.playerInfoUrl = playerInfoUrl;
        this.banCheckUrl = banCheckUrl;
    }



    public BanResponse checkBan(String uid){

        try{
                            //get is the type of requisition
            return webClient.get()
                    .uri(banCheckUrl + "?lang=en&uid=" + uid)
                    .header("X-Requested-With", "B6FksShzIgjfrYImLpTsadjS86sddhFH")
                    .header("Referer", "https://ff.garena.com/en/support/")

                    //wait the api return JSON
                    .retrieve()

                    //convert the JSON to BanResponse (dto)
                    .bodyToMono(BanResponse.class)
                    .block(); // wait the message until is come

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public PlayerInfoResponse playerInfoUrl(String uid){
        try{
            return webClient.get()
                    .uri(playerInfoUrl + "/get_player_personal_show?server=BR&uid=" + uid)
                    .retrieve()
                    .bodyToMono(PlayerInfoResponse.class)
                    .block();
        } catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public PlayerStatsResponse playerStatsResponse(String uid, String gamemode){
        try{
            return webClient.get()
                    .uri(playerInfoUrl + "/get_player_stats?server=BR&uid=" + uid + "&gamemode=" + gamemode + "&matchmode=CAREER")
                    .retrieve()
                    .bodyToMono(PlayerStatsResponse.class)
                    .block();
        } catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }


}
