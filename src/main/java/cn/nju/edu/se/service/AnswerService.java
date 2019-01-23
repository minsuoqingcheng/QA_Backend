package cn.nju.edu.se.service;

import cn.nju.edu.se.entity.Answer;

import java.util.List;

/**
 * Created by PandaLin on 2019/1/23.
 */
public interface AnswerService {

    Answer submitAnswer(Answer answer);

    List<Answer> listAllAnswersForQuestion(int questionId);

    //列出当前用户关注的用户的最新回答
    List<Answer> listFocusedUserAnswers(int userId);

    //删除回答，置hide为1
    Answer delAnswer(int answerId);

    //列出当前用户的所有回答
    List<Answer> listAllAnswersForUser(int userId);

    Answer getAnswerById(int answerId);

}