package com.devlil0.freefirebot.service;

import com.devlil0.freefirebot.model.dto.response.PlayerStatsResponse;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Locale;


@Component
@Data
public class PlayerStatsService {


    @Autowired
    private FreeFireService freeFireService;

    public String checkPlayerStats(String uid, String gamemode, String mode){

        Locale.setDefault(Locale.US);
        String answerApi = "";
        PlayerStatsResponse player = freeFireService.playerStatsResponse(uid, gamemode);

        if (player != null && gamemode.equals("cs")) {

            answerApi =
                    ("✨  CS RANQUEADO  ✨\n"
                            + "\n\uD83D\uDD2B Assistencias:           " + player.getData().getCsstats().getDetailedStats().getAssists()
                            + "\n\uD83D\uDCA5 Dano Causado:       " + player.getData().getCsstats().getDetailedStats().getDamage()
                            + "\n\uD83D\uDC80 Mortes:                    " + player.getData().getCsstats().getDetailedStats().getDeaths()
                            + "\n\uD83D\uDD2B Abates (HS):            " + player.getData().getCsstats().getDetailedStats().getHeadshotKills()
                            + "\n\uD83D\uDC4A Derrubados:            " + player.getData().getCsstats().getDetailedStats().getKnockDowns()
                            + "\n\n\uD83D\uDD25 Sequências de Abates:\n"
                            + "\n      2\uFE0F⃣ Double Kill:    " + player.getData().getCsstats().getDetailedStats().getDoubleKills()
                            + "\n      3\uFE0F⃣ Triple Kill:        " + player.getData().getCsstats().getDetailedStats().getTripleKills()
                            + "\n      4\uFE0F⃣ Quadra Kill:    " + player.getData().getCsstats().getDetailedStats().getFourKills()
                            + "\n\n\uD83C\uDFC6 Highlights:\n"
                            + "\n      \uD83D\uDC51 MVP:                     " + player.getData().getCsstats().getDetailedStats().getMvpCount()
                            + "\n      ❤\uFE0F Reanimações:     " + player.getData().getCsstats().getDetailedStats().getRevivals()
                            + "\n\n\uD83C\uDFAE  RESUMO DE PARTIDAS:\n"
                            + "\n\uD83C\uDFAF Partidas Jogadas:      " + player.getData().getCsstats().getGamesPlayed()
                            + "\n\uD83D\uDD2B Abates Totais:      " + player.getData().getCsstats().getKills()
                            + "\n\uD83C\uDFC6 Vitórias:                " + player.getData().getCsstats().getWins()

                    );





        } else if (player != null && gamemode.equals("br")){

            if (mode.equals("solo")) {
                answerApi =
                        ("✨  BR RANQUEADO SOLO  ✨\n"
                                + "\n💥 Dano Total:          " + player.getData().getSolostats().getDetailedStats().getDamage()
                                + "\n💀 Mortes:                " + player.getData().getSolostats().getDetailedStats().getDeaths()
                                + "\n\uD83D\uDEB6\u200D♂\uFE0F Andou:  " + String.format("%.2f", player.getData().getSolostats().getDetailedStats().getDistanceTravelled()) +  "km"
                                + "\n\n\uD83D\uDD2B COMBATE:\n"
                                + "\n🎯 Headshots:             " + player.getData().getSolostats().getDetailedStats().getHeadshots()
                                + "\n💀 Abates HS:             " + player.getData().getSolostats().getDetailedStats().getHeadshotKills()
                                + "\n🔥 Max Abates:           " + player.getData().getSolostats().getDetailedStats().getHighestKills()
                                + "\n📦 Itens Coletados:    " + player.getData().getSolostats().getDetailedStats().getPickUps()
                                + "\n\n❤\uFE0F SOBREVIVÊNCIA:\n"
                                + "\n⏱️ Tempo:      " + String.format("%.2f", player.getData().getSolostats().getDetailedStats().getSurvivalTime() / 60) + "h"
                                + "\n\uD83C\uDFC6 Top 10:      " + player.getData().getSolostats().getDetailedStats().getTopNTimes() + " vezes"
                                + "\n\n\uD83C\uDFAE  RESUMO DE PARTIDAS\n"
                                + " \n  🎯 Partidas:           " + player.getData().getSolostats().getGamesPlayed()
                                + "\n  🔫 Total Abates:     " + player.getData().getSolostats().getKills()
                                + "\n   ✔\uFE0F Vitórias:          " + player.getData().getSolostats().getWins()
                        );
            } else if (mode.equals("duo")){
                answerApi =
                        ("✨  BR RANQUEADO DUO  ✨\n"
                                + "\n💥 Dano Total:          " + player.getData().getDuostats().getDetailedStats().getDamage()
                                + "\n💀 Mortes:                " + player.getData().getDuostats().getDetailedStats().getDeaths()
                                + "\n\uD83D\uDEB6\u200D♂\uFE0F Andou:  " + String.format("%.2f", player.getData().getDuostats().getDetailedStats().getDistanceTravelled()) +  "km"
                                + "\n\n\uD83D\uDD2B COMBATE:\n"
                                + "\n🎯 Headshots:           " + player.getData().getDuostats().getDetailedStats().getHeadshots()
                                + "\n💀 Abates HS:            " + player.getData().getDuostats().getDetailedStats().getHeadshotKills()
                                + "\n🔥 Max Abates:         " + player.getData().getDuostats().getDetailedStats().getHighestKills()
                                + "\n\uD83E\uDDCE\u200D♂\uFE0F Derrubados:         " + player.getData().getDuostats().getDetailedStats().getKnockDown()
                                + "\n📦 Itens Coletados:  " + player.getData().getDuostats().getDetailedStats().getPickUps()
                                + "\n\uD83C\uDFE5 Reviveu:                " + player.getData().getDuostats().getDetailedStats().getRevives()
                                + "\n\n❤\uFE0F SOBREVIVÊNCIA:\n"
                                + "\n⏱️ Tempo:        " + String.format("%.2f", player.getData().getDuostats().getDetailedStats().getSurvivalTime() / 60) + "h"
                                + "\n\uD83C\uDFC6 Top 10:    " + player.getData().getDuostats().getDetailedStats().getTopNTimes() + " vezes"
                                + "\n\n\uD83C\uDFAE  RESUMO DE PARTIDAS\n"
                                + " \n  🎯 Partidas:           " + player.getData().getDuostats().getGamesPlayed()
                                + "\n  🔫 Total Abates:     " + player.getData().getDuostats().getKills()
                                + "\n   ✔\uFE0F Vitórias:          " + player.getData().getDuostats().getWins()
                        );
            } else if (mode.equals("squad")){

                answerApi = ("✨  BR RANQUEADO SQUAD  ✨\n"
                        + "\n💥 Dano Total:          " + player.getData().getQuadstats().getDetailedStats().getDamage()
                        + "\n💀 Mortes:                " + player.getData().getQuadstats().getDetailedStats().getDeaths()
                        + "\n\uD83D\uDEB6\u200D♂\uFE0F Andou:  " + String.format("%.2f", player.getData().getQuadstats().getDetailedStats().getDistanceTravelled()) +  "km"
                        + "\n\n\uD83D\uDD2B COMBATE:\n"
                        + "\n🎯 Headshots:           " + player.getData().getQuadstats().getDetailedStats().getHeadshots()
                        + "\n💀 Abates HS:              " + player.getData().getQuadstats().getDetailedStats().getHeadshotKills()
                        + "\n🔥 Max Abates:         " + player.getData().getQuadstats().getDetailedStats().getHighestKills()
                        + "\n\uD83E\uDDCE\u200D♂\uFE0F Derrubados:         " + player.getData().getQuadstats().getDetailedStats().getKnockDown()
                        + "\n📦 Itens Coletados:  " + player.getData().getQuadstats().getDetailedStats().getPickUps()
                        + "\n\uD83C\uDFE5 Reviveu:                " + player.getData().getQuadstats().getDetailedStats().getRevives()
                        + "\n\n❤\uFE0F SOBREVIVÊNCIA:\n"
                        + "\n⏱️ Tempo:      " + String.format("%.2f", player.getData().getQuadstats().getDetailedStats().getSurvivalTime() / 60) + "h"
                        + "\n\uD83C\uDFC6 Top 10:          " + player.getData().getQuadstats().getDetailedStats().getTopNTimes() + " vezes"
                        + "\n\n\uD83C\uDFAE  RESUMO DE PARTIDAS\n"
                        + " \n  🎯 Partidas:           " + player.getData().getQuadstats().getGamesPlayed()
                        + "\n  🔫 Total Abates:     " + player.getData().getQuadstats().getKills()
                        + "\n   ✔\uFE0F Vitórias:          " + player.getData().getQuadstats().getWins()
                );
            }
        }
        else{
            return null;
        }
        return answerApi;
    }


}
