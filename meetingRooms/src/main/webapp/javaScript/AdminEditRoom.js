/**
 * http://usejsdoc.org/
 */

 var seating_capacity = 0; // flag to validate seating capacity
 
 var amenity_flag = 0; // flag to check amenities selected

 
 	// function to delete room
 
 function deleteRoom () {
	 
	 document.getElementById ( "DeleteMeetingRoom" ).submit (); // submit the final form to delete room
	 
 } // end of deleteRoom function
 

 	// function to submit form
 
 function finalSubmit () {
	 
	 	// enable all elements before submitting
	 
	 document.getElementById ( "meeting_name" ).disabled = false;
	 
	 document.getElementById ( "seating_capacity" ).disabled = false;
	 
	 enableAmenitySelection ();
	 
	 document.getElementById ( "AdminEditRoomForm" ).submit (); // submit the final form	 
	 
 } // end of finalSubmit button
 
 
 	// function to enable checkBoxes editing
 
 function enableAmenitySelection() {
	 
	 var values = document.getElementsByName ( "amenitites" ); // get checkBoxes
	 
	 for ( var i = 0; i < values.length; i++ ) {	 
			
			values [i].disabled = false;
		}
	 
 } // end of enableAmenitySelection function
 
 
 	// To Set setCheckBoxes function
 
 function setCheckBoxes ( list ) {
 
 	var values = document.getElementsByName ( "amenitites" ); // get checkBoxes 	
 	
 		 // function to find automatically check amenities checkBoxes
 	
 	var amenity_list = "" + list; // convert to string
 	
 	for ( var i = 0; i < values.length; i++ ) {	 
		
		if ( ( amenity_list.search ( values [i].value ) ) != -1 ) {

			values [i].checked = true;			
		
		} else {
			
			values [i].checked = false;			
		}
		
		values [i].disabled = true;
	} 

 }
 
 	// function to enable seating capacity button
 
function enableSeatingCapacity() {
	 
	 document.getElementById ( "seating_capacity" ).disabled = false;
	 
 } // end of enableSeatingCapacity function 
 
 
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



function test () {
	
	
	alert ( "hello" );
}