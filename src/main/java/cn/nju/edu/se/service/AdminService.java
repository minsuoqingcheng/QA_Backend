package cn.nju.edu.se.service;

import cn.nju.edu.se.entity.Admin;
import com.google.common.annotations.VisibleForTesting;

public interface AdminService {

    Admin addAdmin(Admin admin);


    @VisibleForTesting
    void deleteAdmin(String nickName);
}
