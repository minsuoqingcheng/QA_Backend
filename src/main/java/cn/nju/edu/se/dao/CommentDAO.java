package cn.nju.edu.se.dao;

import cn.nju.edu.se.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;


public interface CommentDAO extends JpaRepository<Comment,Integer>, JpaSpecificationExecutor<Comment>, Serializable {

    @Transactional
    Comment save(Comment comment);

    List<Comment> findAllByAnswerId(int answerId);
}
