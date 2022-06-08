package by.mmf.krupenko.entity;

import java.util.Arrays;
import java.util.Optional;

public enum QuestionType {
    TEXT("text"),
    ONE("one"),
    MULTIPLE("multiple");

    private final String value;

    QuestionType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static Optional<QuestionType> getQuestionType(String type) {
        return Arrays.stream(QuestionType.values()).filter(x -> x.getValue().equals(type)).findFirst();
    }
}
