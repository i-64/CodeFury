package com.meetingRooms.entity;

public class loginUserEntity {
	
	private String user_id;	// to store userID
	private String name;	// to store name
	
	private String email;	// to store email address
	private String phone;	// to store phone number
	
	private String role;	// to store role
	private String password; // to store user password

	
	public loginUserEntity () {
		
		password = user_id = name = email = phone = role = "";
		
	} // end of constructor
	
	
	
	/**
	 * @return the password
	 */
	
	public String getPassword() {
		
		return password;
	}


	/**
	 * @param password the password to set
	 */
	
	public void setPassword(String password) {
		
		this.password = password;
	}

	
	/**
	 * @return the user_id
	 */
	
	public String getUser_id() {
		
		return user_id;
	}

	/**
	 * @param user_id the user_id to set
	 */
	
	public void setUser_id(String user_id) {
		
		this.user_id = user_id;
	}
	

	/**
	 * @return the name
	 */
	
	public String getName() {
		
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		
		this.name = name;
	}
	

	/**
	 * @return the email
	 */
	
	public String getEmail() {
		
		return email;
	}

	/**
	 * @param email the email to set
	 */
	
	public void setEmail(String email) {
		
		this.email = email;
	}
	

	/**
	 * @return the phone
	 */
	
	public String getPhone() {
		
		return phone;
	}

	/**
	 * @param phone the phone to set
	 */
	
	public void setPhone(String phone) {
		
		this.phone = phone;
	}
	

	/**
	 * @return the role
	 */
	
	public String getRole() {
		
		return role;
	}

	/**
	 * @param role the role to set
	 */
	
	public void setRole(String role) {
		
		this.role = role;
	}

} // end of loginUserEntity Class 
