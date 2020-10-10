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
 
} // end of setCheckBoxes function 
 
 
 	// function to validate seating capacity
 	
 function validateSeatingCapacity (){
 
 	var seatingCapacity = document.getElementById ( "seating_capacity" ).value;
	
	var error_meesage = "";
	
	if ( seatingCapacity.length == 0 ) {
				
		error_message = "<span style = \"color:red;\"> Seating Capacity Empty </span>";
		seating_capacity = 0;				
				
	} else if ( parseInt ( seatingCapacity ) == 0 ) {
		
		error_message = "<span style = \"color:red;\"> Seating Capacity can't be zero </span>";
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
		
			// get XHR
		
		var xhr = new XMLHttpRequest ();
			
			// open connection with server
			
		xhr.open ( "POST", "AdminGetData?name=" + name, true );
		
			// send request
			
		xhr.send ();
			
			// check response from server
			// state ( 0 = no connection, 1 = connection exists, 2 = client send request, 3 = server responded, 4 = interaction finished )
		
		xhr.onreadystatechange = function () {
			
			if ( xhr.readyState == 4 ) {
				
				message = xhr.responseText;
				
				if ( (message == "0") ) {
					
					error_message = "<span style = \"color:red;\"> Meeting Room Already Exists </span>";
					name_flag = 0;
					document.getElementById ( "meetingRoomError" ).innerHTML = error_message;
				
				} else {
					
					error_message = "";
					name_flag = 1;
					document.getElementById ( "meetingRoomError" ).innerHTML = error_message;
				}				
			}						
		}
	}

	//document.getElementById ( "meetingRoomError" ).innerHTML = error_message;

 } // end of validateMeetingName Function

 
 	// function to enable all checkBoxes before submitting 
 
 function preFinalValidation () {
	 
		amenity_flag = 0;
		 
		var values = document.getElementsByName ( "amenitites" );
		
		var amenitiesCount = 0;
		
		for (var i = 0; i < values.length; i++) {
			  
			if ( values [i].checked ) {
				
				amenitiesCount++;
				
				if ( amenitiesCount >= 2 ) {
					
					amenity_flag = 1;
					break;
				}
			}  
		}
		
		if ( amenitiesCount < 2 ) {
			
			alert ( "Select at least 2 Amenities" );
		}
		
		// checking option selected or not
		
		meeting_type = 0;
		
		var meetingType = document.getElementsByName ( "meetingType" );
		
		if ( meetingType.length == 0 ) {
			
			meeting_type = 0;
			
		} else {
			
			meeting_type = 1;
		}
		

	 if ( name_flag && seating_capacity && meeting_type && amenity_flag ) {
		 
		 document.getElementById ( "submitButton" ).disabled = false;
		 
	 } else {
		 
	 	document.getElementById ( "submitButton" ).disabled = true;
	 }
	 
 } // end of preFinalValidation function
 
 	// final validation
 
 function validateForm () { 
	
		// final validation
	
 	if ( name_flag && seating_capacity && meeting_type && amenity_flag ) {
 		
 		var values = document.getElementsByName ( "amenitites" ); // get checkBoxes
 		 
 		 for ( var i = 0; i < values.length; i++ ) {	 
 				
 				values [i].disabled = false;
 		}
 	
 		document.getElementById ( "AdminCreateRoomForm" ).submit (); // submit the final form
 		
 	} else  {
 	 	
 		alert ( "Error In Existing Form" );
 		document.getElementById ( "submitButton" ).disabled = true;
 	} 
	
 } // end of validateForm