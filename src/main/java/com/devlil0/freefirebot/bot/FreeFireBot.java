package com.devlil0.freefirebot.bot;

import com.devlil0.freefirebot.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.List;

import static com.devlil0.freefirebot.helper.VerifyText.verifyText;

@Component
public class FreeFireBot extends TelegramLongPollingBot {

    @Value("${bot.username}")
    private String botUsername;

    private final FreeFireService freeFireService;

    @Autowired
    private BanResponseService banResponseService;
    @Autowired
    private PlayerInfoUrlService playerInfoUrlService;
    @Autowired
    private SocialInfoService socialInfoService;
    @Autowired
    private PlayerStatsService playerStats;

    public FreeFireBot(@Value("${bot.token}") String botToken, FreeFireService freeFireService) {
        super(botToken);
        this.freeFireService = freeFireService;
    }

    @Override
    public String getBotUsername() {
        return botUsername;
    }

    @Override
    public void onUpdateReceived(Update update) {

        // =========================
        // CALLBACK (BOTÕES)
        // =========================
        if (update.hasCallbackQuery()) {

            SendMessage msg = new SendMessage();
            String data = update.getCallbackQuery().getData();
            msg.setChatId(update.getCallbackQuery().getMessage().getChatId().toString());

            if (data.startsWith("stats_")) {

                String[] parts = data.split("_", 4);

                // =========================
                // MENU BR (COM MODOS)
                // =========================
                if (parts.length == 3) {
                    String gamemode = parts[1];
                    String uid = parts[2];

                    // 🔥 CS NÃO TEM SUBMENU
                    if (gamemode.equals("cs")) {

                        String verifiedText = verifyText(uid);

                        if (verifiedText != null) {
                            msg.setText(verifiedText);
                        } else {
                            String player = playerStats.checkPlayerStats(uid, "cs", "");

                            msg.setText((player));

                        }

                        executeSafe(msg);
                        return;
                    }

                    // 🔫 BR → MOSTRA SOLO/DUO/SQUAD
                    InlineKeyboardButton solo = new InlineKeyboardButton("👤 SOLO");
                    solo.setCallbackData("stats_br_solo_" + uid);

                    InlineKeyboardButton duo = new InlineKeyboardButton("👥 DUO");
                    duo.setCallbackData("stats_br_duo_" + uid);

                    InlineKeyboardButton squad = new InlineKeyboardButton("🔥 SQUAD");
                    squad.setCallbackData("stats_br_squad_" + uid);

                    InlineKeyboardMarkup keyboard = new InlineKeyboardMarkup();
                    keyboard.setKeyboard(List.of(
                            List.of(solo, duo),
                            List.of(squad)
                    ));

                    msg.setText("🔥 Escolha o modo:");
                    msg.setReplyMarkup(keyboard);

                    executeSafe(msg);
                    return;
                }

                // =========================
                // BUSCAR STATS (BR)
                // =========================
                if (parts.length == 4) {
                    String gamemode = parts[1];
                    String mode = parts[2];
                    String uid = parts[3];

                    String verifiedText = verifyText(uid);

                    if (verifiedText != null) {
                        msg.setText(verifiedText);
                    } else {
                        String player = playerStats.checkPlayerStats(uid, gamemode, mode);

                        msg.setText((player != null && !player.isBlank())
                                ? player
                                : "🚫 ERRO AO BUSCAR STATS!");
                    }

                    executeSafe(msg);
                    return;
                }
            }

            return;
        }

        // =========================
        // MENSAGENS NORMAIS
        // =========================
        if (!update.hasMessage() || !update.getMessage().hasText()) return;

        SendMessage msg = new SendMessage();
        msg.setChatId(update.getMessage().getChatId());

        String text = update.getMessage().getText();

        if (text.equals("/start")) {
            msg.setText(
                    "✨ COMANDOS DISPONÍVEIS ✨\n\n" +
                            "⚙️ /ban\n" +
                            "🎮 /player\n" +
                            "📊 /socialinfo\n" +
                            "❤️ /stats\n\n" +
                            "💡 Use: /comando ID"
            );
        }

        else if (text.startsWith("/ban")) {
            String uid = text.replace("/ban", "").trim();

            String verifiedText = verifyText(uid);

            if (verifiedText != null) {
                msg.setText(verifiedText);
            } else {
                String response = banResponseService.checkBan(uid);
                msg.setText(response != null ? response : "🚫 ID NÃO ENCONTRADO!");
            }
        }

        else if (text.startsWith("/player")) {
            String uid = text.replace("/player", "").trim();

            String verifiedText = verifyText(uid);

            if (verifiedText != null) {
                msg.setText(verifiedText);
            } else {
                String response = playerInfoUrlService.checkPlayerInfo(uid);
                msg.setText(response != null ? response : "🚫 ID NÃO ENCONTRADO!");
            }
        }

        else if (text.startsWith("/socialinfo")) {
            String uid = text.replace("/socialinfo", "").trim();

            String verifiedText = verifyText(uid);

            if (verifiedText != null) {
                msg.setText(verifiedText);
            } else {
                String response = socialInfoService.socialInfoRequest(uid);
                msg.setText(response != null ? response : "🚫 ID NÃO ENCONTRADO!");
            }
        }

        else if (text.startsWith("/stats")) {
            String uid = text.replace("/stats", "").trim();

            String verifiedText = verifyText(uid);

            if (verifiedText != null) {
                msg.setText(verifiedText);
            } else {

                InlineKeyboardButton br = new InlineKeyboardButton("🔫 BR RANKED");
                br.setCallbackData("stats_br_" + uid);

                InlineKeyboardButton cs = new InlineKeyboardButton("🔥 CS RANKED");
                cs.setCallbackData("stats_cs_" + uid);

                InlineKeyboardMarkup keyboard = new InlineKeyboardMarkup();
                keyboard.setKeyboard(List.of(List.of(br, cs)));

                msg.setText("🎮 Escolha o modo:");
                msg.setReplyMarkup(keyboard);
            }
        }

        else {
            msg.setText("🚫 Comando inválido!");
        }

        executeSafe(msg);
    }

    private void executeSafe(SendMessage msg) {
        try {
            if (msg.getText() == null || msg.getText().isBlank()) {
                msg.setText("⚠️ Erro interno.");
            }
            execute(msg);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}