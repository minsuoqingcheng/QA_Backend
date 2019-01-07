package cn.nju.edu.se.service;

import org.springframework.stereotype.Service;

import java.util.Random;


@Service
public class TestService {

    public int generateRandomNumber(int n) {
        return new Random().nextInt(n);
    }

}
