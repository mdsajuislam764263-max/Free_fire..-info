package com.devlil0.freefirebot.helper;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

@Component
public class VerifyText {


    public static String verifyText(String uid){
         String text = "";

        if (uid.isEmpty()){
            text = ("\uD83D\uDEAB Comando inválido!, exemplo /player 6750516508");
        } else if (!StringUtils.isNumeric(uid)){
            text = ("\uD83D\uDEAB Digite apenas números!");
        } else {
            return null;
        }
        return text;
    }

}
