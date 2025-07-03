
package model;

public class PlayerSession {
    private final long chatId;
    private Language language = Language.ENGLISH;
    private String currentWord;
    private int attempts;
    private boolean gameActive;

    public int getAttempts() {
        return attempts;
    }

    public void setAttempts(int attempts) {
        this.attempts = attempts;
    }

    public boolean isGameActive() {
        return gameActive;
    }

    public void setGameActive(boolean gameActive) {
        this.gameActive = gameActive;
    }

    public Language getLanguage() {
        return language;
    }

    public void setLanguage(Language language) {
        this.language = language;
    }

    public String getCurrentWord() {
        return currentWord;
    }

    public void setCurrentWord(String currentWord) {
        this.currentWord = currentWord;
    }

    public long getChatId() {
        return chatId;
    }


    public PlayerSession(long chatId) {
        this.chatId = chatId;
    }

    public void startNewGame(String word) {
        this.currentWord = word;
        this.attempts = 0;
        this.gameActive = true;
    }

    public void incrementAttempts() {
        this.attempts++;
    }

    public void endGame() {
        this.gameActive = false;
    }

}

