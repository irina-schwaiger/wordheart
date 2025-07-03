package messages;

import model.Language;

import java.util.Map;

    public class MessageProvider {
        private static final Map<Language, GameMessages> MESSAGES = Map.of(
                Language.ENGLISH, new EnglishMessages(),
                Language.GERMAN, new GermanMessages(),
                Language.FRENCH, new FrenchMessages(),
                Language.SPANISH, new SpanishMessages()
        );

        public static GameMessages forLanguage(Language lang) {
            return MESSAGES.getOrDefault(lang, new EnglishMessages());
        }
    }

