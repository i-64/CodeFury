package com.meetingRooms.entity;

/**
 * ENtity class for imported user
 * 
 * @author Kunal Rasam
 *
 */
public class ImportUser {
	
	private String name;
	private String email;
	private String phone;
	private String role;
	private String uid;
	private String password;
	private int credits;
	private String userpath;
	private String mondayDate;
	
	
	
	
	/**
	 * @param mondaydate
	 */
	public void setmondaydate(String mondaydate)
	{
		this.mondayDate=mondaydate;
	}
	/**
	 * @return date on monday
	 */
	public String getmondaydate()
	{
		return mondayDate;
	}
	
	//---------------------------
	/**
	 * @param userpath
	 */
	public void setuserpath(String userpath)
	{
		this.userpath=userpath;
	}
	/**
	 * @return path for logs of this user
	 */
	public String getuserpath()
	{
		return userpath;
	}
	/**
	 * @param uid
	 */
	public void setuid(String uid)
	{
		this.uid=uid;
	}
	/**
	 * @return generated id of the user
	 */
	public String getuid()
	{
		return uid;
	}
	
	
	//---------------
	/**
	 * @param name
	 */
	public void setname(String name)
	{
		this.name=name;
	}
	
	/**
	 * @return name of the user
	 */
	public String getname()
	{
		return name;
	}
	
	//---------------
	
	
	/**
	 * @param email
	 */
	public void setemail(String email)
	{
		this.email=email;
	}
	/**
	 * @return email of the user 
	 */
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
	
	/**
	 * @param role
	 */
	public void setrole(String role)
	{
		this.role=role;
	}
	/**
	 * @return role of the user
	 */
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
	
	/**
	 * @param credits
	 */
	public void setcredits(int credits)
	{
		this.credits=credits;
	}
	/**
	 * @return credits of the user
	 */
	public int getcredits()
	{
		return credits;
	}
	
	
	
	
	
	
	

}
