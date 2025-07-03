package menu;

import messages.GermanMessages;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;

public class MenuGerman extends Menu {
    public MenuGerman(TelegramLongPollingBot WordHearts) {
        super(WordHearts, new GermanMessages());
    }

    @Override
    public void sendGuessResult(long chatId, String result) {
        send(result, chatId);
    }
    @Override
    public void sendGameOver(long chatId, boolean won, String word) {
        send(won ? "Gewonnen! 🎉 Das Wort lautet: " + word
                : "Verloren... Das Wort lautet: " + word, chatId);
    }
}