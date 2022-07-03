package by.mmf.krupenko.model.view;

import by.mmf.krupenko.entity.Question;

import java.util.Map;
import java.util.Objects;

public class ResultsView {
    /**
     * map where key is a question, values are map where key is answer and value is the number of such responses
     */
    Map<Question, Map<String, Integer>> results;
    String quizTitle;

    public Map<Question, Map<String, Integer>> getResults() {
        return results;
    }

    public void setResults(Map<Question, Map<String, Integer>> results) {
        this.results = results;
    }

    public String getQuizTitle() {
        return quizTitle;
    }

    public void setQuizTitle(String quizTitle) {
        this.quizTitle = quizTitle;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ResultsView that = (ResultsView) o;
        return Objects.equals(results, that.results) && Objects.equals(quizTitle, that.quizTitle);
    }

    @Override
    public int hashCode() {
        return Objects.hash(results, quizTitle);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("ResultsView{");
        sb.append("results=").append(results);
        sb.append(", quizTitle='").append(quizTitle).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
