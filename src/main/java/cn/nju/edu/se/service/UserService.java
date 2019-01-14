package cn.nju.edu.se.service;


import cn.nju.edu.se.entity.User;

import java.util.List;

public interface UserService {

    User getUserByNickName(String nickName);

    User addUser(User user);

    List<User> getUserList();

    User updateUser(User user);

    List<User> deleteUserByNickName(String nickName);

    User getUserById(int userId);

}
