var rooms = [];
var userIdToNameMap = {};
var usersPresentInMeeting = [];
var suggestedUsers = [];
var currentRoomId = null;
var managerCredits;

function ready() {
	//initialze meeting date to tomorrow's date
	document.getElementById('filterbtn').disabled = true
	document.getElementById('meetingDate').value = new Date(getTomorrowDate()).toLocaleDateString().split('/').reverse().join('-')
}



function getTomorrowDate() {
	var tomorrow = new Date();
	tomorrow.setHours(0, 0, 0, 0)
	return tomorrow.setDate(new Date().getDate() + 1);
}

function dateValidation() {
	var meetingDate = document.getElementById("meetingDate").value;
	document.getElementById('dateError').innerHTML = '';

	if (new Date(meetingDate) < getTomorrowDate()) {
		document.getElementById('filterbtn').disabled = true
		document.getElementById('dateError').innerHTML = 'Booking date should always be a later date than today'
	}

}

function timeValidation() {
	var startTime = document.getElementById("startTime").value;
	var endTime = document.getElementById("endTime").value;
	var [startTimeHH, startTimeMM] = startTime.split(':');
	var [endTimeHH, endTimeMM] = endTime.split(':');
	var validationFailed = false;
	document.getElementById('timeError').innerHTML = '';

	if (startTime === '' || endTime === '') return

	if (startTimeHH > endTimeHH) {
		validationFailed = true;
	}
	else if (startTimeHH === endTimeHH && startTimeMM >= endTimeMM) {
		validationFailed = true;
	}


	if (validationFailed) {
		document.getElementById('filterbtn').disabled = true
		document.getElementById('timeError').innerHTML = 'Start time should always be less than end time';
	}
}

function memberCountValidation() {
	var roomObj = rooms.filter(({ roomName }) => roomName === currentRoomId)[0];
	document.getElementById('memberCountError').innerHTML = '';
	if (usersPresentInMeeting.length > roomObj.seatingCapacity) {
		document.getElementById('memberCountError').innerHTML = 'Members in meeting cannot exceed the seating capacity of room';
	}
}

function enableFilterBtn() {

	var startTime = document.getElementById("startTime").value;
	var endTime = document.getElementById("endTime").value;
	var meetingDate = document.getElementById("meetingDate").value;

	if (meetingDate === '' || startTime === '' || endTime === '') return
	if (document.getElementById('timeError').innerHTML === '' && document.getElementById('dateError').innerHTML === '') {
		document.getElementById('filterbtn').disabled = false
	}
}

function enableConfirmBtn() {
	var meetingTitle = document.getElementById('meetingTitle').value;
	if (meetingTitle.trim() === '' || document.getElementById('memberCountError').innerHTML !== '' || usersPresentInMeeting.length === 0) {
		document.getElementById('confirmbtn').disabled = true
		return;
	}

	document.getElementById('confirmbtn').disabled = false
}


function getCreditsAndFilterRooms() {
	getCredits()
}

function getCredits() {
	var x = new XMLHttpRequest();
	//step 2 how xhr will open connection with server
	x.open("GET", "getCredits.jsp", true);

	//step 3 how xhr will send request
	x.send();

	//step 4 how xhr will get response from server
	//state={0,1,2,3,4}

	x.onreadystatechange = function () {
		if (x.readyState == 4) {
			managerCredits = parseInt(x.responseText.trim());
			filterRooms();
		}
	}
}

function calculateMeetingDuration(startTime, endTime) {
	var [startTimeHH, startTimeMM] = startTime.split(':');
	var [endTimeHH, endTimeMM] = endTime.split(':');
	var time1 = parseInt(startTimeHH) * 60 + parseInt(startTimeMM);
	var time2 = parseInt(endTimeHH) * 60 + parseInt(endTimeMM);

	return (time2 - time1) / 60;
}
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
					`<div class='room' id='${room.roomName}'>
                		<h2>${room.roomName}</h2>
                		<div>Seating capactiy: ${room.seatingCapacity}</div>
                		<div> Cost per hour : ${room.costPerHour}</div>
						<div>Average Rating: ${room.averageRating}</div>
						${managerCredits >= (room.costPerHour * calculateMeetingDuration(startTime, endTime))
						? `<button onClick="openModal(event)">Book now!</button>`
						: `<div class='error'>Insufficient credits to book.</div>`
					}
						</div >`

			}
			document.querySelector('.mycontainer').appendChild(roomContainer);
		}
	}

}


function getUserSuggestions() {
	var name = document.querySelector(".input-container input").value;
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
	memberCountValidation();
	enableConfirmBtn();
}

function removeUserFromMeeting(userId) {
	usersPresentInMeeting = usersPresentInMeeting.filter((id) => id !== userId);
	generatePillsDom();
	memberCountValidation();
	enableConfirmBtn();
}

function generatePillsDom() {
	document.querySelector('.membersList').innerHTML = '';
	if (usersPresentInMeeting && usersPresentInMeeting.length) {
		var pillsContent = '';
		for (var userId of usersPresentInMeeting) {
			pillsContent += `<button  onclick = removeUserFromMeeting(${userId})>
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
					<li onclick = addUsertoMeeting(${userId})> ${userIdToNameMap[userId]}
						<img src="images/plus.png"> </img>
					</li> `;
	}
	dropDownContent += `</ul > `;
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
	var meetingDate = document.getElementById("meetingDate").value.split('-').reverse().join('/');

	var modalbodyHTML = `
					<div id='modal-title'> ${roomObj.roomName} </div>

		<div class='flex-row'>
		<div> <span class='bold'>-> Seating capactiy: </span> ${roomObj.seatingCapacity}</div>
		<div> <span class='bold'>-> Average Rating:</span> ${roomObj.averageRating}</div>
		</div>

		<div class='flex-row'>
		<div> <span class='bold'>-> Meeting Type: </span> ${meetingTypeText} </div>
		<div><span class='bold'>-> Booking date: </span> ${meetingDate} </div>
		</div>

		<div class='flex-row'>
		<div> <span class='bold'>-> Start time: </span> ${startTime} </div>
		<div> <span class='bold'>-> End time: </span> ${endTime} </div>
		</div>

	
	<div class='item-container'>
		<div class='title'> Purpose</div>
		<input id='meetingTitle' onkeyup="enableConfirmBtn()" class='userinput width100' type="text" placeholder="Retrospective meeting">
	</div>

	<div class='item-container'>
		<div class='title'>
									Add members
		 </div>
		 <div class='membersList'></div>
		 <div class='error' id='memberCountError'> </div>
		 <div class='input-container'>
			<input type=text class='width100' onkeyup="getUserSuggestions()"> 
			<div class="content-container">
		</div>
	</div>

	<div class='modal-confirm'>
		<button onclick="saveMeeting()" id='confirmbtn' disabled>Confirm</button>
		<button id='cancelbtn' > Cancel </button>
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

	document.getElementById('confirmbtn').disabled = true

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
			else {
				alert(x.responseText.trim())
				document.getElementById('confirmbtn').disabled = false
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