package cn.nju.edu.se;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class QAApplication {

    private static final Logger LOGGER = LoggerFactory.getLogger(QAApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(QAApplication.class, args);
        LOGGER.info("Q&A service is running!");
        LOGGER.info("test");
    }


}
