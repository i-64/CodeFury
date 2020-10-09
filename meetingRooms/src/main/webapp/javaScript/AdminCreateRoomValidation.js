/**
 * 
 */
 
 var name_flag = 0; // flag to validate name
 
 var seating_capacity = 0; // flag to validate seating capacity
 
 var meeting_type = 0; // flag to check meeting type

 var amenity_flag = 0; // flag to check amenities selected
 
 	// To Set setCheckBoxes function
 
 function setCheckBoxes ( list ) {
	 
 	var values = document.getElementsByName ( "amenitites" ); // get checkBoxes 	
 	
 		 // function to find automatically check amenities checkBoxes
 	
 	var amenity_list = "" + list; // convert to string
 	
 	for ( var i = 0; i < values.length; i++ ) {	 
		
		if ( ( amenity_list.search ( values [i].value ) ) != -1 ) {

			values [i].checked = true;
			values [i].disabled = true;
		
		} else {
			
			values [i].checked = false;
			values [i].disabled = false;
		}
	}
	
	
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
				
	} else if ( seatingCapacity.match ( /[^0-9]/ ) ) {
	
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

 
 	// function to enable all checkBoxes before submitting 
 
 function preFinalValidation () {
	 
	var values = document.getElementsByName ( "amenitites" );
	 	
	for (var i = 0; i < values.length; i++) {
		  
		values [i].disabled = false;
	}
	
	validateForm ();
	 
 } // end of preFinalValidation function
 
 	// final validation
 
 function validateForm () {
 
	 	// checking for amenities selected
	
	amenity_flag = 0;
	 
 	var values = document.getElementsByName ( "amenitites" );
 	
	for(var i = 0; i < values.length; i++) {
	  
		if ( values [i].checked ) {
			
			amenity_flag = 1;
			break;
		}  
	} 	
 
		// checking option selected or not
	
	meeting_type = 0;
	
	var meetingType = document.getElementsByName ( "meetingType" );
	
	if ( meetingType.length == 0 ) {
		
		meeting_type = 0;
		
	} else {
		
		meeting_type = 1;
	}
	
		// final validation
	
 	if ( name_flag && seating_capacity && meeting_type && amenity_flag ) {
 	
 		document.getElementById ( "submitButton" ).disabled = false;

 	} else  {
 	 	
 		alert ( "Error In Existing Form" );
 		document.getElementById ( "submitButton" ).disabled = true;
 	} 
	
 } // end of validateForm