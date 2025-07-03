package bot;

import model.Language;
import model.session;
import menu.Menu;
import menu.MenuFactory;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.AnswerCallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Update;
import words.WordProvider;

import java.util.HashMap;
import java.util.Map;

public class WordHeartsBot extends TelegramLongPollingBot {
    private final Map<Long, session> sessions = new HashMap<>();
    private final Map<Long, Menu> menus = new HashMap<>();

    @Override
    public void onUpdateReceived(Update upd) {
        long chatId = upd.hasMessage()
                ? upd.getMessage().getChatId()
                : upd.getCallbackQuery().getMessage().getChatId();

        session session = sessions.computeIfAbsent(chatId, model.session::new);
        Menu menu = menus.computeIfAbsent(chatId,
                id -> MenuFactory.createMenu(session.getLanguage(), this));

        if (upd.hasMessage() && upd.getMessage().getText().equals("/start")) {
            menu.sendWelcome(chatId);
        }
        if (upd.hasCallbackQuery()) {
            chatId = upd.getCallbackQuery().getMessage().getChatId();
            String data = upd.getCallbackQuery().getData().toUpperCase();
            handleLanguageSelection(chatId, data);
            // optional: Query quittieren, damit Telegram das “Lädt…” wegnimmt
            AnswerCallbackQuery ans = new AnswerCallbackQuery()
                    .setCallbackQueryId(upd.getCallbackQuery().getId());
            executeSafely(ans);
        }
    }

    private void handleLanguageSelection(long chatId, String callbackData) {
        session session = sessions
                .computeIfAbsent(chatId, model.session::new);

        Language lang;
        try {
            lang = Language.valueOf(callbackData);
        } catch (IllegalArgumentException ex) {
            lang = Language.ENGLISH;                // Fallback
        }
        session.setLanguage(lang);

        Menu menu = MenuFactory.createMenu(lang, this);
        userMenus.put(chatId, menu);

        menu.sendWelcome(chatId);




        String word = WordProvider.getRandomWord(session.getLanguage());
        session.startNewGame(word);
        menu.sendWelcome(chatId);
    }

    @
    public String getBotUsername() {
        return "WordHearts";
    }

    @Override
    public String getBotToken() {
        return System.getenv("BOT_TOKEN");
    }
}