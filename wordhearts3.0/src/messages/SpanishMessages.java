package messages;

public class SpanishMessages implements GameMessages {
    @Override
    public String getWelcomeMessage() {
        return """
                👋 ¡Bienvenido a WordHearts!
                
                He elegido una palabra de 5 letras. ¡Intenta adivinarla!
                Si ves 💔 debajo de una letra, significa que mi palabra no contiene esa letra.
                Si ves 🧡, la letra está en la palabra pero no en esa posición.
                Si ves 💚, la letra está en la posición correcta.
                
                ¡Si consigues cinco 💚 corazones, ganas!
                
                Escribe tu primera palabra de 5 letras:""";
    }

    @Override
    public String getPlayAgainMessage() {
        return "¿Quieres jugar otra vez?";
    }

    @Override
    public String getStartGameFirstMessage() {
        return "Por favor, inicia primero un juego con /start";
    }

    @Override
    public String getThanksMessage() {
        return  "¡Gracias por jugar! 👋";
    }

    @Override
    public String getErrorMessage() {
        return "Lo siento, ha ocurrido un error. Por favor, inténtalo de nuevo.";
    }
}
