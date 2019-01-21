package cn.nju.edu.se.entity;

import javax.persistence.*;

/**
 * Created by PandaLin on 2019/1/21.
 */
@Entity
@Table(name = "answer")
public class Answer implements Comparable<Answer> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String content;

    private String time;

    // 0 表示回答不匿名， 1表示回答匿名
    private int state;
    // 1表示回答被删除，0表示回答未被删除
    private int hide;

    @ManyToOne
    @JoinColumn
    private User user;

    @ManyToOne
    @JoinColumn
    private Question question;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public int getHide() {
        return hide;
    }

    public void setHide(int hide) {
        this.hide = hide;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    @Override
    public int compareTo(Answer o) {
        return o.getTime().compareTo(this.time);
    }
}