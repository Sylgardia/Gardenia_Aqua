package com.zy.service_main9001.security.config;

import com.zy.service_main9001.security.filter.JWTAuthenticationFilter;
import com.zy.service_main9001.security.handler.*;
import de.codecentric.boot.admin.server.config.AdminServerProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import javax.sql.DataSource;

/**
 *  Security配置类
 *
 * @author zy
 * @since 2019-11-18
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private DataSource dataSource;

    /**
     * 配置设置
     * @param http  安全权限配置
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers(JWTConfig.antMatchers.split(",")).permitAll()      // 获取白名单（不进行权限验证）
                .antMatchers(AUTH_WHITELIST).anonymous()        // 后端接口规范 放行
                .anyRequest().authenticated()                   // 其他的需要登陆后才能访问
                .and().httpBasic().authenticationEntryPoint(new UserNotLoginHandler()) // 配置未登录处理类
                .and().formLogin().loginProcessingUrl("/login").defaultSuccessUrl("/index").permitAll()
//                .successHandler(new UserLoginSuccessHandler()) // 配置登录成功处理类
                .failureHandler(new UserLoginFailureHandler())   // 配置登录失败处理类
                .and().logout().logoutUrl("/logout").logoutSuccessHandler(new UserLogoutSuccessHandler())    // 配置登出地址 & 登出处理器
                .and().exceptionHandling().accessDeniedHandler(new UserAccessDeniedHandler())  // 配置没有权限处理类
                .and().rememberMe().tokenRepository(persistentTokenRepository()).tokenValiditySeconds(60).userDetailsService(userDetailsService)  // 存储 Token 一般使用 redis ，这里使用了 关系型数据库mysql 存储
//                .and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)  // 禁用session（使用Token认证）
                .and().cors()  // 开启跨域
                .and().csrf().disable()     // 禁用跨站请求伪造防护
//                .addFilterBefore(new JWTAuthenticationFilter(this.authenticationManager()), UsernamePasswordAuthenticationFilter.class)
//                .addFilter(new JWTAuthenticationFilter(this.authenticationManager()))   // 添加JWT过滤器
                .headers().cacheControl();   // 禁用缓存
    }

    /**
     * 密码处理
     * @param auth
     * @throws Exception
     */
    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        auth.userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder);
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

    @Bean
    protected PersistentTokenRepository persistentTokenRepository(){
        JdbcTokenRepositoryImpl jdbcTokenRepository = new JdbcTokenRepositoryImpl();
//        jdbcTokenRepository.setCreateTableOnStartup(true);
        jdbcTokenRepository.setDataSource(dataSource);
        return jdbcTokenRepository;
    }

}