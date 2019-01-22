package cn.nju.edu.se.dao;

import cn.nju.edu.se.entity.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;

public interface AdminDao extends JpaRepository<Admin, Integer>, JpaSpecificationExecutor<Admin>, Serializable {

    Admin save(Admin admin);

    Admin findById(int id);

    Admin findByNickName(String nickName);

    @Transactional
    void deleteByNickName(String nickName);
}
