package com.devlil0.freefirebot.service;

import com.devlil0.freefirebot.helper.FormatTimeStamp;
import com.devlil0.freefirebot.model.dto.BanResponse;
import com.devlil0.freefirebot.model.dto.PlayerInfoResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BanResponseService {

    private final FreeFireService freeFireService;

    public String checkBan(String uid) {
        BanResponse ban = freeFireService.checkBan(uid);
        PlayerInfoResponse player = freeFireService.playerInfoUrl(uid);

        String answer = "";

        if (ban != null && player != null){
            Integer createdAt = Integer.parseInt(player.getBasicInfo().getCreateat());

            Integer banEnd = ban.getData().getPeriod() - createdAt;

            if (ban.getData().getIsBanned() == 1) {

                if (banEnd < 1451606400) {
                    answer = "\uD83D\uDEAB Conta BANIDA PERMANENTEMENTE!\n"
                            + "\n\uD83D\uDC64 Nome:" + player.getBasicInfo().getNickname()
                            + "\n\uD83C\uDD94 ID: " + uid
                            + "\n\uD83D\uDC80 Início do banimento: " + FormatTimeStamp.formatTimestamp(player.getBasicInfo().getCreateat());
                } else {
                    answer = "⏳ Conta BANIDA TEMPORARIAMENTE!\n"
                            + "\n\uD83D\uDC64 Nome:" + player.getBasicInfo().getNickname()
                            + "\n\uD83C\uDD94 ID: " + uid
                            + "\nInício do banimento: " + FormatTimeStamp.formatTimestamp(player.getBasicInfo().getCreateat())
                            + "\nFim do banimento: " + FormatTimeStamp.formatTimeStampWithInteger(banEnd);
                }


            } else if (ban.getData().getIsBanned() == 0) {

                answer = "✅ Conta ATIVA!\n"
                        + "\n\uD83D\uDC64 Nome: " + player.getBasicInfo().getNickname()
                        + "\n\uD83C\uDD94 ID: " + uid;
            }
        } else {
            return null;
        }

        return answer;
    }
}
