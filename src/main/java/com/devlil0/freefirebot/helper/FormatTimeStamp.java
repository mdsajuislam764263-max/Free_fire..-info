package com.devlil0.freefirebot.helper;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class FormatTimeStamp {

    public static String formatTimestamp(String timestamp) {
        long epoch = Long.parseLong(timestamp);
        LocalDateTime date = LocalDateTime.ofInstant(
                Instant.ofEpochSecond(epoch),
                ZoneId.of("America/Sao_Paulo")
        );
        return date.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));
    }

    public static String formatTimeStampWithInteger(Integer timestamp){
        LocalDateTime date = LocalDateTime.ofInstant(
                Instant.ofEpochSecond(timestamp),
                ZoneId.of("America/Sao_Paulo")
        );
        return date.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));
    }

}
