package com.hiball.common.security.domain;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class User implements UserDetails {
    private static final long serialVersionUID = 7428320037753294875L;

    private String username;
    private String password;
    private String name;
    private String userNo;
    private String mail;
    private String belongedDept;
    private String belongedDeptId;

    private List<Role> authorities;
    private boolean accountNonExpired = true;
    private boolean accountNonLocked = true;
    private boolean credentialsNonExpired = true;
    private boolean enabled = true;

    public void setUsername(String username) {
	this.username = username;
    }

    public void setPassword(String password) {
	this.password = password;
    }

    public void setAuthorities(List<Role> authorities) {
	this.authorities = authorities;
    }

    public void setAccountNonExpired(boolean accountNonExpired) {
	this.accountNonExpired = accountNonExpired;
    }

    public void setAccountNonLocked(boolean accountNonLocked) {
	this.accountNonLocked = accountNonLocked;
    }

    public void setCredentialsNonExpired(boolean credentialsNonExpired) {
	this.credentialsNonExpired = credentialsNonExpired;
    }

    public void setEnabled(boolean enabled) {
	this.enabled = enabled;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
	return this.authorities;
    }

    @Override
    public String getPassword() {
	return password;
    }

    @Override
    public String getUsername() {
	return username;
    }

    @Override
    public boolean isAccountNonExpired() {
	return this.accountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
	return this.accountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
	return this.credentialsNonExpired;
    }

    @Override
    public boolean isEnabled() {
	return this.enabled;
    }

    public String getUserNo() {
	return userNo;
    }

    public void setUserNo(String userNo) {
	this.userNo = userNo;
    }

    public String getBelongedDept() {
	return belongedDept;
    }

    public void setBelongedDept(String belongedDept) {
	this.belongedDept = belongedDept;
    }

    public String getName() {
	return name;
    }

    public void setName(String name) {
	this.name = name;
    }

    public String getMail() {
	return mail;
    }

    public void setMail(String mail) {
	this.mail = mail;
    }

    public String getBelongedDeptId() {
	return belongedDeptId;
    }

    public void setBelongedDeptId(String belongedDeptId) {
	this.belongedDeptId = belongedDeptId;
    }

}
