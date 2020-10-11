package com.meetingRooms.entity;

public class ImportUser {
	
	private String name;
	private String email;
	private String phone;
	private String role;
	private String uid;
	private String password;
	private int credits;
	private String userpath;
	private String mondaydate;
	
	
	
	
	public void setmondaydate(String mondaydate)
	{
		this.mondaydate=mondaydate;
	}
	public String getmondaydate()
	{
		return mondaydate;
	}
	
	//---------------------------
	public void setuserpath(String userpath)
	{
		this.userpath=userpath;
	}
	public String getuserpath()
	{
		return userpath;
	}
	public void setuid(String uid)
	{
		this.uid=uid;
	}
	public String getuid()
	{
		return uid;
	}
	
	
	//---------------
	public void setname(String name)
	{
		this.name=name;
	}
	
	public String getname()
	{
		return name;
	}
	
	//---------------
	
	
	public void setemail(String email)
	{
		this.email=email;
	}
	public String getemail()
	{
		return email;
		
	}
	
	//------------------
	public void setphone(String phone)
	{
		this.phone=phone;
	}
	public String getphone()
	{
		return phone;
	}
	
	
	//-------------------
	
	public void setrole(String role)
	{
		this.role=role;
	}
	public String getrole()
	{
		return role;
	}
	
	
	//---------------
	
	public void setpassword(String password)
	{
		this.password=password;
	}
	public String getpassword()
	{
		return password;
	}
	
	
	//---------------
	
	public void setcredits(int credits)
	{
		this.credits=credits;
	}
	public int getcredits()
	{
		return credits;
	}
	
	
	
	
	
	
	

}
