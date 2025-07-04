package messages;

public class FrenchMessages implements GameMessages {
    @Override
    public String getWelcomeMessage() {
        return "\"\uD83D\uDC4B Bienvenue sur WordHearts!\n\n\" +\n" +
            "                   \"J'ai choisi un mot de 5 lettres. Essayez de le deviner!\n\" +\n" +
            "                   \"Si vous voyez \uD83D\uDC94 sous une lettre, cela signifie que mon mot ne contient pas cette lettre.\\n\" +\n" +
            "                   \"Si vous voyez \uD83E\uDDE1, la lettre est dans le mot mais pas à cette position.\n\" +\n" +
            "                   \"Si vous voyez \uD83D\uDC9A, la lettre est à la bonne position.\n\n\" +\n" +
            "                   \"Si vous obtenez cinq \uD83D\uDC9A cœurs, vous gagnez!\n\n\" +\n" +
            "                   \"Tapez votre première suggestion de 5 lettres ci-dessous:";
    }

    @Override
    public String getPlayAgainMessage() {
        return "Voulez-vous rejouer?";
    }

    @Override
    public String getStartGameFirstMessage() {
        return "Veuillez d'abord commencer une partie avec /start";
    }

    @Override
    public String getThanksMessage() {
        return "Merci d'avoir joué! 👋";
    }

    @Override
    public String getErrorMessage() {
        return "Désolé, une erreur s'est produite. Veuillez réessayer.";

    }
}
