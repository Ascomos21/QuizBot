package com.home.telegram_bot;

import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.HashMap;

public class GameHandler {
    private static HashMap<Long, Game> games = new HashMap<>();

    public static void check(PredictionTelegramBot telegramBot, Update update) {
        Game game = new Game();
        long chatId = update.getMessage().getChatId();
        if (games.containsKey(chatId))
            game = games.get(chatId);
        else {
            games.put(chatId, game);
        }

        // variables
        Message message = update.getMessage();
        String message_text = message.getText();
        String message_text_lower = message_text.toLowerCase();
        String username = message.getFrom().getUserName();

        // check if starting a new guess game
        if (message_text_lower.equals("/guess") || message_text_lower.equals("???? guess") || message_text_lower.equals("start"))
        {
            // only create a new guess game if there isn't one
            if (game.guessGame == null) {
                game.guessGame = ImageGuess.random();
            }
            telegramBot.sendPhoto(Long.toString(chatId), game.guessGame.getUrl());
        }
        // check if guess game answered correctly
        else if (game.guessGame != null && message_text_lower.contains(game.guessGame.getAnswer())) {
            Database.getInstance().incrementGuessScore(username);

            // reset the guess game!
            game.guessGame = null;
            telegramBot.sendMessage(Long.toString(chatId), username + " has won!");
        } else if (message_text_lower.equals("/scores")) {
            Database d = Database.getInstance();
            telegramBot.sendMessage(Long.toString(chatId), "Guess: " + d.getGuessScore(username));
        }
    }

    private static class Game {
        private ImageGuess guessGame = null;
    }


}

