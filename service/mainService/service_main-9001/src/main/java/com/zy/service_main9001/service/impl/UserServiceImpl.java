package com.zy.service_main9001.service.impl;

import com.zy.service_main9001.entity.User;
import com.zy.service_main9001.mapper.UserMapper;
import com.zy.service_main9001.service.UserService;
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
