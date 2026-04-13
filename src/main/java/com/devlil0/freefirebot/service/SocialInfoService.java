package com.devlil0.freefirebot.service;

import com.devlil0.freefirebot.model.dto.response.PlayerInfoResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component

public class SocialInfoService {

    @Autowired
    private FreeFireService freeFireService;

    public String socialInfoRequest(String uid){


        PlayerInfoResponse player = freeFireService.playerInfoUrl(uid);
        String answerApi = "";


        if (player != null){

            if (player.getSocialInfo().getModeprefer() != null){

                if (player.getSocialInfo().getModeprefer().contains("CS")){
                    player.getSocialInfo().setModeprefer("CS RANKED");
                } else {
                    player.getSocialInfo().setModeprefer("BR RANKED");
                }
            } else {
                player.getSocialInfo().setModeprefer("NENHUMA PREFERÊNCIA");
            }

            if (player.getSocialInfo().getTimeactive().contains("NIGHT")){
                player.getSocialInfo().setTimeactive("NOITE");
            } else if (player.getSocialInfo().getTimeactive().contains("AFTERNOON")){
                player.getSocialInfo().setTimeactive("TARDE");
            } else if (player.getSocialInfo().getTimeactive().contains("MORNING")){
                player.getSocialInfo().setTimeactive("MANHÃ");
            } else {
                player.getSocialInfo().setTimeactive("FLEXÍVEL");
            }

            if ("GENDERMALE".equals(player.getSocialInfo().getGender())){
                player.getSocialInfo().setGender("MASCULINO");
            }
            else{
                player.getSocialInfo().setGender("FEMININO");
            }

            answerApi = "✨ SOCIAL INFO ✨\n"
                    + "\n\uD83C\uDFAE Modo de preferência: " + player.getSocialInfo().getModeprefer()
                    + "\n\uD83C\uDF19 Tempo de atividade: " + player.getSocialInfo().getTimeactive()
                    + "\n\uD83D\uDCDD Bio: " + player.getSocialInfo().getSignature()
                    + "\n\uD83D\uDEBA Gênero: " + player.getSocialInfo().getGender()
                    + "\n⭐ Reputação: " + player.getCreditScoreInfo().getCreditscore() + " ❤\uFE0F";


        }
        else {
            return null;
        }

        return answerApi;
    }
}
