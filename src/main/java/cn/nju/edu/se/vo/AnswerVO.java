package cn.nju.edu.se.vo;

import cn.nju.edu.se.entity.Answer;

/**
 * Created by PandaLin on 2019/1/24.
 */
public class AnswerVO {
    Answer answer;
    Boolean answererFocused;

    public Answer getAnswer() {
        return answer;
    }

    public void setAnswer(Answer answer) {
        this.answer = answer;
    }

    public Boolean getAnswererFocused() {
        return answererFocused;
    }

    public void setAnswererFocused(Boolean answererFocused) {
        this.answererFocused = answererFocused;
    }
}
