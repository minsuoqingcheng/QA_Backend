package cn.nju.edu.se.controller;

import cn.nju.edu.se.entity.User;
import cn.nju.edu.se.entity.UserMap;
import cn.nju.edu.se.form.UserForm;
import cn.nju.edu.se.form.UserMapForm;
import cn.nju.edu.se.service.UserMapService;
import cn.nju.edu.se.service.UserService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private UserMapService userMapService;


    @RequestMapping(value = "/{nickName}", method = RequestMethod.GET)
    @ApiOperation(value = "根据昵称获取用户信息", response = User.class)
    public User getUserByNickName(@PathVariable String nickName){
        return userService.getUserByNickName(nickName);
    }


    @ApiOperation(value = "增加用户接口", response = User.class)
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public User addUser(@RequestBody UserForm requestBody){
        String nickName = requestBody.getNickName();
        String avatarUrl = requestBody.getAvatarUrl();
        int gender = requestBody.getGender();
        User user = new User();
        user.setAvatarUrl(avatarUrl);
        user.setNickName(nickName);
        user.setGender(gender);
        return userService.addUser(user);
    }



    @ApiOperation(value = "查询用户列表方法", response = User.class, responseContainer = "List")
    @RequestMapping(value ="/list", method = RequestMethod.GET)
    public List<User> list(){
        return userService.getUserList();
    }



    @ApiOperation(value = "更新用户信息", response = User.class)
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public User updateUser(@RequestBody UserForm requestBody){
        String nickName = requestBody.getNickName();
        String avatarUrl = requestBody.getAvatarUrl();
        int gender = requestBody.getGender();
        User user = new User();
        user.setAvatarUrl(avatarUrl);
        user.setNickName(nickName);
        user.setGender(gender);
        return userService.updateUser(user);
    }


    @ApiOperation(value = "删除用户", response = User.class, responseContainer = "List")
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public List<User> deleteUserByNickName(@RequestBody UserForm requestBody){
        String nickName = requestBody.getNickName();
        return userService.deleteUserByNickName(nickName);
    }


    @ApiOperation(value = "关注用户", response = UserMap.class)
    @RequestMapping(value = "/focus", method = RequestMethod.POST)
    public UserMap focusUser(@RequestBody UserMapForm requestBody){
        int userId = requestBody.getUserId();
        int focusedUserId = requestBody.getFocusedUserId();
        return userMapService.addFocusUser(userId,focusedUserId);
    }


    @ApiOperation(value = "取消关注")
    @RequestMapping(value = "/ignore", method = RequestMethod.POST)
    public void ignoreUser(@RequestBody UserMapForm requestBody){
        int userId = requestBody.getUserId();
        int focusedUserId = requestBody.getFocusedUserId();
        userMapService.ignoreUser(userId,focusedUserId);
    }


}
