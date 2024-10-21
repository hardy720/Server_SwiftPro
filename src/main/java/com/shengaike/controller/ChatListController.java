package com.shengaike.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.shengaike.entity.User;
import com.shengaike.form.LoginForm;
import com.shengaike.service.UserService;
import com.shengaike.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
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
 * @since 2024-10-21
 */
@RestController
@RequestMapping("/chatList")
public class ChatListController {
    @Autowired
    UserService userService;
    @GetMapping("/insertChart")
    public Result insertChart(@Valid LoginForm loginForm){
        return Result.ok().put("status","success").put("data", "");
    }
}
