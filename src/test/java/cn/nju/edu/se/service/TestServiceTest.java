package cn.nju.edu.se.service;

import org.junit.Assert;
import org.junit.Test;

public class TestServiceTest {

    private TestService testService = new TestService();

    @Test
    public void generateRandomNumber() {
        int randomNumber = testService.generateRandomNumber(10);
        Assert.assertTrue("<= bound", randomNumber <= 10);
    }
}