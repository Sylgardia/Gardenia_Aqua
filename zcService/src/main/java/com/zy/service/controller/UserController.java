package com.zy.service.controller;

import com.zy.service.entity.User;
import com.zy.service.service.UserService;
import com.zy.service.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author zy-栀
 * @since 2022-04-11
 */
@RestController
@RequestMapping("/main/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("getAllUsers")
    public R allUsers(){
        List<User> list = userService.list();
        return R.ok().data("users", list);
    }

}

