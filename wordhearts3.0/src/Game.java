import java.util.Random;
import java.util.Scanner;

public class Game {
    Scanner sc = new Scanner(System.in);

    Word

    public void startGame(){
        String guess = sc.nextLine();
        Random random = new Random();
        String gameWord = random.Word;
        StringBuilder result = new StringBuilder();
        boolean[] usedWord = new boolean[gameWord.length()];
        boolean[] usedGuess = new boolean[guess.length()];



        // Erst exakte Übereinstimmungen markieren
        for (int i = 0; i < guess.length(); i++) {
            if (i < gameWord.length() && guess.charAt(i) == gameWord.charAt(i)) {
                result.append("💚");
                usedWord[i] = true;
                usedGuess[i] = true;
            }
        }

        // Dann Buchstaben an falscher Position suchen
        for (int i = 0; i < guess.length(); i++) {
            if (!usedGuess[i]) {
                boolean found = false;
                for (int j = 0; j < gameWord.length(); j++) {
                    if (!usedWord[j] && guess.charAt(i) == gameWord.charAt(j)) {
                        result.append("🧡");
                        usedWord[j] = true;
                        found = true;
                        break;
                    }
                }
                if (!found) {
                    result.append("💔");
                }
            }
        }
    }
}
