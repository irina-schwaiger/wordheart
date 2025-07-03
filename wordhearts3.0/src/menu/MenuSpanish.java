package menu;

import messages.SpanishMessages;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;

public class MenuSpanish extends Menu {

    public MenuSpanish(TelegramLongPollingBot bot) {
        super(bot, new SpanishMessages());
    }

    @Override
    public void sendGuessResult(long chatId, String result) {
        send(result, chatId);
    }

    @Override
    public void sendGameOver(long chatId, boolean won, String word) {
        String text = won
                ? "¡Has ganado! 🎉 La palabra era: " + word
                : "Lo siento, has perdido… La palabra era: " + word;
        send(text, chatId);
    }
}