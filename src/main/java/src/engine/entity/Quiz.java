package src.engine.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="Quizzes")
public class Quiz {
    @Id
    @Column(name="ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotBlank
    @Column(name="TITLE")
    private String title;

    @NotBlank
    @Column(name="TEXT")
    private String text;

    @NotNull
    @Column(name="OPTIONS")
    @ElementCollection
    private List<String> options;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Column(name="ANSWERS")
    @ElementCollection
    @NotNull
    private List<Integer> answer;

    public Quiz() {
        this.answer = new ArrayList<>();
    }

    public Quiz(String title, String text, List<String> options) {
        this.title = title;
        this.text = text;
        this.options = options;
        this.answer = new ArrayList<Integer>();
    }

    public Quiz(String title, String text, List<String> options, List<Integer> answer) {
        this.title = title;
        this.text = text;
        this.options = options;
        this.answer = answer;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setId(int id) { this.id = id; }

    public List<String> getOptions() {
        return options;
    }

    public void setOptions(List<String> options) { this.options = options; }
    @JsonIgnore
    public List<Integer> getAnswer() { return answer; }
    @JsonProperty
    public void setAnswer(List<Integer> answer) { this.answer = answer; }

    public int getId() { return id; }

    @JsonIgnore
    public int getAnswersAmount() {
        return answer.size();}

}
