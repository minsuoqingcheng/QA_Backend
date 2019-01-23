package cn.nju.edu.se.service;

import cn.nju.edu.se.QAApplication;
import cn.nju.edu.se.entity.UserQuestionMap;
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
public class UserQuestionMapServiceTest {

    private static final int USER_ID = 10;
    private static final int QUESTION_A_ID = 11;
    private static final int QUESTION_B_ID = 11;

    @Autowired
    UserQuestionMapService userQuestionMapService;


    @Test
    public void testFocusQuestion() {
        UserQuestionMap map = userQuestionMapService.focusQuestion(USER_ID, QUESTION_A_ID);
        Assert.assertTrue(map.getId()!=-1);
    }

    @Test
    public void testNotFocusQuestion() {
        UserQuestionMap map = userQuestionMapService.focusQuestion(USER_ID, QUESTION_B_ID);
        userQuestionMapService.notFocusQuestion(USER_ID, QUESTION_B_ID);
        Assert.assertTrue(userQuestionMapService.checkFocus(USER_ID,QUESTION_B_ID) == Boolean.FALSE);

    }

    @Test
    public void testCheckFocus() {

        Assert.assertTrue(userQuestionMapService.checkFocus(USER_ID,QUESTION_B_ID) == Boolean.FALSE);
    }

    @Test
    public void testUserFocusedQuestions() {
        UserQuestionMap map = userQuestionMapService.focusQuestion(USER_ID, QUESTION_B_ID);
        Assert.assertTrue(userQuestionMapService.userFocusedQuestions(USER_ID).size()>-1);
        userQuestionMapService.notFocusQuestion(USER_ID, QUESTION_B_ID);
    }
}