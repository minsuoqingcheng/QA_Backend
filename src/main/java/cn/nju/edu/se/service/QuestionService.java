package cn.nju.edu.se.service;

import cn.nju.edu.se.entity.Question;
import cn.nju.edu.se.vo.AnswerForQuestion;

import java.util.List;

/**
 * Created by PandaLin on 2019/1/18.
 */
public interface QuestionService {

    Question getOneQuestion(int questionId);

    Question submitQuestion(Question question);

    Question controlQuestion(int questionId, int state);

    Question hideQuestion(int questionId);

    List<Question> listAllQuestions();

    List<Question> listUserSubmittedQuestions(int userId);

    List<AnswerForQuestion> listUserAnsweredQuestions(int userId);

    Question getQuestionById(int questionId);

    List<Question> listPartQuestions(int pageNumber);

    List<Question> search(String keyword);
}
