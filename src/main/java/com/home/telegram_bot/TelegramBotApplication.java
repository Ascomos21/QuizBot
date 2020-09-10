package com.home.telegram_bot;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.ArrayList;

@SpringBootApplication
public class TelegramBotApplication {

    public static void main(String[] args) {
        ArrayList arrayList = new ArrayList<>();
        arrayList.add(34);
        arrayList.add("asd");
        System.out.println(arrayList.size());
        // Initialize Api Context
        ApiContextInitializer.init();

        // Instantiate Telegram Bots API
        TelegramBotsApi botsApi = new TelegramBotsApi();

        // Register our bot
        try {
            botsApi.registerBot(new PredictionTelegramBot());
        } catch (TelegramApiException e) {
            e.getMessage();
        }
    }

}
