package cn.nju.edu.se.service;

import cn.nju.edu.se.QAApplication;
import cn.nju.edu.se.entity.Question;
import cn.nju.edu.se.entity.User;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.testng.annotations.BeforeMethod;

import java.util.List;


/**
 * Created by PandaLin on 2019/1/18.
 */
@SpringBootTest(classes = QAApplication.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class QuestionServiceTest {

    @Autowired
    private QuestionService questionService;

    @Autowired
    private UserService userService;

    private Question testQuestion;
    private User testUser;

    private void initial(){
        testQuestion = new Question();
        testQuestion.setTitle("This is a Question?");
        testQuestion.setContent("To be or not to be");
        testQuestion.setHide(0);
        testQuestion.setState(1);
        testQuestion.setTime("2018-01-08 10:53");

        User user = new User();
        user.setNickName("$testQuestion$*()");
        user.setGender(0);
        testUser = userService.addUser(user);


        testQuestion.setUser(testUser);
        testQuestion = questionService.submitQuestion(testQuestion);
    }


    @Test
    public void testSubmitQuestion() {
        initial();
        Assert.assertTrue(testQuestion.getId() != -1);
    }

    @Test
    public void testGetOneQuestion() {
        initial();

        Question question = questionService.getOneQuestion(testQuestion.getId());
        Assert.assertTrue(question.getId() == testQuestion.getId());
    }



    @Test
    public void testControlQuestion() {
        initial();
        Question question = questionService.controlQuestion(testQuestion.getId(), 0);
        Assert.assertTrue(question.getState() == 0);

    }

    @Test
    public void testHideQuestion() {
        initial();
        Question question = questionService.hideQuestion(testQuestion.getId());
        Assert.assertTrue(question.getHide() == 1);
    }

    @Test
    public void testListAllQuestions() {
        initial();
        List<Question> questions = questionService.listAllQuestions();
        Assert.assertTrue(questions.size() > 0);
    }

    @Test
    public void testListUserSubmittedQuestions() {
        initial();
        List<Question> questions = questionService.listUserSubmittedQuestions(testUser.getId());
        Assert.assertTrue(questions.size() > 0);
    }

    @Test
    public void testGetQuestionById() {
        initial();
        Question question = questionService.getQuestionById(testQuestion.getId());
        Assert.assertTrue(question.getId() == testQuestion.getId());
    }


}