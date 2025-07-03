package words;

public class GameEngine {
    public static final int maxAttempts = 8;

    public static String checkGuess(String guess, String target) {
        guess  = guess.toLowerCase();
        target = target.toLowerCase();

        StringBuilder result = new StringBuilder();
        boolean[] usedTarget = new boolean[target.length()];

        // 1) Grünen Herz-Fall
        for (int i = 0; i < guess.length(); i++) {
            if (i < target.length() && guess.charAt(i) == target.charAt(i)) {
                result.append("💚");
                usedTarget[i] = true;
            } else {
                result.append("_"); // Platzhalter
            }
        }


        for (int i = 0; i < guess.length(); i++) {
            if (result.charAt(i) != '_') continue;

            char c = guess.charAt(i);
            int idx = indexOfUnused(target, c, usedTarget);
            if (idx >= 0) {
                result.setCharAt(i, '🧡');
                usedTarget[idx] = true;
            } else {
                result.setCharAt(i, '💔');
            }
        }

        return result.toString();
    }

    private static int indexOfUnused(String target, char c, boolean[] used) {
        for (int j = 0; j < target.length(); j++) {
            if (!used[j] && target.charAt(j) == c) {
                return j;
            }
        }
        return -1;
    }

    public static boolean isCorrect(String feedback) {
        return feedback.chars()
                .allMatch(ch -> ch == '💚');
    }
}