function ready() {
	//initialze meeting date to today's date
	document.getElementById('meetingDate').value = new Date().toISOString().split('T')[0];
}

var rooms = [];

function filterRooms() {

	var startTime = document.getElementById("startTime").value;
	var endTime = document.getElementById("endTime").value;
	var meetingType = document.getElementById("meetingType").value;
	var meetingDate = document.getElementById("meetingDate").value;


	var x = new XMLHttpRequest();
	//step 2 how xhr will open connection with server
	x.open("GET",
		"filterMeetingRooms.jsp" +
		"?startTime=" + startTime +
		"&endTime=" + endTime +
		"&meetingType=" + meetingType +
		"&meetingDate=" + meetingDate
		, true
	);

	//step 3 how xhr will send request
	x.send();

	//step 4 how xhr will get response from server
	//state={0,1,2,3,4}

	x.onreadystatechange = function () {
		if (x.readyState == 4) {
			rooms = JSON.parse(x.responseText);
			var roomContainer;
			if (!document.querySelector('#rooms')) {
				roomContainer = document.createElement('div');
				roomContainer.setAttribute('id', 'rooms');
			}
			else {
				roomContainer = document.querySelector('#rooms');
			}
			roomContainer.innerHTML = '';
			for (var room of rooms) {
				roomContainer.innerHTML +=
					` <div class='room' id='${room.roomName}'>
                		<h2>${room.roomName}</h2>
                		<div>Seating capactiy: ${room.seatingCapacity}</div>
                		<div> Cost per hour : ${room.costPerHour}</div>
						<div>Average Rating: ${room.averageRating}</div>
						<button onClick="openModal(event)">Book now!</button>
					</div>`
					;
			}
			document.querySelector('.mycontainer').appendChild(roomContainer);
		}
	}

}


function getUserSuggestions() {

}

function openModal(e) {
	document.querySelector('.modal-container').classList.remove('inactive');
	var room_id = e.target.parentElement.id;
	var roomObj = rooms.filter(({ roomName }) => roomName === room_id)[0];

	var modalbodyHTML = `
	<h3>${roomObj.roomName} </h3>
	
	<div class='flex-row'>
	<div>Seating capactiy: ${roomObj.seatingCapacity}</div>
	<div>Average Rating: ${roomObj.averageRating}</div>
	</div>
	
	
	<div class='item-container'>
		<div class='title'> Purpose</div>
		<input class='userinput width100' type="text" placeholder="Retrospective meeting">

	</div>
	<div class='item-container'>
	<div class='title'> Add members </div>
		<input type=text class="input-container width100" onkeyup="getUserSuggestions()"> 
		<div class="content-container">
	</div>
	`

	document.querySelector('.modal-body').innerHTML = modalbodyHTML;

}

window.addEventListener("click", (e) => {
	if (e.target === document.querySelector('.modal-container')) {
		document.querySelector('.modal-container').classList.add('inactive');
		document.querySelector('.modal-body').innerHTML = '';
	}
})
// DOMContentLoaded event – DOM is ready, so the handler can lookup DOM nodes, initialize the interface.
document.addEventListener('DOMContentLoaded', ready);