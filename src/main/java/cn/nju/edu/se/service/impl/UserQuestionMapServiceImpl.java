package cn.nju.edu.se.service.impl;

import cn.nju.edu.se.dao.QuestionDAO;
import cn.nju.edu.se.dao.UserDAO;
import cn.nju.edu.se.dao.UserQuestionMapDAO;
import cn.nju.edu.se.entity.Question;
import cn.nju.edu.se.entity.User;
import cn.nju.edu.se.entity.UserQuestionMap;
import cn.nju.edu.se.service.UserQuestionMapService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by PandaLin on 2019/1/23.
 */
@Service
public class UserQuestionMapServiceImpl implements UserQuestionMapService {
    @Autowired
    private UserQuestionMapDAO userQuestionMapDao;

    @Autowired
    private QuestionDAO questionDao;

    @Autowired
    private UserDAO userDao;


    @Override
    public UserQuestionMap focusQuestion(int userId, int questionId) {
        UserQuestionMap userQuestionMap = new UserQuestionMap();
        Question question = questionDao.findById(questionId);
        User user = userDao.findById(userId);
        userQuestionMap.setQuestion(question);
        userQuestionMap.setUser(user);
        return userQuestionMapDao.save(userQuestionMap);
    }

    @Override
    public void notFocusQuestion(int userId, int questionId) {
        userQuestionMapDao.deleteUserQuestionMapByUserIdAndQuestionId(userId,questionId);
    }

    @Override
    public Boolean checkFocus(int userId, int questionId) {
        List<UserQuestionMap> maps = userQuestionMapDao.findAllByUserIdAndQuestionId(userId, questionId);
        return maps.size()>0;
    }

    @Override
    public List<Question> userFocusedQuestions(int userId) {
        List<Question> questions = new ArrayList<>();
        List<UserQuestionMap> maps = userQuestionMapDao.findAllByUserId(userId);
        for(UserQuestionMap map: maps){
            Question question = map.getQuestion();
            if(question.getHide()==0){
                questions.add(question);
            }

        }

        return questions;
    }
}
