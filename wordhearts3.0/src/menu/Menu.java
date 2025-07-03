package menu;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import messages.GameMessages;

public abstract class Menu {
    protected final TelegramLongPollingBot bot;
    protected final GameMessages msgs;

    public Menu(TelegramLongPollingBot bot, GameMessages msgs) {
        this.bot = bot;
        this.msgs = msgs;
    }

    public void sendWelcome(long chatId)        { send(msgs.getWelcomeMessage(), chatId); }
    public void sendPlayAgain(long chatId)      { send(msgs.getPlayAgainMessage(), chatId); }
    public void sendStartFirst(long chatId)     { send(msgs.getStartGameFirstMessage(), chatId); }
    public void sendThanks(long chatId)         { send(msgs.getThanksMessage(), chatId); }
    public void sendError(long chatId)          { send(msgs.getErrorMessage(), chatId); }

    protected void send(String text, long chatId) {
        SendMessage m = new SendMessage(String.valueOf(chatId), text);
        try { bot.execute(m); }
        catch (TelegramApiException e) { e.printStackTrace(); }
    }

    // Spielspezifisch
    public abstract void sendGuessResult(long chatId, String result);
    public abstract void sendGameOver(long chatId, boolean won, String word);
}