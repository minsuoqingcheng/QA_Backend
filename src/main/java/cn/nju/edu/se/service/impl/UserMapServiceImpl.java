package cn.nju.edu.se.service.impl;

import cn.nju.edu.se.dao.UserDAO;
import cn.nju.edu.se.dao.UserMapDAO;
import cn.nju.edu.se.entity.User;
import cn.nju.edu.se.entity.UserMap;
import cn.nju.edu.se.service.UserMapService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class UserMapServiceImpl implements UserMapService {

    @Autowired
    private UserMapDAO userMapDao;

    @Autowired
    private UserDAO userDao;

    @Override
    public UserMap addFocusUser(int userId, int focusedUserId) {
        User user = userDao.findById(userId);
        User focused = userDao.findById(focusedUserId);
        UserMap userMap = new UserMap();
        userMap.setUser(user);
        userMap.setFocusedUser(focused);

        return userMapDao.save(userMap);
    }


    @Override
    public void ignoreUser(int userId, int focusedUserId) {
        userMapDao.deleteUserMapByUserIdAndFocusedUserId(userId, focusedUserId);
    }

    @Override
    public Boolean checkUserFocused(int userId, int focusedUserId) {
        List maps = userMapDao.getAllByUserIdAndFocusedUserId(userId,focusedUserId);
        return maps.size()>0;
    }


}
