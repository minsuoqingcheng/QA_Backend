package cn.nju.edu.se.dao;

import cn.nju.edu.se.entity.Answer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;

/**
 * Created by PandaLin on 2019/1/21.
 */
public interface AnswerDAO extends JpaRepository<Answer, Integer>, JpaSpecificationExecutor<Answer>, Serializable {

    @Transactional
    Answer save(Answer answer);

    List<Answer> findAllByQuestionId(int questionId);

    List<Answer> findAllByUserId(int userId);

    Answer findById(int answerId);
}
