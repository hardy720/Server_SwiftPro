package com.shengaike.controller;


import com.shengaike.entity.FriendList;
import com.shengaike.form.LoginForm;
import com.shengaike.service.FriendListService;
import com.shengaike.service.UserService;
import com.shengaike.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

import javax.validation.Valid;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Hardy
 * @since 2024-10-22
 */
@RestController
@RequestMapping("/friendList")
public class FriendListController {
    @Autowired
    FriendListService friendListService;
    @PostMapping("/addFriend")
    public Result insertChart(@Valid FriendList friendList){
        boolean save = this.friendListService.save(friendList);
        if(!save) return Result.error("创建好友失败");
        return Result.ok();
    }
}

