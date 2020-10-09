function ready() {
	//initialze meeting date to today's date
	document.getElementById('meetingDate').value = new Date().toISOString().split('T')[0];
}

var rooms = [];
var userIdToNameMap = {};
var usersPresentInMeeting = [];
var suggestedUsers = [];
var currentRoomId = null;

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
			var roomContainer = document.querySelector('#rooms');

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
	var name = document.querySelector(".input-container").value;
	var x = new XMLHttpRequest();
	//step 2 how xhr will open connection with server
	x.open("GET", "searchUser.jsp?name=" + name, true);

	//step 3 how xhr will send request
	x.send();

	//step 4 how xhr will get response from server
	//state={0,1,2,3,4}

	x.onreadystatechange = function () {
		if (x.readyState == 4) {
			var users = JSON.parse(x.responseText);
			suggestedUsers = [];
			for (var user of users) {
				userIdToNameMap[user.userId] = user.name;
				if (!usersPresentInMeeting.includes(user.userId)) {
					suggestedUsers.push(user.userId)
				}
			}
			generateDropDownDom();
		}
	}
}

function addUsertoMeeting(userId) {
	suggestedUsers = suggestedUsers.filter((id) => id !== userId);
	usersPresentInMeeting.push(userId);
	generateDropDownDom();
	generatePillsDom();
}

function removeUserFromMeeting(userId) {
	usersPresentInMeeting = usersPresentInMeeting.filter((id) => id !== userId);
	generatePillsDom();
}

function generatePillsDom() {
	document.querySelector('.membersList').innerHTML = '';
	if (usersPresentInMeeting && usersPresentInMeeting.length) {
		var pillsContent = '';
		for (var userId of usersPresentInMeeting) {
			pillsContent += `<button  onclick=removeUserFromMeeting(${userId}) >
								${userIdToNameMap[userId]}
								<img src='images/cross-remove-sign.png'>
							</button>`
		}
		document.querySelector('.membersList').innerHTML = pillsContent;
	}
}

function generateDropDownDom() {
	var dropDownContent;
	dropDownContent = ` <ul> `;
	for (var userId of suggestedUsers) {
		dropDownContent += `
		<li onclick= addUsertoMeeting(${userId})> ${userIdToNameMap[userId]}
		<img src="images/plus.png"> </img>
		</li>`;
	}
	dropDownContent += `</ul>`;
	document.querySelector(".content-container").innerHTML = dropDownContent;

}

function openModal(e) {
	document.querySelector('.modal-container').classList.remove('inactive');
	var room_id = e.target.parentElement.id;
	var roomObj = rooms.filter(({ roomName }) => roomName === room_id)[0];
	currentRoomId = room_id;

	var startTime = document.getElementById("startTime").value;
	var endTime = document.getElementById("endTime").value;
	var meetingTypeSel = document.getElementById("meetingType");
	var meetingTypeText = meetingTypeSel.options[meetingTypeSel.selectedIndex].text;
	var meetingDate = document.getElementById("meetingDate").value;

	var modalbodyHTML = `
	<div id='modal-title'>${roomObj.roomName} </div>
	
	<div class='room-modal-items'>
		<div> <span class='bold'>-> Seating capactiy: </span> ${roomObj.seatingCapacity}</div>
		<div> <span class='bold'>-> Average Rating:</span> ${roomObj.averageRating}</div>
		<div> <span class='bold'>-> Meeting Type: </span> ${meetingTypeText} </div>
		<div><span class='bold'>-> Booking date: </span> ${meetingDate} </div>
		<div> <span class='bold'>-> Start time: </span> ${startTime} </div>
		<div> <span class='bold'>-> End time: </span> ${endTime} </div>
	</div>
	
	<div class='item-container'>
		<div class='title'> Purpose</div>
		<input id='meetingTitle' class='userinput width100' type="text" placeholder="Retrospective meeting">
	</div>

	<div class='item-container'>
		<div class='title'>
		 Add members
		 </div>
		 <div class='membersList'></div>
		<input type=text class="input-container width100" onkeyup="getUserSuggestions()"> 
		<div class="content-container">
	</div>

	<div class='modal-confirm'>
		<button onclick="saveMeeting()">Confirm</button>
		<button id='cancelbtn'> Cancel </button>
	</div>
	`

	document.querySelector('.modal-body').innerHTML = modalbodyHTML;

}

function saveMeeting() {
	var members = usersPresentInMeeting.join(',');
	var meetingTypeId = document.getElementById("meetingType").value;
	var meetingDate = document.getElementById("meetingDate").value;
	var meetingTitle = document.getElementById("meetingTitle").value;
	var startTime = document.getElementById("startTime").value;
	var endTime = document.getElementById("endTime").value;

	var x = new XMLHttpRequest();
	//step 2 how xhr will open connection with server
	x.open("GET",
		"bookMeeting.jsp" +
		"?members=" + members +
		"&meetingTypeId=" + meetingTypeId +
		"&meetingDate=" + meetingDate +
		"&meetingTitle=" + meetingTitle +
		"&startTime=" + startTime +
		"&endTime=" + endTime +
		"&meetingRoomId=" + currentRoomId
		, true
	);

	//step 3 how xhr will send request
	x.send();

	//step 4 how xhr will get response from server
	//state={0,1,2,3,4}

	x.onreadystatechange = function () {
		if (x.readyState == 4) {
			if (x.responseText.trim() === 'success') {
				window.location.href = 'ManagerHomePage.jsp';
			}

		}
	}
}

function cleanState() {
	document.querySelector('.modal-container').classList.add('inactive');
	document.querySelector('.modal-body').innerHTML = '';
	suggestedUsers = [];
	usersPresentInMeeting = [];
	userIdToNameMap = {};
	currentRoomId = null;
}

function resetRooms() {
	document.querySelector('#rooms').innerHTML = '';
	rooms = [];
	suggestedUsers = [];
	usersPresentInMeeting = [];
	userIdToNameMap = {};
}

window.addEventListener("click", (e) => {
	if (e.target === document.querySelector('.modal-container') || e.target === document.querySelector('#cancelbtn')) {
		cleanState();
	}
})
// DOMContentLoaded event – DOM is ready, so the handler can lookup DOM nodes, initialize the interface.
document.addEventListener('DOMContentLoaded', ready);