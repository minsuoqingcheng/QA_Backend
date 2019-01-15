package cn.nju.edu.se.service;

import cn.nju.edu.se.QAApplication;
import cn.nju.edu.se.entity.User;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@SpringBootTest(classes = QAApplication.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class UserServiceTest {

    @Autowired
    private UserService userService;

    @Test
    public void addUser() {
        User user = new User();
        user.setNickName("$test$*()");
        user.setGender(0);
        User saveUser = userService.addUser(user);
        Assert.assertTrue(saveUser.getId() != -1);
    }

    @Test
    public void getUserByNickName() {
        User test = userService.getUserByNickName("$test$*()");
        Assert.assertTrue(test.getId() != -1);
    }


    @Test
    public void getUserList() {
        List<User> userList = userService.getUserList();
        Assert.assertTrue(userList.size() > 0);
    }

    @Test
    public void updateUser() {
        User test = userService.getUserByNickName("$test$*()");
        test.setGender(1);
        test.setNickName("$test$*(2)");
        User user = userService.updateUser(test);
        Assert.assertTrue(user.getGender() == 1);
        Assert.assertTrue("$test$*(2)".equals(user.getNickName()));
    }


    @Test
    public void getUserById() {
        User test = userService.getUserByNickName("$test$*(2)");
        int id = test.getId();
        User userById = userService.getUserById(id);
        Assert.assertTrue(userById != null && userById.getId() == id);
    }

    @Test
    public void deleteUserByNickName() {
        List<User> users = userService.deleteUserByNickName("$test$*(2)");
        boolean exclude = (users ==  null) || users.stream().noneMatch(u -> "$test$*(2)".equals(u.getNickName()));
        Assert.assertTrue(exclude);
    }


}