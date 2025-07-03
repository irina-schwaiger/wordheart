package menu;

import bot.WordHearts.WordHeartsBot;
import bot.WordHeartsBot;
import messages.EnglishMessages;
import messages.SpanishMessages;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;

public class MenuSpanish extends Menu {
    public MenuSpanish(TelegramLongPollingBot bot) {
        super(WordHearts.WordHeartsBot, new SpanishMessages() {
        });
    }

    @Override
    public void sendGuessResult(long chatId, String result) {
        send(result, chatId);
    }
    @Override
    public void sendGameOver(long chatId, boolean won, String word) {
        send(won ? "You won! 🎉 The word was " + word
                : "You lost. The word was " + word, chatId);
    }
}