package words;

import model.Language;
import java.util.Random;


public class WordProvider {
    private static final Random RANDOM = new Random();

    public static String getRandomWord(Language lang) {
        return switch (lang) {
            case ENGLISH -> randomFromEnum(EnglishWords.values());
            case GERMAN  -> randomFromEnum(GermanWords.values());
            case FRENCH  -> randomFromEnum(FrenchWords.values());
            case SPANISH -> randomFromEnum(SpanishWords.values());
        };
    }

    private static <E extends Enum<E>> String randomFromEnum(E[] values) {
        int idx = RANDOM.nextInt(values.length);
        return values[idx].name().toLowerCase();
    }
}