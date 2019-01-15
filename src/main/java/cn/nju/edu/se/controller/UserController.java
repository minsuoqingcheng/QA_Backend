package cn.nju.edu.se.controller;

import cn.nju.edu.se.entity.User;
import cn.nju.edu.se.form.UserForm;
import cn.nju.edu.se.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    private UserService userService;


    @RequestMapping(value = "/{nickName}", method = RequestMethod.GET)
    public User getUserByNickName(@PathVariable String nickName){
        return userService.getUserByNickName(nickName);
    }

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


    /**
     * 查询用户列表方法
     */
    @RequestMapping(value ="/list", method = RequestMethod.GET)
    public List<User> list(){
        return userService.getUserList();
    }

    /**
     * 更新用户信息
     * @param requestBody
     * @return
     */
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

    /**
     * 删除用户
     * @param requestBody
     * @return
     */
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public List<User> deleteUserByNickName(@RequestBody UserForm requestBody){
        String nickName = requestBody.getNickName();
        return userService.deleteUserByNickName(nickName);
    }


}
