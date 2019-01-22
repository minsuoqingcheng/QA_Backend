package cn.nju.edu.se.controller;

import cn.nju.edu.se.entity.Admin;
import cn.nju.edu.se.form.AdminForm;
import cn.nju.edu.se.service.AdminService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @ApiOperation(value = "增加管理员", response = Admin.class)
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public Admin addAdmin(@RequestBody AdminForm requestBody){
        String nickName = requestBody.getNickName();
        String avatarUrl = requestBody.getAvatarUrl();
        Admin admin = new Admin();
        admin.setNickName(nickName);
        admin.setAvatarUrl(avatarUrl);
        return adminService.addAdmin(admin);
    }
}
