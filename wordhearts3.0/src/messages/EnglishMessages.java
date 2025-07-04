package messages;

public class EnglishMessages implements GameMessages {
    public String getWelcomeMessage() {
        return """
                👋 Welcome to WordHearts!
                I’ve picked a 5-letter word. Try to guess it!
                If you see \uD83D\uDC94 under a letter, it means my word doesn’t contain that letter.
                If you see \uD83E\uDDE1, the letter is in the word but in a different position.
                If you see \uD83D\uDC9A, the letter is in the correct position.
                If you get five \uD83D\uDC9A hearts, you win!
                Type your first 5-letter guess below
                """;
    }

    public String getPlayAgainMessage() {
        return "Would you like to play again?";
    }

    public String getStartGameFirstMessage() {
        return "Please start a game first with /start";
    }

    public String getThanksMessage() {
        return "Thanks for playing! 👋";
    }

    public String getErrorMessage() {
        return "Sorry, an error occurred. Please try again.";
    }
}