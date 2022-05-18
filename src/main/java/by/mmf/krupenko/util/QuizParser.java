package by.mmf.krupenko.util;

import by.mmf.krupenko.entity.Question;
import by.mmf.krupenko.entity.QuestionType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static by.mmf.krupenko.resource.CommonConsts.COMMA_STRING;
import static by.mmf.krupenko.resource.CommonConsts.SEMICOLON_STRING;

public class QuizParser {
    private static Logger logger = LogManager.getLogger();

    public QuizParser() {
    }

    public static Map<Question, List<String>> parseCreatedQuiz(String input) {
        String[] questions = getArrayFromString(input);
        Map<Question, List<String>> map = new HashMap<>();
        Pattern pattern = Pattern.compile("\"\\{type:([\\w]+),title:([\\w\\p{Punct} ]*)}\":\\[([\\w\\p{Punct} ]*)]");
        for (String questionString : questions) {
            Matcher matcher = pattern.matcher(questionString);
            matcher.matches();
            Question question = new Question(matcher.group(2), QuestionType.getQuestionType(matcher.group(1)).get());
            List<String> valuesList = createAnswers(matcher.group(3));
            map.put(question, valuesList);
        }
        return map;
    }

    private static String[] getArrayFromString(String input) {
        input = input.substring(1, input.length() - 1).replaceAll("\\\\\"", "");
        input = input.replaceAll("],\"", "];\"");
        return input.split(SEMICOLON_STRING);
    }

    private static List<String> createAnswers(String answersString) {
        List<String> answers = new ArrayList<>();
        if (!answersString.isEmpty()) {
            answers = Arrays.stream(answersString.split(COMMA_STRING)).collect(Collectors.toList());
            for (int i = 0; i < answers.size(); i++) {
                String current = answers.get(i);
                answers.set(i, current.substring(1, current.length() - 1));
            }
        }
        return answers;
    }

    public static Map<Integer, List<String>> parseAnswers(String input) {
        String[] answers = getArrayFromString(input);
        Map<Integer, List<String>> map = new HashMap<>();
        Pattern pattern = Pattern.compile("\"([0-9]+)\":\\[([\\w\\p{Punct} ]*)]");
        for (String answerString : answers) {
            Matcher matcher = pattern.matcher(answerString);
            matcher.matches();
            map.put(Integer.parseInt(matcher.group(1)), createAnswers(matcher.group(2)));
        }
        return map;
    }

}
