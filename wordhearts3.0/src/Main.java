import org.telegram.telegrambots.meta.exceptions.TelegramApiRequestException;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        ApiContextInitializer.init();
        TelegramBotApi telegramBotApi = new TelegramBotApi();
        try {
            telegramBotApi.registerBot(Bot.getBot());
        } catch (TelegramApiRequestException e) {
            e.printStackTrace();
        }
}
}