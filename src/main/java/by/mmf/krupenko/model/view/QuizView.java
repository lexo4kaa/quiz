package by.mmf.krupenko.model.view;

import by.mmf.krupenko.entity.Quiz;

import java.util.Objects;

public class QuizView {
    private Quiz quiz;
    private int countOfQuestions;

    public Quiz getQuiz() {
        return quiz;
    }

    public void setQuiz(Quiz quiz) {
        this.quiz = quiz;
    }

    public int getCountOfQuestions() {
        return countOfQuestions;
    }

    public void setCountOfQuestions(int countOfQuestions) {
        this.countOfQuestions = countOfQuestions;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        QuizView quizView = (QuizView) o;
        return countOfQuestions == quizView.countOfQuestions && Objects.equals(quiz, quizView.quiz);
    }

    @Override
    public int hashCode() {
        return Objects.hash(quiz, countOfQuestions);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("QuizView{");
        sb.append("quiz=").append(quiz);
        sb.append(", countOfQuestions=").append(countOfQuestions);
        sb.append('}');
        return sb.toString();
    }
}
