package com.shengaike.service.impl;

import com.shengaike.entity.User;
import com.shengaike.mapper.UserMapper;
import com.shengaike.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Hardy
 * @since 2024-10-18
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

}
