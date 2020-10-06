<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Organize meeting</title>
<link rel="stylesheet" href="css/organizeMeeting.css">

    <script>
        function ready() {
            //initialze meeting date to today's date
            document.getElementById('meetingDate').value = new Date().toISOString().split('T')[0];
        }

        function filterRooms() {
            
            var startTime = document.getElementById("startTime").value;
            var duration = document.getElementById("duration").value;
            var meetingType = document.getElementById("meetingType").value;
            var meetingDate = document.getElementById("meetingDate").value;

            
        	var x=new XMLHttpRequest();
        	//step 2 how xhr will open connection with server
        	x.open(	"GET",
                	"filterMeetingRooms.jsp"+
                	"?startTime=" + startTime  + 
                	"&duration=" + duration + 
                	"&meetingType=" + meetingType + 
                	"&meetingDate=" + meetingDate
                	,true
                  );
        	
        	//step 3 how xhr will send request
        	x.send();
        	
        	//step 4 how xhr will get response from server
        	//state={0,1,2,3,4}
        	
        	x.onreadystatechange=function(){
        		if(x.readyState==4){
        			var vv=x.responseText;	
        		}
        	}

        }

        // DOMContentLoaded event – DOM is ready, so the handler can lookup DOM nodes, initialize the interface.
        document.addEventListener('DOMContentLoaded', ready);
    </script>
</head>
<body>
	    <div class='container'>
        <h1> Organize a meeting </h1>

        <div class='item-container'>
            <div class='title'> Purpose</div>
            <input class='content width100' type="text" placeholder="Retrospective meeting">
        </div>

        <div class='item-container'>
            <div class='title'> Select meeting type</div>
            <select class='content width100' id='meetingType'>
                <option value='1000'> Classroom Training</option>
                <option value='1001'>Online Training</option>
                <option value='1002'>Conference call</option>
                <option value='1003'>Business</option>
            </select>
        </div>

        <div class='item-container'>
            <div class='title'> Select booking date</div>
            <input class='content width100' type="date" id='meetingDate'>
        </div>

        <div class='flex-row item-container'>
            <div class='flex-item'>
                <div class='title'> Select start time</div>
                <input class='content' type="time" id='startTime'>
            </div>

            <div class='flex-item'>
                <div class='title'> Select end time </div>
                <input class='content' type="time" id='duration'>
            </div>
        </div>
        <button onclick="filterRooms()" id='filterbtn'>Filter Rooms</button>
    </div>
</body>
</html>