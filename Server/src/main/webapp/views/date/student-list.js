/**
 * Created by ata2 on 12.12.2016.
 */
function getUserFromDate(sectionId, course, date) { //This function get all students' names that attend a lecture.
    return $.ajax({
        type: "GET",
        url: "http://localhost:8080/rest/attendance/getAttendanceFromDate/" + course + "/" + sectionId + "/" + date,
        async: false // This option prevents this function to execute asynchronized
    });
}

function getAttendanceId(course, sectionId, date) {
    return $.ajax({
        type: "GET",
        url: "http://localhost:8080/rest/attendance/getAttendanceId/" + course + "/" + sectionId + "/" + date,
        async: false // This option prevents this function to execute asynchronized
    });
}

function deleteStudentFromAttendance(attendanceId, userId) { //This function deletes a student from the database.
    return $.ajax({
        type: "DELETE",
        url: "http://localhost:8080/rest/attendanceList/delete/" + attendanceId + "/" + userId ,
        async: false // This option prevents this function to execute asynchronized
    });
}

$(window).on('load', function() {
    // Animate loader off screen
    $(".myModal2").fadeOut("slow");
});

$(document).ready(function(){
    var StudentAttList, StudentAttListObj;
    var date, course, user;


    $.ajax({
        url: '/views/main/main.js',
        dataType: 'script',
        async: false  // This option prevents this function to execute asynchronized
    });
    $.ajax({
        url: '/views/utility/utility.js',
        dataType: 'script',
        async: false  // This option prevents this function to execute asynchronized
    });

    $.ajax({
        url: '/views/eduUser/eduUser.js',
        dataType: 'script',
        async: false  // This option prevents this function to execute asynchronized
    });

    $.ajax({
        url: '/views/WS/websocket.js',
        dataType: 'script',
        async: false  // This option prevents this function to execute asynchronized
    });

    $("#course-home-btn").click(function(){
        window.location.replace("http://localhost:8080/templates/course/course-home.html");
    });

    $("#course-dates-btn").click(function(){
        window.location.replace("http://localhost:8080/templates/date/date.html"); //redirects back to lecturer page
    });

    $("#course-exams-btn").click(function(){
        window.location.replace("http://localhost:8080/templates/exam/exam.html"); //redirects back to lecturer page
    });

    date = JSON.parse(readCookie('courseDate'));
    course = JSON.parse(readCookie('course'));
    user = JSON.parse(readCookie('mainuser'));
    wsSendMessage(user["id"]);
    var img = document.getElementById("studentImage"); //This puts the profile picture of the student to the home page.
    img.src = String(user["ppic"]);

    var img = document.getElementById("studentImage2"); //This puts the profile picture of the student to the home page.
    img.src = String(user["ppic"]);

    var img = document.getElementById("studentImage3"); //This puts the profile picture of the student to the home page.
    img.src = String(user["ppic"]);

    $('#studentName').html(user["name"] + " " + user["surname"]);
    $('#studentButtonName').html(user["name"] + " " + user["surname"]);
    $('#stuName').html(user["name"] + " " + user["surname"]);
    $('#userIdHeader').html(user["id"]);

    StudentAttListObj=getUserFromDate(course["sectionId"], course["id"], date["date"]);
    StudentAttList=JSON.parse(StudentAttListObj.responseText);
    var captions=["ID", "Name", "Surname", "Distance", "Top Coordinate", "Bottom Coordinate", "Left Coordinate", "Right Coordinate"];
    $('#student-list').html(createStudentTable(StudentAttList,captions,1));

    $(".deleteAttendance").click(function(){
        var row = ($(this)[0].id.substr(16));
        var attendance = StudentAttList[row];
        var attendanceIdObj=getAttendanceId(course["id"], course["sectionId"], date["date"]);
        var attendanceId=JSON.parse(attendanceIdObj.responseText)[0]["id"];
        deleteStudentFromAttendance(attendanceId, attendance["id"]);
        window.location.replace("http://localhost:8080/templates/date/student-list.html");
    });

    $("#addNewStudent").click(function(){
        window.location.replace("http://localhost:8080/templates/date/add-student.html");
    });

    $("#backDatePage").click(function(){
        eraseCookie("date");
        window.location.replace("http://localhost:8080/templates/date/date.html"); //redirects back to lecturer page
    });

    document.getElementById("contentHeader").innerHTML = '<h1>' + course["id"] + " " + course["name"] + " / Section " + course["sectionId"] + '</h1>';

    var courseListObj=getAllCourses(user["id"],user["role"]);
    var courseList=JSON.parse(courseListObj.responseText);
    var htmlString = "";

    for(var i=0;i<courseList.length;i++){
        var courseId = courseList[i]["id"];
        var sectionId = courseList[i]["sectionId"];
        var courseName = courseList[i]["name"];
        htmlString += '<li><a href="#" onClick="goToCourseHome('
        htmlString += courseId + ',' + "'" + courseName + "'" +  ',' + sectionId + ')"><i class="fa fa-circle-o"></i>';
        htmlString += courseId;
        htmlString += " - ";
        htmlString += sectionId;
        htmlString += '</a></li>';
    }
    document.getElementById("coursesTreeView").innerHTML = htmlString;

});
