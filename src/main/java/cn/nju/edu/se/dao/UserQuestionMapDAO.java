package cn.nju.edu.se.dao;

import cn.nju.edu.se.entity.UserQuestionMap;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;

/**
 * Created by PandaLin on 2019/1/23.
 */
public interface UserQuestionMapDAO extends JpaRepository<UserQuestionMap, Integer>, JpaSpecificationExecutor<UserQuestionMap>, Serializable {


    UserQuestionMap save(UserQuestionMap userQuestionMap);

    @Transactional
    void deleteUserQuestionMapByUserIdAndQuestionId(int userId, int questionId);

    List<UserQuestionMap> findAllByUserIdAndQuestionId(int userId, int questionId);

    List<UserQuestionMap> findAllByUserId(int userId);
}
