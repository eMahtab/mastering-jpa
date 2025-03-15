package net.mahtabalam;


import jakarta.persistence.*;

@Entity
@Table(name="questions")
public class Question {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long id;

    private String statement;

    @Convert(converter = AnswerConverter.class)
    private Answer answer;

    public Question(String statement, Answer answer) {
        this.statement = statement;
        this.answer = answer;
    }

    public long getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Question{" +
                "id=" + id +
                ", statement='" + statement + '\'' +
                ", answer=" + answer +
                '}';
    }
}
