package com.zy.service.service.impl;

import com.zy.service.entity.User;
import com.zy.service.mapper.UserMapper;
import com.zy.service.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zy-栀
 * @since 2022-04-11
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

}
