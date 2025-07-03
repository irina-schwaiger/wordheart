import org.intellij.lang.annotations.Language;

public enum Languages implements GameMessage {
    ;

    @Override
    public String GameMessage(String welcomeMessage,
                              String playAgainMessage,
                              String startGameFirstMessage,
                              String thanksForPlayingMessage,
                              String errorMessage) {
        return welcomeMessage + playAgainMessage + startGameFirstMessage + thanksForPlayingMessage + errorMessage;
    }

        Language language;
        Language english = ENGLISH("👋 Welcome to WordHearts!\n\n" +
                        "I picked a 5-letter word. Try to guess it!\n" +
                        "If you see 💔 under a letter of your word, it means my word does not contain this letter.\n" +
                        "If you see 🧡 under a letter of your word, it means it is also in my word, but not at this position.\n" +
                        "If you see 💚 under a letter of your word, it means the correct letter is at the correct position.\n\n" +
                        "If you get five 💚 hearts, you win!\n\n" +
                        "Type your first 5-letter guess below:",
                "Would you like to play again?",
                "Please start a game first using /start",
                "Thanks for playing! 👋",
                "Sorry, an error occurred. Please try again.");

        Language german = GERMAN("👋 Willkommen bei WordHearts!\n\n" +
                        "Ich habe mir ein 5-buchstabiges Wort ausgedacht. Versuche es zu erraten!\n" +
                        "Wenn du 💔 unter einem Buchstaben siehst, ist dieser nicht in meinem Wort enthalten.\n" +
                        "Wenn du 🧡 siehst, ist der Buchstabe im Wort, aber an einer anderen Position.\n" +
                        "Wenn du 💚 siehst, ist der Buchstabe an der richtigen Position.\n\n" +
                        "Wenn du fünf 💚 Herzen hast, gewinnst du!\n\n" +
                        "Gib deinen ersten 5-buchstabigen Versuch ein:",
                "Möchtest du noch einmal spielen?",
                "Bitte starte zuerst ein Spiel mit /start",
                "Danke fürs Spielen! 👋",
                "Entschuldigung, ein Fehler ist aufgetreten. Bitte versuche es erneut.");


   Language french = FRENCH("👋 Bienvenue sur WordHearts!\n\n" +
                   "J'ai choisi un mot de 5 lettres. Essayez de le deviner!\n" +
                   "Si vous voyez 💔 sous une lettre, cela signifie que mon mot ne contient pas cette lettre.\n" +
                   "Si vous voyez 🧡, la lettre est dans le mot mais pas à cette position.\n" +
                   "Si vous voyez 💚, la lettre est à la bonne position.\n\n" +
                   "Si vous obtenez cinq 💚 cœurs, vous gagnez!\n\n" +
                   "Tapez votre première suggestion de 5 lettres ci-dessous:",
           "Voulez-vous rejouer?",
           "Veuillez d'abord commencer une partie avec /start",
           "Merci d'avoir joué! 👋",
           "Désolé, une erreur s'est produite. Veuillez réessayer.");

   Language spanish = SPANISH("👋 ¡Bienvenido a WordHearts!\n\n" +
                   "He elegido una palabra de 5 letras. ¡Intenta adivinarla!\n" +
                   "Si ves 💔 debajo de una letra, significa que mi palabra no contiene esa letra.\n" +
                   "Si ves 🧡, la letra está en la palabra pero no en esa posición.\n" +
                   "Si ves 💚, la letra está en la posición correcta.\n\n" +
                   "¡Si consigues cinco 💚 corazones, ganas!\n\n" +
                   "Escribe tu primera palabra de 5 letras:",
           "¿Quieres jugar otra vez?",
           "Por favor, inicia primero un juego con /start",
           "¡Gracias por jugar! 👋",
           "Lo siento, ha ocurrido un error. Por favor, inténtalo de nuevo.");



    private Language ENGLISH(String welcomeMessage, String playAgainMessage, String startGameFirstMessage, String thanksForPlayingMessage, String errorMessage) {
        return null;
    }
    private Language GERMAN(String welcomeMessage, String playAgainMessage, String startGameFirstMessage, String thanksForPlayingMessage, String errorMessage) {
        return null;
    }

    private Language FRENCH(String welcomeMessage, String playAgainMessage, String startGameFirstMessage, String thanksForPlayingMessage, String errorMessage) {
        return null;
    }

    private Language SPANISH(String welcomeMessage, String playAgainMessage, String startGameFirstMessage, String thanksForPlayingMessage, String errorMessage) {
        return null;
    }


}
