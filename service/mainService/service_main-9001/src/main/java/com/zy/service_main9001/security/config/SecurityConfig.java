package com.zy.service_main9001.security.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 *  Security配置类
 *
 * @author zy
 * @since 2019-11-18
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    /**
     * 配置设置
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers( // 后端接口规范 放行
                        "/swagger-ui/**",
                        "/swagger-resources/**","/v2/**", "/doc.html/**",
                        "/webjars/**", "/profile/**", "/v3/**").anonymous()
                .anyRequest().authenticated() // 其他的需要登陆后才能访问
                .and().formLogin()
                .and().logout().logoutUrl("/logout") // 配置登出地址
                .and().cors()   // 开启跨域
                .and().csrf().disable();  // 禁用跨站请求伪造防护
    }

    /**
     * 密码处理
     * @param auth
     * @throws Exception
     */
    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        auth.inMemoryAuthentication().withUser("zy")
                .password(passwordEncoder.encode("123"))
                .roles("admin")
                .and().passwordEncoder(passwordEncoder);
    }

    /**
     * 配置哪些请求不拦截
     * @param web
     * @throws Exception
     */
    @Override
    public void configure(WebSecurity web) {
        web.ignoring().antMatchers(AUTH_WHITELIST);
    }

    /**
     * -- swagger ui忽略
      */
    private static final String[] AUTH_WHITELIST = {
            "/swagger-resources/**","/swagger-ui/**",
            "/swagger-ui.html","/v3/**","/v2/**",
            "/webjars/**","/doc.html","/profile/**"
    };
}