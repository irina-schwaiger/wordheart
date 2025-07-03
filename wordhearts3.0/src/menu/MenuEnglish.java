package menu;

import messages.EnglishMessages;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;

public class MenuEnglish extends Menu {

    public MenuEnglish(TelegramLongPollingBot bot) {
        super(bot, new EnglishMessages());
    }

    @Override
    public void sendGuessResult(long chatId, String result) {
        send(result, chatId);
    }

    @Override
    public void sendGameOver(long chatId, boolean won, String word) {
        send(won
                        ? "You won! 🎉 The word was " + word
                        : "You lost. The word was " + word,
                chatId);
    }
}