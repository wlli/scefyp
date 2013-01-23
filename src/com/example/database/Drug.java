package com.example.database;

public class Drug {
	private int code;
	private String name;
	private String usage;
	private int timePerDay = 0;
	private String amount = "NA";
	private String indication = "NA";
	private String sideEffect = "NA";
	private String active = "NO";
	private String longTerm = "NO";
	
	//constructors
	public Drug(){}
	public Drug(int code, String name, String usage, int timePerDay, String amount, 
			String indication, String sideEffect, String active, String longTerm){
		this.code = code;
		this.usage= usage;
		this.name = name;
		this.timePerDay = timePerDay;
		this.amount = amount;
		this.indication = indication;
		this.sideEffect = sideEffect;
		this.active = active;
		this.longTerm = longTerm;
	}
	public Drug(int code, String name, String usage){
		this.code = code;
		this.usage= usage;
		this.name = name;
	}
	
	// getter
	public int getCode(){
		return code;
	}
	public String getName(){
		return name;
	}
	public String getUsage(){
		return usage;
	}
	public int getTimePerDay(){
		return timePerDay;
	}
	public String getAmount(){
		return amount;
	}
	public String getIndication(){
		return indication;
	}
	public String getSideEffect(){
		return sideEffect;
	}
	public String isActive(){
		return active;
	}
	public String isLongTerm(){
		return longTerm;
	}
	
	//setter
	public void setCode(int code){
		this.code = code;
	}
	public void setName(String name){
		this.name = name;
	}
	public void setUsage(String usage){
		this.usage= usage;
	}
	public void set(int timePerDay){
		this.timePerDay = timePerDay;
	}
	public void set(String amount){
		this.amount = amount;
	}
	public void setIndication(String indication){
		this.indication = indication;
	}
	public void setSideEffect(String sideEffect){
		this.sideEffect = sideEffect;
	}
	public void setActive(String yesOrNo){
		active = yesOrNo;
	}
	public void setLongTerm(String yesOrNo){
		longTerm = yesOrNo;
	} 
}
