import io.github.cdimascio.dotenv.Dotenv;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.ArrayList;
import java.util.List;


public class WordHearts extends TelegramLongPollingBot {

    private static final Dotenv dotenv = Dotenv.load();

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            String userMessage = update.getMessage().getText();
            long chatId = update.getMessage().getChatId();

            if (userMessage.equals("/start")) {
                sendMenu(chatId);
            }
        } else if (update.hasCallbackQuery()) {
            String callbackData = update.getCallbackQuery().getData();
            long chatId = update.getCallbackQuery().getMessage().getChatId();

            handleMenuSelection(chatId, callbackData);
        }
    }


    private void sendMenu(long chatId) {
        SendMessage message = new SendMessage();
        message.setChatId(chatId);
        message.setText("Choose a language:");

        List<List<InlineKeyboardButton>> rows = new ArrayList<>();
        InlineKeyboardMarkup keyboardMarkup = new InlineKeyboardMarkup();

        // Row 1
        List<InlineKeyboardButton> row1 = new ArrayList<>();
        message.setReplyMarkup(keyboardMarkup);
        row1.add(InlineKeyboardButton en = new InlineKeyboardButton("English").setCallbackData("ENGLISH"));
        row1.add(InlineKeyboardButton de = new InlineKeyboardButton("Deutsch").setCallbackData("DEUTSCH"));

        // Row 2
        List<InlineKeyboardButton> row2 = new ArrayList<>();
        row2.add(InlineKeyboardButton fr = new InlineKeyboardButton("Française").setCallbackData("FRANÇAISE"));
        row2.add(InlineKeyboardButton es = new InlineKeyboardButton("Español").setCallbackData("ESPAÑOL"));

        rows.add(row1);
        rows.add(row2);


        keyboardMarkup.setKeyboard();

    }

    private void handleMenuSelection(long chatId, String callbackData) {
        String responseText;

        switch (callbackData) {
            case "SPANISH":
                responseText = "¡Ha seleccionado el español!";
                break;
            case "GERMAN":
                responseText = "Sie haben Deutsch ausgewählt!";
                break;
            case "FRENCH":
                responseText = "Vous avez choisi le français!";
                break;
            default:
                responseText = "You are playing in English.";
        }

        SendMessage message = new SendMessage();
        message.setChatId(chatId);
        message.setText(responseText);

    }
    private void sendResponse(long chatId, String s) {
        SendMessage msg = new SendMessage();
        msg.setChatId(chatId);
        msg.setText(s);
        msg.enableMarkdown(true);


    }

    @Override
    public String getBotUsername() {
        return "WordHearts";
    }

    @Override
    public String getBotToken() {
        return  dotenv.get("8032955664:AAFTFN0YAA4Fn0EvDYXxz4hTUb6z7l5dlC0");
    }
}

