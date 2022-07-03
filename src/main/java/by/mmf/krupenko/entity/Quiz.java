package by.mmf.krupenko.entity;

import java.time.LocalDate;
import java.util.Objects;

public class Quiz {
    private String id;
    private String name;
    private String teacherEmail;
    private LocalDate creationDate;
    private String link;
    private int countOfAnswers;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTeacherEmail() {
        return teacherEmail;
    }

    public void setTeacherEmail(String teacherEmail) {
        this.teacherEmail = teacherEmail;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public int getCountOfAnswers() {
        return countOfAnswers;
    }

    public void setCountOfAnswers(int countOfAnswers) {
        this.countOfAnswers = countOfAnswers;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Quiz quiz = (Quiz) o;
        return Objects.equals(id, quiz.id) && Objects.equals(name, quiz.name) &&
                Objects.equals(teacherEmail, quiz.teacherEmail) &&
                Objects.equals(creationDate, quiz.creationDate) &&
                countOfAnswers == quiz.countOfAnswers;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, teacherEmail, creationDate, countOfAnswers);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Quiz {");
        sb.append("id=").append(id);
        sb.append(", name='").append(name).append('\'');
        sb.append(", teacherEmail=").append(teacherEmail);
        sb.append(", creationDate=").append(creationDate);
        sb.append(", countOfAnswers=").append(countOfAnswers);
        sb.append('}');
        return sb.toString();
    }
}
