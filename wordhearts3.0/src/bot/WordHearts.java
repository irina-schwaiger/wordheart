package bot;

import model.Language;
import model.Session;
import menu.Menu;
import menu.MenuFactory;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.AnswerCallbackQuery;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import words.GameEngine;
import words.WordProvider;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WordHearts extends TelegramLongPollingBot {

    private final Map<Long, Session> sessions = new HashMap<>();
    private final Map<Long, Menu> menus = new HashMap<>();

    @Override
    public void onUpdateReceived(Update update) {
        long chatId = update.hasMessage()
                ? update.getMessage().getChatId()
                : update.getCallbackQuery().getMessage().getChatId();


        Session session = sessions.computeIfAbsent(chatId, Session::new);
        Menu menu = menus.computeIfAbsent(
                chatId,
                id -> MenuFactory.createMenu(session.getLanguage(), this)
        );
//        menu.sendStartGameFirst(chatId);

        if (update.hasMessage() && "/start".equals(update.getMessage().getText())) {
            showLanguageMenu(chatId);
            return;
        }

        if (update.hasCallbackQuery()) {
            String data = update.getCallbackQuery().getData().toUpperCase();
            String callbackId = update.getCallbackQuery().getId();

            handleLanguageSelection(chatId, data);
            executeSafely(new AnswerCallbackQuery(callbackId));
            return;
        }

        if (update.hasMessage() && session.isGameActive()) {
            String guess = update.getMessage().getText().trim();
            handleGuess(chatId, session, menu, guess);
        }

    }


    private void showLanguageMenu(long chatId) {
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
        Session session = sessions.get(chatId);

        Language lang;
        try {
            lang = Language.valueOf(callbackData);
        } catch (IllegalArgumentException e) {
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
                             Session session,
                             Menu menu,
                             String guess) {

        session.incrementAttempts();
        String feedback = words.GameEngine.checkGuess(guess, session.getCurrentWord());
        menu.sendGuessResult(chatId, feedback);

        boolean correct = words.GameEngine.isCorrect(feedback);
        if (correct || session.getAttempts() >= GameEngine.maxAttempts) {
            menu.sendGameOver(chatId, correct, session.getCurrentWord());
            session.endGame();
        }
    }

    private <T extends Serializable> T executeSafely(BotApiMethod<T> method) {
        try {
            return execute(method);
        } catch (TelegramApiException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public String getBotUsername() {
        return System.getenv("BOT_USERNAME");
    }

    @Override
    public String getBotToken() {
        return System.getenv("BOT_TOKEN");
    }
}