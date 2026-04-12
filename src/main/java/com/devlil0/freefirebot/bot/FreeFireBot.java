package com.devlil0.freefirebot.bot;

import com.devlil0.freefirebot.service.BanResponseService;
import com.devlil0.freefirebot.service.FreeFireService;
import com.devlil0.freefirebot.service.PlayerInfoUrlService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Component
public class FreeFireBot extends TelegramLongPollingBot {

    private final FreeFireService freeFireService;

    @Autowired
    private BanResponseService banResponseService;
    @Autowired
    private PlayerInfoUrlService playerInfoUrlService;

    @Value("${bot.username}")
    public String botUsername;

    @Override
    public String getBotUsername() {
        return botUsername;
    }

    @Autowired
    public FreeFireBot(@Value("${bot.token}") String botToken,  FreeFireService freeFireService) {
        super(botToken);
        this.freeFireService = freeFireService;

    }


    @Override
    public void onUpdateReceived(Update update) {
        if (!update.hasMessage() || !update.getMessage().hasText()) return;

        //if someone send a message to my bot, telegram will send an object "update". who send, the text, chat, etc.
        SendMessage msg = new SendMessage();
        msg.setChatId(update.getMessage().getChatId());
        String text = update.getMessage().getText();

        if (text.equals("/start"))
            msg.setText("comands: \n" + "/ban\n/player" + "\ndigite um comand + seu id.");

        else if (text.startsWith("/ban")) {
            String uid = text.replace("/ban", "").trim();

            if (uid.isEmpty()){
                msg.setText("\uD83D\uDEAB Comando inválido!, exemplo /ban 6750516508");
            } else if (!StringUtils.isNumeric(uid)){
                msg.setText("\uD83D\uDEAB Digite apenas números!");
            }
            else {

                String answer = banResponseService.checkBan(uid);

                if (answer != null){
                    msg.setText(answer);
                } else{
                    msg.setText("\uD83D\uDEAB ID NÃO ENCONTRADO!");
                }
            }
        }

        else if (text.startsWith("/player")){

            String uid = text.replace("/player", "").trim();

            if (uid.isEmpty()){
                msg.setText("\uD83D\uDEAB Comando inválido!, exemplo /player 6750516508");
            } else if (!StringUtils.isNumeric(uid)){
                msg.setText("\uD83D\uDEAB Digite apenas números!");
            }
            else {
               String answer = playerInfoUrlService.checkPlayerInfo(uid);

               if (answer != null){
                   msg.setText(answer);
               } else{
                   msg.setText("\uD83D\uDEAB ID NÃO ENCONTRADO!");
               }
            }
        }
        else{
            msg.setText("\uD83D\uDEAB Comando inválido!, tente novamente");
        }

        try{
            execute(msg);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}
