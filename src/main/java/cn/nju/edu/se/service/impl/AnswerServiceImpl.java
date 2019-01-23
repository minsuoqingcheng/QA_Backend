package cn.nju.edu.se.service.impl;

import cn.nju.edu.se.dao.AnswerDAO;
import cn.nju.edu.se.dao.UserMapDAO;
import cn.nju.edu.se.entity.Answer;
import cn.nju.edu.se.entity.User;
import cn.nju.edu.se.entity.UserMap;
import cn.nju.edu.se.service.AnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by PandaLin on 2019/1/23.
 */
@Service
public class AnswerServiceImpl implements AnswerService {

    @Autowired
    private AnswerDAO answerDao;

    @Autowired
    private UserMapDAO userMapDao;

    @Override
    public Answer submitAnswer(Answer answer) {
        //answer就需要判重了
        //这个判重在前端判
        return answerDao.save(answer);
    }

    @Override
    public List<Answer> listAllAnswersForQuestion(int questionId) {
        List<Answer> answers = answerDao.findAllByQuestionId(questionId);
        Collections.sort(answers);
        return answers;
    }

    @Override
    public List<Answer> listFocusedUserAnswers(int userId) {
        List<UserMap> userMaps = userMapDao.getAllByUserId(userId);
        List<Answer> answers = new ArrayList<>();
        for(UserMap userMap:userMaps){
//            AnswerForQuestion answerForQuestion = new AnswerForQuestion();
            //获取该用户关注的每一个人
            User focusedUser = userMap.getFocusedUser();
            List<Answer> focusedUserAnswers = answerDao.findAllByUserId(focusedUser.getId());
            if(focusedUserAnswers.size()>0) {
                Collections.sort(focusedUserAnswers);
                for(Answer answer:focusedUserAnswers){
                    if(answer.getHide()==0){
                        answers.add(answer);
                        break;
                    }
                }

            }
        }

        return answers;
    }

    @Override
    public Answer delAnswer(int answerId) {
        Answer answer = answerDao.findById(answerId);
        answer.setHide(1);
        answerDao.save(answer);
        return answer;
    }

    @Override
    public List<Answer> listAllAnswersForUser(int userId) {
        List<Answer> answers = answerDao.findAllByUserId(userId);
        Collections.sort(answers);
        return answers;
    }

    @Override
    public Answer getAnswerById(int answerId){
        return answerDao.findById(answerId);
    }
}
