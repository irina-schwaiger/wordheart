package bot;

import model.Language;
import model.PlayerSession;
import menu.Menu;
import menu.MenuFactory;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.AnswerCallbackQuery;
import org.telegram.telegrambots.meta.api.methods.botapimethods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import words.WordProvider;
import words.GameEngine;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.AnswerCallbackQuery;


public class WordHeartsBot extends TelegramLongPollingBot {

    private final Map<Long, PlayerSession> sessions = new HashMap<>();
    private final Map<Long, Menu> menus      = new HashMap<>();


    public void onUpdateReceived(Update update) {
        // 1) Chat-ID bestimmen
        long chatId = update.hasMessage()
                ? update.getMessage().getChatId()
                : update.getCallbackQuery().getMessage().getChatId();

        PlayerSession session = sessions.computeIfAbsent(chatId, PlayerSession::new);
        Menu menu = menus.computeIfAbsent(
                chatId,
                id -> MenuFactory.createMenu(session.getLanguage(), this)
        );

        if (update.hasMessage() && "/start".equals(update.getMessage().getText().trim())) {
            sendLanguageMenu(chatId);
            return;
        }

        if (update.hasCallbackQuery()) {
            String data = update.getCallbackQuery().getData().toUpperCase();
            long callbackId = Long.parseLong(update.getCallbackQuery().getId());
            handleLanguageSelection(chatId, data);
            String callBackId = Sting.valueOf(callbackId);
            executeSafely(new AnswerCallbackQuery(BotApiMethod));
            return;
        }

        if (update.hasMessage() && session.isGameActive()) {
            String guess = update.getMessage().getText().trim();
            handleGuess(chatId, session, menu, guess);
            return;
        }

        if (update.hasMessage()) {
            menu.sendStartGameFirst(chatId);
        }
    }

    private void sendLanguageMenu(long chatId) {
        InlineKeyboardMarkup keyboard = InlineKeyboardMarkup.builder()
                .keyboard(List.of(
                        List.of(
                                InlineKeyboardButton.builder()
                                        .text("🇬🇧 English").callbackData("ENGLISH").build(),
                                InlineKeyboardButton.builder()
                                        .text("🇩🇪 Deutsch").callbackData("GERMAN").build()
                        ),
                        List.of(
                                InlineKeyboardButton.builder()
                                        .text("🇫🇷 Français").callbackData("FRENCH").build(),
                                InlineKeyboardButton.builder()
                                        .text("🇪🇸 Español").callbackData("SPANISH").build()
                        )
                ))
                .build();

        SendMessage msg = SendMessage.builder()
                .chatId(String.valueOf(chatId))
                .text("Choose a language:")
                .replyMarkup(keyboard)
                .build();

        executeSafely(msg);
    }

    private void handleLanguageSelection(long chatId, String callbackData) {
        PlayerSession session = sessions.get(chatId);

        Language lang;
        try {
            lang = Language.valueOf(callbackData);
        } catch (IllegalArgumentException ex) {
            lang = Language.ENGLISH;
        }
        session.setLanguage(lang);

        Menu menu = MenuFactory.createMenu(lang, this);
        menus.put(chatId, menu);

        menu.sendWelcome(chatId);

        String word = WordProvider.getRandomWord(lang);
        session.startNewGame(word);
    }

    private void handleGuess(long chatId,
                             PlayerSession session,
                             Menu menu,
                             String guess) {

        session.incrementAttempts();
        String result = GameEngine.checkGuess(guess, session.getCurrentWord());
        menu.sendGuessResult(chatId, result);

        boolean correct = GameEngine.isCorrect(result);
        if (correct || session.getAttempts() >= GameEngine.maxAttempts) {
            menu.sendGameOver(chatId, correct, session.getCurrentWord());
            session.endGame();
        }

        String feedback = GameEngine.checkGuess(guess, session.getCurrentWord());
        menu.sendGuessResult(chatId, feedback);

        if (GameEngine.isCorrect(feedback)
                || session.getAttempts() >= GameEngine.maxAttempts) {
            menu.sendGameOver(chatId,
                    GameEngine.isCorrect(feedback),
                    session.getCurrentWord());
            session.endGame();
        }
    }

    private <T> T executeSafely(BotApiMethod<T> apiMethod) {
        try {
            return execute(apiMethod);
        } catch (TelegramApiException e) {
            e.printStackTrace();
            return null;
        }
    }

    public String getBotUsername() {
        return "WordHearts";
    }

    @Override
    public String getBotToken() {
        return System.getenv("BOT_TOKEN");
    }
}