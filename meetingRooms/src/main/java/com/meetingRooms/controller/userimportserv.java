package com.meetingRooms.controller;


import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.SecureRandom;
import java.util.List;
import java.util.Random;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.meetingRooms.entity.ImportUser;
import com.meetingRooms.service.ImportUserServiceInterface;
import com.meetingRooms.utility.ImportUserServiceFactory;
import com.meetingRooms.utility.xmlEmailException;
import com.meetingRooms.utility.xmlPhoneException;






/**
 * Servlet implementation class addxml
 */
public class userimportserv extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
    public userimportserv() {
        super();
        
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
    	if(ServletFileUpload.isMultipartContent(request))
    	
    		{
            
    		try 
            	
            	{
    			
    			//path to create  DatabaseOfUser  you can change as per your work directory
    			Path path = Paths.get("C:\\Users\\kunal\\Documents\\training\\CodeFury\\meetingRooms\\src\\main\\webapp\\DatabaseOfUsers");
    			Files.createDirectories(path);
    			
    			
    			//object creaation using factory method for service clas
    			ImportUserServiceInterface si=ImportUserServiceFactory.createobject("adminservice");
    		    
    			
    			//path where imported xml file from frontend will save
    			File DIR = new File("c:/xmlFiles");
    		      //Creating the directory
    		      boolean bool = DIR.mkdir();
                
    				List <FileItem> multiparts = new ServletFileUpload(new DiskFileItemFactory()).parseRequest(request);
                
    					for(FileItem item : multiparts){
                    
    							if(!item.isFormField()){
                        
    								String name = new File(item.getName()).getName();
                        
    								//writing file to directory
    								item.write( new File("c:/xmlFiles" + File.separator + name));
                        
    								//reading file for usage
    								File fXmlFile = new File("c:/xmlFiles" + File.separator + name);
                        
    								DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            		    
            			
    								DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            		    
            			
    								Document doc = dBuilder.parse(fXmlFile);
            		    
    								//Normalization process to reduce redundancies
            			
    								doc.getDocumentElement().normalize();

            		    
    								//System.out.println("Root element :" + doc.getDocumentElement().getNodeName());
            		 
    								//fetching data from xml file
    								NodeList List = doc.getElementsByTagName("users");
            		    
            		   
    								//System.out.println("----------------------------");
            		    
            		    
            		    
    								for (int temp = 0; temp < List.getLength(); temp++) {
            		        
            		    	
    									Node Node = List.item(temp);
            		        
            		    	
    									//System.out.println("\nCurrent Element :" + Node.getNodeName());
            		        
            		    	
    									if (Node.getNodeType() == Node.ELEMENT_NODE) {
            		    		
            		    			
            		            
    										//Actual data extraction
    										Element eElement = (Element) Node;
            		           		 
            		    			
    										String id=(String)eElement.getAttribute("id");
    										
    										//function to generate uniqueID
    										String uniqueID = GenerateUniqueId();
    										
    										
    										//creating folder for each user in DatabaseOfUser with respect to its uniqueId
    										Path newPATH=Paths.get(path + File.separator + uniqueID);
    										Files.createDirectories(newPATH);	
    										
    										String np=newPATH.toString();
    										
    										//creating log file for each user
    										File log=new File(np + File.separator + "log.File");
    										log.createNewFile();
    										
    										//System.out.println(uniqueID);
            		    			
    										//System.out.println(id);
            		    			
            		    			
    										String fname=eElement.getElementsByTagName("firstname").item(0).getTextContent();
            		    			
            		                
    										//System.out.println("first name : " + fname);
            		                 
            		                
    										String phone=eElement.getElementsByTagName("phone").item(0).getTextContent();
            		                
            		                
    										//System.out.println("phone no :"+ phone);
    										
    										
    										//validating phone number
    										boolean result=ValidatePhoneNumber(phone);
            		                
    										if(result)
    										{
    											
    										}
    										else
    										{
    											  throw new xmlPhoneException();
    											//making user defined Exception
    											
    										}
            		                
            		               
    										String email=eElement.getElementsByTagName("email").item(0).getTextContent();
            		            	
    										//validating email
    										boolean emailresult=ValidateEmail(email);
    										
    										
    										if(emailresult)
    										{
    											
    										}
    										else
    										{
    											
    											//making user defined Exception
    											throw new xmlEmailException();
    											
    										}
            		                
    										
    										
    										
    										//System.out.println("email :"+ email);
            		                
            		                
    										String role=eElement.getElementsByTagName("role").item(0).getTextContent();
            		            	
            		                
    										//System.out.println("role is :"+ role);
    										int credits;
    										
    										
    										//Assaigning credits as per type of user
    										if(role.equals("manager"))
    										{
    											credits=2000;
    										}
    										else
    										{
    											credits=0;
    										}
    										//System.out.println(credits);
    										
    										
    										int len = 10;
    										String password;
    						        		//System.out.println(generateRandomPassword(len));
    										
    										//Generating password for each user of lenght of 10
    										password=generateRandomPassword(len);
    										
    										//importing userpath 
    										String userpath="\\main\\webapp\\DatabaseOfUsers"+File.separator+uniqueID;
    										
    										ImportUser iu=new ImportUser();
    										iu.setuid(uniqueID);
    										iu.setname(fname);
    										iu.setphone(phone);
    										iu.setemail(email);
    										iu.setrole(role);
    										iu.setcredits(credits);
    										iu.setpassword(password);
    										iu.setuserpath(userpath);
    										
    										si.ServiceImport(iu);
    										
            		    	
    									}
            		    
    								}
            		    
                   
    							}
                
    					}
               //File uploaded successfully
               
    					request.setAttribute("message", "User Imported Successfully");
            
            	} 
    		catch (Exception ex) 
    		
    		{
               
    			request.setAttribute("message", "File Upload Failed due to " + ex);
    			ex.printStackTrace();
            
    		}         		
        
    		}else
    		
    		{

            request.setAttribute("message","No File found");

    		}
        
    	request.getRequestDispatcher("/result.jsp").forward(request, response);

    }

	private String generateRandomPassword(int len) {
		
		
		
		// ASCII range - alphanumeric (0-9, a-z, A-Z)
		final String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789@#$%^&*";

		SecureRandom random = new SecureRandom();
		StringBuilder sb = new StringBuilder();

		// each iteration of loop choose a character randomly from the given ASCII range
		// and append it to StringBuilder instance

		for (int i = 0; i < len; i++) {
			int randomIndex = random.nextInt(chars.length());
			sb.append(chars.charAt(randomIndex));
		}

		return sb.toString();
		
		
	}

	private boolean ValidateEmail(String email) {
		
		
		String EmailExpression = "^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$";
	      //Matching the given phone number with regular expression
	      boolean result = email.matches(EmailExpression);
	      
	      if(result) {
	          
	    	  result=true;
	       } 
	       else 
	       
	       {
	    	  result=false;
	       }
  	
		
		
		return result;
	}

	private boolean ValidatePhoneNumber(String phone) {
		
		String PhoneExpression = "\\d{10}|(?:\\d{3}-){2}\\d{4}|\\(\\d{3}\\)\\d{3}-?\\d{4}";
    	//regular expression to validate  phone number
        boolean result = phone.matches(PhoneExpression);
	      
	      if(result) {
	          
	    	  result=true;
	       } 
	       else 
	       
	       {
	    	   result=false;
	       }
		
		
		return result;
	}

	private String GenerateUniqueId() {
		// TODO Auto-generated method stub
		
		String uid=UUID.randomUUID().toString();
		
		//generated uid is more than length of 20 , thats why trimming it with randomised process
		Random random = new Random();
		
		int startlimit = 7;
		
		while (true)
		{
		    startlimit= random.nextInt(14);
		    if(startlimit !=0) break;
		}
		

		int endlimit=startlimit+7;
		
		uid= (uid.substring(startlimit, endlimit));
		
		
		
		return uid;
	}



}
