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
            document.getElementById('meetingdate').value = new Date().toISOString().split('T')[0];
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
            <select class='content width100'>
                <option> Classroom Training</option>
                <option>Online Training</option>
                <option>Conference call</option>
                <option>Business</option>
            </select>
        </div>

        <div class='item-container'>
            <div class='title'> Select booking date</div>
            <input class='content width100' type="date" id='meetingdate'>
        </div>

        <div class='flex-row item-container'>
            <div class='flex-item'>
                <div class='title'> Select booking time</div>
                <input class='content' type="time">
            </div>

            <div class='flex-item'>
                <div class='title'> Duration ( hh/mm ) </div>
                <input class='content' type="time">
            </div>
        </div>
       </div>
</body>
</html>