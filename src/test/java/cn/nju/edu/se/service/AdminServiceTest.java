package cn.nju.edu.se.service;

import cn.nju.edu.se.QAApplication;
import cn.nju.edu.se.entity.Admin;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

@SpringBootTest(classes = QAApplication.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class AdminServiceTest {

    private static final String NICK_NAME = "$testAdmin$*()";

    @Autowired
    private AdminService adminService;

    @Test
    public void addAdmin() {
        Admin admin = new Admin();
        admin.setNickName(NICK_NAME);
        Admin savedAdmin = adminService.addAdmin(admin);
        Assert.assertTrue(savedAdmin.getNickName().equals("$testAdmin$*()"));

    }

    @Test
    public void deleteAdmin() {
        adminService.deleteAdmin(NICK_NAME);
    }
}