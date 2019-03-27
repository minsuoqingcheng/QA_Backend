package cn.nju.edu.se.service;

import cn.nju.edu.se.QAApplication;
import cn.nju.edu.se.entity.User;
import cn.nju.edu.se.entity.UserMap;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

@SpringBootTest(classes = QAApplication.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class UserMapServiceTest {

    private static final int USER_A_ID = 12;
    private static final int USER_B_ID = 13;

    @Autowired
    private UserMapService userMapService;



    @Test
    public void addFocusUser() {
        UserMap userMap = userMapService.addFocusUser(USER_A_ID, USER_B_ID);
        Assert.assertTrue(userMap.getUser().getId() == USER_A_ID && userMap.getFocusedUser().getId() == USER_B_ID);
    }

    @Test
    public void ignoreUser() {
        userMapService.ignoreUser(USER_A_ID, USER_B_ID);
    }
}