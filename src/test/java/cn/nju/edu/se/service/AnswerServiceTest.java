package cn.nju.edu.se.service;

import cn.nju.edu.se.QAApplication;
import cn.nju.edu.se.entity.Answer;
import cn.nju.edu.se.entity.Question;
import cn.nju.edu.se.entity.User;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;



/**
 * Created by PandaLin on 2019/1/23.
 */
@SpringBootTest(classes = QAApplication.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class AnswerServiceTest {

    @Autowired
    private QuestionService questionService;

    @Autowired
    private UserService userService;

    @Autowired
    private AnswerService answerService;

    private Question testQuestion;
    private User testUser;
    private Answer testAnswer;

    private void initial(){
        testQuestion = new Question();
        testQuestion.setTitle("This is a Question?");
        testQuestion.setContent("To be or not to be");
        testQuestion.setHide(0);
        testQuestion.setState(1);
        testQuestion.setTime("2018-01-08 10:54");

        User user = new User();
        user.setNickName("$testQuestion$*()");
        user.setGender(0);
        testUser = userService.addUser(user);


        testQuestion.setUser(testUser);
        testQuestion = questionService.submitQuestion(testQuestion);

        Answer answer = new Answer();
        answer.setQuestion(testQuestion);
        answer.setHide(0);
        answer.setContent("This is an testAnswer.");
        answer.setState(1);
        answer.setTime("2018-01-08 10:55");
        answer.setUser(testUser);

        testAnswer = answerService.submitAnswer(answer);

    }

    @Test
    public void testSubmitAnswer() {

        initial();
        Assert.assertTrue(testAnswer.getId()!=-1);

    }

    @Test
    public void testListAllAnswersForQuestion() {
        initial();
        Assert.assertTrue(answerService.listAllAnswersForQuestion(testQuestion.getId()).size()>0);
    }

    @Test
    public void testListFocusedUserAnswers() {
        initial();
        Assert.assertTrue(answerService.listFocusedUserAnswers(testUser.getId()).size()>-1);
    }

    @Test
    public void testDelAnswer() {
        initial();
        Answer temp = answerService.delAnswer(testAnswer.getId());
        Assert.assertTrue(temp.getHide() == 1);
    }

    @Test
    public void testListAllAnswersForUser() {
        initial();
        Assert.assertTrue(answerService.listAllAnswersForUser(testUser.getId()).size()>0);
    }

    @Test
    public void testGetAnswerById() {
        initial();
        Assert.assertTrue(answerService.getAnswerById(testAnswer.getId()).getId() != -1);
    }
}