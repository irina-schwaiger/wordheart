package bot;

import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

public class Main {
    public static void main(String[] args) {
        try {
            // Initialisiert intern alles – kein ApiContextInitializer mehr
            TelegramBotsApi botsApi = new TelegramBotsApi(DefaultBotSession.class);
            // registerBot nimmt hier direkt deine TelegramLongPollingBot-Instanz
            botsApi.registerBot(new WordHearts());
            System.out.println("Bot gestartet!");
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}