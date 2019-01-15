package cn.nju.edu.se.service.impl;

import cn.nju.edu.se.dao.UserDAO;
import cn.nju.edu.se.entity.User;
import cn.nju.edu.se.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDAO userDao;


    @Override
    public User getUserByNickName(String nickName) {
        List users = userDao.findAllByNickName(nickName);
        if(users.size() == 1){
            return (User)users.get(0);
        }
        //no result
        User user = new User();
        user.setId(-1);
        return user;
    }

    @Override
    public User addUser(User user) {
        String nickName = user.getNickName();
        User existed = getUserByNickName(nickName);
        if(existed.getId()!=-1){
            return existed;
        }

        return userDao.save(user);
    }

    @Override
    public List<User> getUserList() {
        return userDao.findAll();
    }

    @Override
    public User updateUser(User user) {
        String nickName = user.getNickName();
        User existed = getUserByNickName(nickName);
        //存在，即修改
        if(existed.getId()!=-1){
            existed.setGender(user.getGender());
            existed.setNickName(user.getNickName());
            existed.setAvatarUrl(user.getAvatarUrl());
            return userDao.save(existed);
        }
        //不存在，即新建
        return userDao.save(user);
    }

    @Override
    public List<User> deleteUserByNickName(String nickName) {
        userDao.deleteByNickName(nickName);
        return userDao.findAll();
    }

    @Override
    public User getUserById(int userId) {
        return userDao.findById(userId);
    }
}
