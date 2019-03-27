package cn.nju.edu.se.service;

import cn.nju.edu.se.QAApplication;
import cn.nju.edu.se.entity.Answer;
import cn.nju.edu.se.entity.Comment;
import cn.nju.edu.se.entity.User;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.*;

@SpringBootTest(classes = QAApplication.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class CommentServiceTest {

    private static final int userId = 15;
    private static final int answerId = 10;

    @Autowired
    private CommentService commentService;
    @Autowired
    private UserService userService;
    @Autowired
    private AnswerService answerService;


    @Test
    public void submitComment() {

        User user = userService.getUserById(userId);
        Answer answer = answerService.getAnswerById(answerId);
        String content = "评论测试";
        String time = "";
        Comment comment = new Comment();
        comment.setUser(user);
        comment.setAnswer(answer);
        comment.setContent(content);
        comment.setTime(time);
        Comment submitComment = commentService.submitComment(comment);
        Assert.assertTrue(submitComment.getId() != 0);
    }

    @Test
    public void getCommentForUser() {
        List<Comment> commentForUser = commentService.getCommentForUser(userId);
        Assert.assertTrue(commentForUser.size() > 0);
    }

    @Test
    public void getAnswerComments() {
        List<Comment> answerComments = commentService.getAnswerComments(answerId);
        Assert.assertTrue(answerComments.size() > 0);
    }
}