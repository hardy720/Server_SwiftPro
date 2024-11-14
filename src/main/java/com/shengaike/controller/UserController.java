package com.shengaike.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.shengaike.entity.FriendList;
import com.shengaike.entity.User;
import com.shengaike.form.LoginForm;
import com.shengaike.service.FriendListService;
import com.shengaike.service.UserService;
import com.shengaike.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Hardy
 * @since 2024-10-18
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;
    @GetMapping("/login")
    public Result login(@Valid LoginForm loginForm){
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_name", loginForm.getUserName());
        User user = this.userService.getOne(queryWrapper);
        if(user == null){
            return Result.error("用户名错误");
        }
        // 判断密码是否正确
        if (!user.getPassWord().equals(loginForm.getPassWord())){
            return Result.error("密码错误");
        }

        Map<String,Object> map = new HashMap<>();
        map.put("userInfo",user);
        return Result.ok().put("status","success").put("data", map);
    }

    @PostMapping("/addUser")
    public Result insertUser(@Valid User user){
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_name",user.getUserName());
        User user1 = this.userService.getOne(queryWrapper);

        if (user1 != null){
            return Result.ok().put("status","success").put("data", user1);
        }

        boolean save = this.userService.save(user);
        QueryWrapper<User> queryWrapper1 = new QueryWrapper<>();
        queryWrapper1.eq("user_name",user.getUserName());
        User user2 = this.userService.getOne(queryWrapper1);
        if(!save) return Result.error("创建好友失败");
        return Result.ok().put("status","success").put("data", user2);
    }
}
