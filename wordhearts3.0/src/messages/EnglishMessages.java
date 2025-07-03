package messages;

public class EnglishMessages implements GameMessages {
    public String getWelcomeMessage() { return "👋 Welcome to WordHearts! …"; }
    public String getPlayAgainMessage() { return "Would you like to play again?"; }
    public String getStartGameFirstMessage() { return "Please start a game first with /start"; }
    public String getThanksMessage() { return "Thanks for playing! 👋"; }
    public String getErrorMessage() { return "Sorry, an error occurred. Please try again."; }
}