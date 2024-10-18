package com.shengaike.controller;


import com.shengaike.util.Result;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

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
    @GetMapping("/login")
    public Result login(){
        return Result.ok().put("data","你好");
    }
}
