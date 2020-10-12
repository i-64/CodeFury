

# Introduction

	The system automates the procedure of booking a meeting room in a Company.Employees can search and book a meeting room based on thier requirement.
  
  
# Prerequisite

*  [Jdk 1.8](https://www.oracle.com/java/technologies/javase/javase8-archive-downloads.html)
*  [Apache Derby 10.13.1.1](https://db.apache.org/derby/releases/release-10.13.1.1.html)
*  [Apache Tomcat v9.0](https://tomcat.apache.org/download-90.cgi)
*  [Spring tool Suite](https://spring.io/tools/)  


# Attachments (Present In Project)

## Required for project execution

*  Derby.jar : Contains java classes which require to perform database operations.
*  commons-fileupload-1.3: Contains java classes which require to perform file opeations.
*  commons-io-2.2 : Contains java classes which require to perform file operations.


# How to import this project into your System:
	
1. Clone project from git

1. Import In Spring Tool Suite as an existing maven project.



# Configuration of Project

Right click on the project --> build path --> configure build path-->Order and Export-->Check unchecked checkboxes-->Apply and Close.



## Configuration of Apache Tomcat Server

 Go to the Window tab --> Show view -->Others --> Search Server --> Server-->Apache --> Tomcat v9.0 server



## Configuration of External .jar files 

 The .jar files mentioned above will be present in Web-INF/lib folder. Kindlly add them to the build path. 
									
Referesh and clean the project
	
  

 ## Connection to Derby database								

1. Open directory where you have installed derby

1. bin -> Open "ij.bat" file.

1. dump.sql file is present in application/meetingRooms/src/main/web-app. All required tables and data are provided in dump.sql, copy everythig from dump.sql  nd paste in console of "ij.bat".

1. Now, Application is ready to use.


# Note 

* Clean the Tomcat work directory before starting the Tomcat v9.0 Server
* Derby ij shell should be terminated before running the index.jsp file because Derby doesn’t support concurrent instances of connection.
 



			    
 		
	
		



			    
 		
	
		
