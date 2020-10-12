
function enableSubmit() {
    document.getElementById('submitbtn').disabled = false
}


function submitRating() {
    var roomId = document.getElementById("room").value;
    var rating = document.getElementById("feedbackrating").value;
    var x = new XMLHttpRequest();
    //step 2 how xhr will open connection with server
    x.open("GET", "submitFeedback.jsp?roomId=" + roomId + "&rating=" + rating, true);

    //step 3 how xhr will send request
    x.send();

    //step 4 how xhr will get response from server
    //state={0,1,2,3,4}

    x.onreadystatechange = function () {
        if (x.readyState == 4) {
            alert(x.responseText.trim());
        }
    }
}
