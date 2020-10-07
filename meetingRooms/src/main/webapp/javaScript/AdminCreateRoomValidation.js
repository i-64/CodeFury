/**
 * 
 */
 
 var name_flag = 0; // flag to validate name
 
 var seating_capacity = 0; // flag to validate seating capacity
 
 var meeting_type = 0; // flag to check meeting type
 
 
 	// To Set setCheckBoxes function
 
 function setCheckBoxes () {
 
 	var option = document.getElementById ( "meetingType" ).value;
 	
 	if ( option.length == 0 ) {
 	
 		 var meeting_type = 0;
 		 alert ( "Select Meeting Type" );
 	
 	} else {
 	
 		alert ( option );
 	}
 
 } // end of setCheckBoxes function 
 
 
 	// function to validate seating capacity
 	
 function validateSeatingCapacity (){
 
 	var seatingCapacity = document.getElementById ( "seating_capacity" ).value;
	
	var error_meesage = "";
	
	if ( seatingCapacity.length == 0 ) {
				
		error_message = "<span style = \"color:red;\"> Seating Capacity Empty </span>";
		seating_capacity = 0;				
				
	} else if ( seatingCapacity.match ( /[^0-9 ]/ ) ) {
	
		error_message = "<span style = \"color:red;\"> Invalid Characters </span>";
		seating_capacity = 0;
		
	} else {
	
		error_message = "";
		seating_capacity = 1;
	}		
	
	document.getElementById ( "seatingRoomError" ).innerHTML = error_message;
 
 } // end of validateSeatingCapacity Function
 
 
 
 	// function to validate Meeting Name
 	
 function validateMeetingName () {

	var name = document.getElementById ( "meeting_name" ).value;
	
	var error_meesage = "";
	
	if ( name.length == 0 ) {
				
		error_message = "<span style = \"color:red;\"> Name Empty </span>";
		name_flag = 0;				
				
	} else if ( name.match ( /[^A-Za-z0-9 ]/ ) ) {
	
		error_message = "<span style = \"color:red;\"> Invalid Characters </span>";
		name_flag = 0;
		
	} else {
	
		error_message = "";
		name_flag = 1;
	}
		
	
	document.getElementById ( "meetingRoomError" ).innerHTML = error_message;

 } // end of validateMeetingName Function
 
 
 	// final validation
 
 function validateForm () {
 
 	var values = document.getElementsByName ( "amenitites" );
 	
 	var numberOfCheckedItems = 0;  
 	
	for(var i = 0; i < values.length; i++) {
	  
		if ( values [i].checked ) {
			
			alert ( (values [i].value) );
		}  
	} 	
 
 	if ( name_flag && seating_capacity ) {
 	
 		form.submit ();
 	
 	} else  {
 	 	
 		alert ( "Error In Existing Form" );
 	}
 
	
 } // end of validateForm