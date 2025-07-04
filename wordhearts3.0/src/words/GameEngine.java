package words;

public class GameEngine {
    public static final int maxAttempts = 8;

    public static String checkGuess(String guess, String target) {
        guess  = guess.toLowerCase();
        target = target.toLowerCase();

        String[] result = new String[guess.length()];
        boolean[] usedTarget = new boolean[target.length()];


        for (int i = 0; i < guess.length(); i++) {
            if (i < target.length() && guess.charAt(i) == target.charAt(i)) {
                result[i] = "💚";
                usedTarget[i] = true;
            } else {
                result[i] = null;
            }
        }

        for (int i = 0; i < guess.length(); i++) {
            if (result[i] != null) continue;

            char c = guess.charAt(i);
            int idx = indexOfUnused(target, c, usedTarget);
            if (idx >= 0) {
                result[i] = "🧡";
                usedTarget[idx] = true;
            } else {
                result[i] = "💔";
            }
        }

        return String.join("", result);
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
        final int greenCp = 0x1F49A;
        return feedback
                .codePoints()
                .allMatch(cp -> cp == greenCp);
    }
}