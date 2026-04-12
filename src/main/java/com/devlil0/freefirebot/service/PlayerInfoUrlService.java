package com.devlil0.freefirebot.service;

import com.devlil0.freefirebot.helper.FormatTimeStamp;
import com.devlil0.freefirebot.model.dto.PlayerInfoResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PlayerInfoUrlService {

    private final FreeFireService freeFireService;

    public String checkPlayerInfo(String uid){

        PlayerInfoResponse player = freeFireService.playerInfoUrl(uid);
        String answer = "";

        if (player != null){

             answer =
                    "✨ PERFIL DO JOGADOR ✨"
                            + "\n\uD83D\uDC64 Nome: " + player.getBasicInfo().getNickname()
                            + "\n\uD83C\uDD94 ID: " + uid
                            + "\n\uD83C\uDF0E Região: " + player.getBasicInfo().getRegion()
                            + "\n❤\uFE0F Likes: " + player.getBasicInfo().getLiked()
                            + "\n\uD83C\uDFAE Level: " + player.getBasicInfo().getLevel()
                            + "\n\uD83C\uDFC6 Rank: " + player.getBasicInfo().getRank()
                            + "\n\uD83D\uDD52 Último Login: " + FormatTimeStamp.formatTimestamp(player.getBasicInfo().getLastloginat())
                            + "\n\uD83D\uDCC5 Data de Criação: " + FormatTimeStamp.formatTimestamp(player.getBasicInfo().getCreateat());

        } else {
            return null;
        }

        return answer;
    }
}
