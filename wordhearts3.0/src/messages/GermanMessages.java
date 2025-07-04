package messages;

public class GermanMessages implements GameMessages {

        @Override
        public String getWelcomeMessage() {
            return "👋 Willkommen bei WordHearts!\n\nIch habe ein Wort mit 5 Buchstaben ausgewählt. Versuche es zu erraten!";
        }

        @Override
        public String getPlayAgainMessage() {
            return "Nochmal spielen?";
        }

        @Override
        public String getStartGameFirstMessage() {
            return "Starte das Spiel indem du /start schreibst.";
        }

        @Override
        public String getThanksMessage() {
            return "Danke für's mitspielen! 👋";
        }

        @Override
        public String getErrorMessage() {
            return "Entschuldigung, es ist ein Fehler aufgetreten. Bitte nochmal versuchen.";
        }
    }