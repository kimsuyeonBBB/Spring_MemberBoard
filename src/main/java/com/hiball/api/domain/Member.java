package com.hiball.api.domain;

import java.util.Date;

import com.hiball.web.common.domain.CommonDomain;

public class Member extends CommonDomain {
	private String memberId;
	private String name;
	private String hometown;
	private String team;
	private Date birthDate;
	private String height;
	private String weight;
	private String firstJoinTeam;
	private String firstJoinYear; 
	private String[] memberArr;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getHometown() {
		return hometown;
	}
	public void setHometown(String hometown) {
		this.hometown = hometown;
	}
	public String getTeam() {
		return team;
	}
	public void setTeam(String team) {
		this.team = team;
	}
	public Date getBirthDate() {
		return birthDate;
	}
	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}
	public String getHeight() {
		return height;
	}
	public void setHeight(String height) {
		this.height = height;
	}
	public String getWeight() {
		return weight;
	}
	public void setWeight(String weight) {
		this.weight = weight;
	}
	public String getMemberId() {
		return memberId;
	}
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	public String[] getMemberArr() {
		return memberArr;
	}
	public void setMemberArr(String[] memberArr) {
		this.memberArr = memberArr;
	}
	public String getFirstJoinTeam() {
		return firstJoinTeam;
	}
	public void setFirstJoinTeam(String firstJoinTeam) {
		this.firstJoinTeam = firstJoinTeam;
	}
	public String getFirstJoinYear() {
		return firstJoinYear;
	}
	public void setFirstJoinYear(String firstJoinYear) {
		this.firstJoinYear = firstJoinYear;
	}
	
	
}
