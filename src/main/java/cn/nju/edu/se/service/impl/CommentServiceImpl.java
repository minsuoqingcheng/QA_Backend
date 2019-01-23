package cn.nju.edu.se.service.impl;

import cn.nju.edu.se.dao.AnswerDAO;
import cn.nju.edu.se.dao.CommentDAO;
import cn.nju.edu.se.entity.Answer;
import cn.nju.edu.se.entity.Comment;
import cn.nju.edu.se.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentDAO commentDAO;
    @Autowired
    private AnswerDAO answerDao;


    @Override
    public Comment submitComment(Comment comment) {
        return commentDAO.save(comment);
    }

    @Override
    public List<Comment> getAnswerComments(int answerId) {
        return commentDAO.findAllByAnswerId(answerId);
    }

    @Override
    public List<Comment> getCommentForUser(int userId) {
        List<Comment> comments = new ArrayList<>();
        //先找到自己有哪些回答
        //再找每一个回答的所有评论
        List<Answer> answers = answerDao.findAllByUserId(userId);
        for(Answer answer : answers){
            //自己的每一个回答
            List<Comment> answerComments = commentDAO.findAllByAnswerId(answer.getId());
            if(null != answerComments && answerComments.size() > 0){
                Collections.sort(answerComments);
                comments.add(answerComments.get(0));
            }
        }
        return comments;
    }
}
