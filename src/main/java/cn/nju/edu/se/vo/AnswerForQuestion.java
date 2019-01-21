package cn.nju.edu.se.vo;

import cn.nju.edu.se.entity.Answer;
import cn.nju.edu.se.entity.Question;
import cn.nju.edu.se.entity.User;

/**
 * Created by PandaLin on 2019/1/21.
 */
public class AnswerForQuestion implements Comparable<AnswerForQuestion> {

    private User user;

    private Question question;

    private Answer answer;

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

    public Answer getAnswer() {
        return answer;
    }

    public void setAnswer(Answer answer) {
        this.answer = answer;
    }

    @Override
    public int compareTo(AnswerForQuestion o) {
        return o.getAnswer().getTime().compareTo(this.getAnswer().getTime());
    }
}
