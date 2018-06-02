package com.kh.spring.demo.model.vo;

import java.util.Arrays;

public class Dev {
	private int devNo;
	private String devName;
	private int devAge;
	private String[] devLang;
	private String devEmail;
	public Dev() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Dev(int devNo, String devName, int devAge, String[] devLang, String devEmail) {
		super();
		this.devNo = devNo;
		this.devName = devName;
		this.devAge = devAge;
		this.devLang = devLang;
		this.devEmail = devEmail;
	}
	public int getDevNo() {
		return devNo;
	}
	public void setDevNo(int devNo) {
		this.devNo = devNo;
	}
	public String getDevName() {
		return devName;
	}
	public void setDevName(String devName) {
		this.devName = devName;
	}
	public int getDevAge() {
		return devAge;
	}
	public void setDevAge(int devAge) {
		this.devAge = devAge;
	}
	public String[] getDevLang() {
		return devLang;
	}
	public void setDevLang(String[] devLang) {
		this.devLang = devLang;
	}
	public String getDevEmail() {
		return devEmail;
	}
	public void setDevEmail(String devEmail) {
		this.devEmail = devEmail;
	}
	@Override
	public String toString() {
		return "Dev [devNo=" + devNo + ", devName=" + devName + ", devAge=" + devAge + ", devLang="
				+ Arrays.toString(devLang) + ", devEmail=" + devEmail + "]";
	}
	
}
