package cn.nju.edu.se.service.impl;

import cn.nju.edu.se.dao.AdminDao;
import cn.nju.edu.se.entity.Admin;
import cn.nju.edu.se.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminDao adminDao;

    @Override
    public Admin addAdmin(Admin admin) {
        Admin ad = adminDao.findByNickName(admin.getNickName());
        if (null == ad) {
            return adminDao.save(admin);
        } else {
            return ad;
        }
    }

    @Override
    public void deleteAdmin(String nickName) {
        adminDao.deleteByNickName(nickName);
    }

}
