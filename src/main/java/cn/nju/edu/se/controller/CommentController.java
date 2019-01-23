package cn.nju.edu.se.controller;

import cn.nju.edu.se.entity.Answer;
import cn.nju.edu.se.entity.Comment;
import cn.nju.edu.se.entity.User;
import cn.nju.edu.se.form.AnswerID;
import cn.nju.edu.se.form.CommentForm;
import cn.nju.edu.se.form.UserID;
import cn.nju.edu.se.service.AnswerService;
import cn.nju.edu.se.service.CommentService;
import cn.nju.edu.se.service.UserService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/comment")
public class CommentController {

    @Autowired
    private CommentService commentService;
    @Autowired
    private UserService userService;
    @Autowired
    private AnswerService answerService;


    @ApiOperation(value = "添加评论", response = Comment.class)
    @RequestMapping(value = "/submit", method = RequestMethod.POST)
    public Comment submitComment(@RequestBody CommentForm requestBody){
        int userId = requestBody.getUserId();
        User user = userService.getUserById(userId);
        int answerId = requestBody.getAnswerId();
        Answer answer = answerService.getAnswerById(answerId);
        String content = requestBody.getContent();
        String time = requestBody.getTime();
        Comment comment = new Comment();
        comment.setUser(user);
        comment.setAnswer(answer);
        comment.setContent(content);
        comment.setTime(time);
        return commentService.submitComment(comment);
    }


    @ApiOperation(value = "获取回答的评论", response = Comment.class, responseContainer = "List")
    @RequestMapping(value = "/answer", method = RequestMethod.POST)
    public List<Comment> getAnswerComments(@RequestBody AnswerID requestBody){
        int answerId = Integer.parseInt(requestBody.getAnswerId());
        return commentService.getAnswerComments(answerId);
    }


    @ApiOperation(value = "获取用户的评论", response = Comment.class, responseContainer = "List")
    @RequestMapping(value = "/user", method = RequestMethod.POST)
    public List<Comment> getCommentForUser(@RequestBody UserID requestBody){
        int userId = Integer.parseInt(requestBody.getUserId());
        return commentService.getCommentForUser(userId);
    }

}
