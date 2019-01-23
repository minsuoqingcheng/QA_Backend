package cn.nju.edu.se.service;

import cn.nju.edu.se.entity.Comment;

import java.util.List;


public interface CommentService {

    Comment submitComment(Comment comment);

    //查看对该用户的回答的所有评论，按时间倒排
    List<Comment> getCommentForUser(int userId);

    List<Comment> getAnswerComments(int answerId);

}
