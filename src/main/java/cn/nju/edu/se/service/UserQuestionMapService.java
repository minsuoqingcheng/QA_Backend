package cn.nju.edu.se.service;

import cn.nju.edu.se.entity.Question;
import cn.nju.edu.se.entity.UserQuestionMap;

import java.util.List;

/**
 * Created by PandaLin on 2019/1/23.
 */
public interface UserQuestionMapService {
    public UserQuestionMap focusQuestion(int userId, int questionId);

    public void notFocusQuestion(int userId, int questionId);

    public Boolean checkFocus(int userId, int questionId);

    List<Question> userFocusedQuestions(int userId);
}
