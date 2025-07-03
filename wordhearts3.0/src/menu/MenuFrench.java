package menu;

import messages.EnglishMessages;
import messages.FrenchMessages;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;

public class MenuFrench extends Menu {
    public MenuFrench(TelegramLongPollingBot bot) {
        super(WordHearts, new FrenchMessages() {
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