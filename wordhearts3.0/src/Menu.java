import java.util.Arrays;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.ArrayList;
import java.util.List;

import static org.apache.commons.codec.digest.MessageDigestAlgorithms.values;

public abstract class Menu {

    private final String display;

    public Menu(String display) {
        this.display = display;
    }

    public static EnglishWords fromDisplay(String display) {
        return Arrays.stream(values())
                .filter(lang -> lang.display.equals(display))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Unknown language: " + display));
    }

    abstract void sendWelcome(Long chatId);

    abstract void sendGuessResult(Long chatId, String result);

    abstract void sendGameOver(Long chatId, boolean won, String word);

    abstract void sendError(Long chatId);

    abstract void sendStartGameFirst(Long chatId);

    public String getDisplay() {

        return display;
    }

}
