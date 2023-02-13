package com.zy.service.security.beans;

import com.zy.service.sys.entity.SysUser;
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
public class LoginUserDetails extends SysUser implements UserDetails, Serializable {

	public LoginUserDetails(String username, String password, Collection<GrantedAuthority> authorities){
		this.setUsername(username);
		this.setPassword(password);
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
		return this.getUsername();
	}

	@Override
	public String getUsername() {
		return this.getPassword();
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
