package cn.nju.edu.se.dao;

import cn.nju.edu.se.entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;

/**
 * Created by PandaLin on 2019/1/18.
 */
public interface QuestionDAO extends JpaRepository<Question, Integer>, JpaSpecificationExecutor<Question>, Serializable {

    @Transactional
    Question save(Question question);

    List<Question> findAllByUserId(int userId);

    List<Question> findAllByTitleLike(String keyword);

    List<Question> findAllByContentLike(String keyword);

    Question findById(int id);

    @Modifying
    @Query("update Question q set q.hide = 1 where q.id = :id")
    @Transactional
    Question setHide( @Param(value = "id") int id);

    @Modifying
    @Query("update Question q set q.state = 1 where q.id = :id")
    @Transactional
    Question setOpen( @Param(value = "id") int id);

    @Modifying
    @Query("update Question q set q.state = 0 where q.id = :id")
    @Transactional
    Question setClose( @Param(value = "id") int id);

}
