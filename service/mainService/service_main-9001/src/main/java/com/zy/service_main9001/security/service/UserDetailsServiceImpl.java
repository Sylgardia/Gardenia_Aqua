package com.zy.service_main9001.security.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zy.service_main9001.security.beans.LoginUserDetails;
import com.zy.service_main9001.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author zyGardenia - 栀
 * @Since 2022/4/11 13:58
 */
@Service("userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // 查询用户
        QueryWrapper<com.zy.service_main9001.entity.User> wrapper = new QueryWrapper<>();
        wrapper.eq("name", username);
        com.zy.service_main9001.entity.User user = userService.getOne(wrapper);

        if(user == null){
            throw new UsernameNotFoundException("用户名不存在！");
        }

        LoginUserDetails loginUserDetails = new LoginUserDetails();
        BeanUtils.copyProperties(user, loginUserDetails);

        Set<GrantedAuthority> authorities = new HashSet<>();

        loginUserDetails.setAuthorities(authorities);

        return loginUserDetails;
    }
}
