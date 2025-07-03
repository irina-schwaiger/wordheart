package bot;

import model.Language;
import model.PlayerSession;
import menu.Menu;
import menu.MenuFactory;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.AnswerCallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Update;
import java.util.HashMap;
import java.util.Map;

public class WordHeartsBot extends TelegramLongPollingBot {
    private final Map<Long, PlayerSession> sessions = new HashMap<>();
    private final Map<Long, Menu> menus = new HashMap<>();

    @Override
    public void onUpdateReceived(Update upd) {
        long chatId = upd.hasMessage()
                ? upd.getMessage().getChatId()
                : upd.getCallbackQuery().getMessage().getChatId();

        PlayerSession session = sessions.computeIfAbsent(chatId, PlayerSession::new);
        Menu menu = menus.computeIfAbsent(chatId,
                id -> MenuFactory.createMenu(session.getLanguage(), this));

        if (upd.hasMessage() && upd.getMessage().getText().equals("/start")) {
            menu.sendWelcome(chatId);
        }
        if (update.hasCallbackQuery()) {
            long chatId = update.getCallbackQuery().getMessage().getChatId();
            String data = update.getCallbackQuery().getData().toUpperCase();
            handleLanguageSelection(chatId, data);
            // optional: Query quittieren, damit Telegram das “Lädt…” wegnimmt
            AnswerCallbackQuery ans = new AnswerCallbackQuery()
                    .setCallbackQueryId(update.getCallbackQuery().getId());
            executeSafely(ans);
        }
    }

    private void handleLanguageSelection(long chatId, String callbackData) {
        // 1) Session holen oder neu anlegen
        PlayerSession session = sessions
                .computeIfAbsent(chatId, PlayerSession::new);

        // 2) Sprache aus CallbackData ableiten
        Language lang;
        try {
            lang = Language.valueOf(callbackData);
        } catch (IllegalArgumentException ex) {
            lang = Language.ENGLISH;                // Fallback
        }
        session.setLanguage(lang);

        // 3) passendes Menu erzeugen und speichern
        Menu menu = MenuFactory.createMenu(lang, this);
        userMenus.put(chatId, menu);

        // 4) Begrüßung senden
        menu.sendWelcome(chatId);

        // 5) Erstes Spielwort ziehen und Session starten
        String word = WordProvider.getRandomWord(lang);
        session.startNewGame(word);
    }

    String word = WordProvider.getRandomWord(session.getLanguage());
session.startNewGame(word);
menu.sendWelcome(chatId);

    @Override
    public String getBotUsername() {
        return "WordHearts";
    }

    @Override
    public String getBotToken() {
        return System.getenv("BOT_TOKEN");
    }
}