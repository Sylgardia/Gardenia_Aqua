package com.zy.generator.bootadmin8999.config;

import de.codecentric.boot.admin.server.config.AdminServerProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;

/**
 * @author zyGardenia - 栀
 * @Since 2022/4/11 19:00
 */

@Configuration(proxyBeanMethods = false)
public class SecurityConfigure extends WebSecurityConfigurerAdapter {
    private final String adminContextPath;

    public SecurityConfigure(AdminServerProperties adminServerProperties) {
        this.adminContextPath = adminServerProperties.getContextPath();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        SavedRequestAwareAuthenticationSuccessHandler successHandler = new SavedRequestAwareAuthenticationSuccessHandler();
        successHandler.setTargetUrlParameter( "redirectTo" );

        http.authorizeRequests()
                .antMatchers( adminContextPath + "/assets/**" ).permitAll()
                .antMatchers( adminContextPath + "/login" ).permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin().loginPage( adminContextPath + "/login" ).successHandler( successHandler ).and()
                .logout().logoutUrl( adminContextPath + "/logout" ).and()
                .httpBasic().and()
                .csrf().disable();
    }

}
