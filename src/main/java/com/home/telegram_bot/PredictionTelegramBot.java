package com.home.telegram_bot;

import org.springframework.beans.factory.annotation.Value;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class PredictionTelegramBot extends TelegramLongPollingBot {
    @Value ("${userName}")
    private String botName ;
    @Value ("${botToken}")
    private String botToken;
    public void onUpdateReceived(Update update) {
        // We check if the update has a message and the message has text
        if (update.hasMessage() && update.getMessage().hasText()) {
            // get the username, id, and the message text
            String username = update.getMessage().getChat().getUserName();
            long user_id = update.getMessage().getChat().getId();
            String message_text = update.getMessage().getText();
            long chat_id = update.getMessage().getChatId();

            SendMessage message = new SendMessage().setChatId(chat_id).setText(message_text);
            // since just echo bot, the bot response is the same as the incoming update message text
            log(username, Long.toString(user_id), message_text, message_text);
            GameHandler.check(this, update);
            try {
                execute(message); // Sending our message object to user
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }
    }
    private void log(String username, String chatId, String textReceived, String botResponse) {
        System.out.println("----------------------------\n");
        // print out in PM/AM time
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd h:mm a");
        Date date = new Date();
        System.out.println(dateFormat.format(date));
        System.out.println("Message from " + username + ". (id = " + chatId + ") \n Text - " + textReceived);
        System.out.println("Bot answer: \n Text - " + botResponse);
    }
    public void sendPhoto(String chatId, String url) {
        SendPhoto sendPhoto = new SendPhoto();
        sendPhoto.setChatId(chatId);
        sendPhoto.setPhoto(url);
        try {
            execute(sendPhoto);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    public void sendMessage(String chatId, String message) {
        SendMessage sendMessageRequest = new SendMessage();
        sendMessageRequest.setChatId(chatId);
        sendMessageRequest.setText(message);
        try {
            execute(sendMessageRequest);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
    @Override
    public String getBotUsername() {
        System.out.println(botName+"++++++++++++++++++++++++++++++++++++++++++++++++++++");
        // Return bot username
        // If bot username is @KodeCentralBot, it must return 'KodeCentralBot'
        return "@UkrRailwayBot";
    }

    @Override
    public String getBotToken() {
        System.out.println(botToken+"++++++++++++++++++++++++++++++++++++++++++++++++++++");

        // Return bot token from BotFather
        return "1392164303:AAHpTHfscUBPBRQMoO0iJCXJ5TvAQGQ9Nbw";
    }

}
