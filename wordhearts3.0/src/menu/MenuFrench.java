package menu;

import messages.FrenchMessages;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;

public class MenuFrench extends Menu {

    public MenuFrench(TelegramLongPollingBot bot) {
        super(bot, new FrenchMessages());
    }

    @Override
    public void sendGuessResult(long chatId, String result) {
        send(result, chatId);
    }

    @Override
    public void sendGameOver(long chatId, boolean won, String word) {
        String msg = won
                ? "Bravo ! 🎉 Le mot était « " + word + " »"
                : "Dommage… 😞 Le mot était « " + word + " »";
        send(msg, chatId);
    }
}