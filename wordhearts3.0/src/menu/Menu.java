package menu;

import messages.GameMessages;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public abstract class Menu {
    protected final TelegramLongPollingBot bot;
    protected final GameMessages messages;

    public Menu(TelegramLongPollingBot bot, GameMessages messages) {
        this.bot = bot;
        this.messages = messages;
    }

    public void sendWelcome(long chatId) {
        send(messages.getWelcomeMessage(), chatId);
    }

    public void sendPlayAgain(long chatId) {
        send(messages.getPlayAgainMessage(), chatId);
    }

    public void sendStartGameFirst(long chatId) {
        send(messages.getStartGameFirstMessage(), chatId);
    }

    public void sendThanks(long chatId) {
        send(messages.getThanksMessage(), chatId);
    }

    public void sendError(long chatId) {
        send(messages.getErrorMessage(), chatId);
    }

    protected void send(String text, long chatId) {
        SendMessage msg = SendMessage.builder()
                .chatId(String.valueOf(chatId))
                .text(text)
                .build();
        try {
            bot.execute(msg);
        } catch (TelegramApiException e) {

            e.printStackTrace();
        }
    }

    public abstract void sendGuessResult(long chatId, String result);

    public abstract void sendGameOver(long chatId, boolean won, String word);
}