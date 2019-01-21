package cn.nju.edu.se.service;


import cn.nju.edu.se.entity.UserMap;


public interface UserMapService {

    //新增关注用户
    UserMap addFocusUser(int userId, int focusedUserId);


    void ignoreUser(int userId, int focusedUserId);

}
