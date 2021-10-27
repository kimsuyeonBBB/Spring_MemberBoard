package com.hiball.common.security.domain;

import java.util.List;

import org.springframework.security.core.GrantedAuthority;

public class Role implements GrantedAuthority {
    private static final long serialVersionUID = -6279257976203846792L;

    private String roleId;
    private String belongedDeptId;
    private String userId;
    private String name;
    private String rolePriority;
    private String roleDesc;
    private List<Privilege> privileges;

    public String getName() {
	return name;
    }

    public void setName(String name) {
	this.name = name;
    }

    public List<Privilege> getPrivileges() {
	return privileges;
    }

    public void setPrivileges(List<Privilege> privileges) {
	this.privileges = privileges;
    }

    @Override
    public String getAuthority() {
	return getName();
    }

    public String getRoleId() {
	return roleId;
    }

    public void setRoleId(String roleId) {
	this.roleId = roleId;
    }

    public String getBelongedDeptId() {
	return belongedDeptId;
    }

    public void setBelongedDeptId(String belongedDeptId) {
	this.belongedDeptId = belongedDeptId;
    }

    public String getUserId() {
	return userId;
    }

    public void setUserId(String userId) {
	this.userId = userId;
    }

    public String getRolePriority() {
	return rolePriority;
    }

    public void setRolePriority(String rolePriority) {
	this.rolePriority = rolePriority;
    }

    public String getRoleDesc() {
	return roleDesc;
    }

    public void setRoleDesc(String roleDesc) {
	this.roleDesc = roleDesc;
    }

}
