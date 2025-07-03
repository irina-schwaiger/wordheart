package model;

import messages.GameMessages;

public enum Language implements GameMessages {
    ENGLISH, GERMAN, FRENCH, SPANISH;


    @Override
    public String getWelcomeMessage() {
        return "";
    }

    @Override
    public String getPlayAgainMessage() {
        return "";
    }

    @Override
    public String getStartGameFirstMessage() {
        return "";
    }

    @Override
    public String getThanksMessage() {
        return "";
    }

    @Override
    public String getErrorMessage() {
        return "";
    }
}