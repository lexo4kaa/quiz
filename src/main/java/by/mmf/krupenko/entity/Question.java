package by.mmf.krupenko.entity;

import java.util.Objects;

public class Question {
    private int id;
    private String quizId;
    private String title;
    private boolean isRequired;
    private QuestionType questionType;

    public Question() {
    }

    public Question(String title, boolean isRequired, QuestionType questionType) {
        this.title = title;
        this.isRequired = isRequired;
        this.questionType = questionType;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getQuizId() {
        return quizId;
    }

    public void setQuizId(String quizId) {
        this.quizId = quizId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean getIsRequired() {
        return isRequired;
    }

    public void setIsRequired(boolean required) {
        isRequired = required;
    }

    public QuestionType getQuestionType() {
        return questionType;
    }

    public void setQuestionType(QuestionType questionType) {
        this.questionType = questionType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Question question = (Question) o;
        return id == question.id && Objects.equals(quizId, question.quizId) && isRequired == question.isRequired && Objects.equals(title, question.title) && questionType == question.questionType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, quizId, title, isRequired, questionType);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Question{");
        sb.append("id=").append(id);
        sb.append(", quizId=").append(quizId);
        sb.append(", title='").append(title).append('\'');
        sb.append(", isRequired=").append(isRequired);
        sb.append(", questionType=").append(questionType);
        sb.append('}');
        return sb.toString();
    }
}
