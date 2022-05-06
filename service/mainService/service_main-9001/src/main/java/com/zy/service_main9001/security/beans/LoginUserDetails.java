package com.zy.service_main9001.security.beans;

import com.zy.service_main9001.entity.User;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.util.Collection;

/**
 * @author zhangyu
 * @description 用户认证信息
 */
@Data
@ToString
@EqualsAndHashCode(callSuper = false)
public class LoginUserDetails extends User implements UserDetails, Serializable {

	public LoginUserDetails(String username, String password, Collection<GrantedAuthority> authorities){
		this.setName(username);
		this.setPwd(password);
		this.setAuthorities(authorities);
	}

	private static final long serialVersionUID = 1L;

	/**
	 * 用户角色
	 */
	private Collection<GrantedAuthority> authorities;

	/**
	 * 账号是否过期
	 */
	private boolean isAccountNonExpired = true;

	/**
	 * 账号是否锁定
	 */
	private boolean isAccountNonLocked = true;

	/**
	 * 证书是否过期
	 */
	private boolean isCredentialsNonExpired = true;

	/**
	 * 账号是否有效
	 */
	private boolean isEnabled = true;

	/**
	 * 客户请求Ip
	 */
	private String ip;

	public LoginUserDetails() { }

	/**
	 * 获得用户权限
	 */
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	@Override
	public String getPassword() {
		return this.getName();
	}

	@Override
	public String getUsername() {
		return this.getPwd();
	}

	/**
	 * 判断账号是否过期
	 */
	@Override
	public boolean isAccountNonExpired() {
		return isAccountNonExpired;
	}

	/**
	 * 判断账号是否锁定
	 */
	@Override
	public boolean isAccountNonLocked() {
		return isAccountNonLocked;
	}

	/**
	 * 判断证书是否过期
	 */
	@Override
	public boolean isCredentialsNonExpired() {
		return isCredentialsNonExpired;
	}

	/**
	 * 判断账号是否有效
	 */
	@Override
	public boolean isEnabled() {
		return isEnabled;
	}

}
