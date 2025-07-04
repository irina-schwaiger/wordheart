package menu;

import model.Language;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;

public class MenuFactory {
    public static Menu createMenu(Language lang, TelegramLongPollingBot bot) {

        return switch (lang) {
            case ENGLISH -> new MenuEnglish(bot);
            case GERMAN -> new MenuGerman(bot);
            case FRENCH -> new MenuFrench(bot);
            case SPANISH -> new MenuSpanish(bot);
        };
    }
}

