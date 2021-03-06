function deleteUser(userId) { //This function deletes a student from the database.
    return $.ajax({
        type: "DELETE",
        url: "http://localhost:8080/rest/user/delete/" + userId ,
        async: false // This option prevents this function to execute asynchronized
    });
}



$(document).ready(function(){
    var userlist,userlistobj;
    var user;
    $.ajax({
        url: '/views/main/main.js',
        dataType: 'script',
        async: false  // This option prevents this function to execute asynchronized
    });
    //First eduUser.js and utility.js is imported to admin-home.js
    $.ajax({
        url: '/views/eduUser/eduUser.js',
        dataType: 'script',
        async: false  // This option prevents this function to execute asynchronized
    });
    $.ajax({
        url: '/views/utility/utility.js',
        dataType: 'script',
        async: false  // This option prevents this function to execute asynchronized
    });

    $.ajax({
        url: '/views/WS/websocket.js',
        dataType: 'script',
        async: false  // This option prevents this function to execute asynchronized
    });

    $('#adm-register').click(function () {
        window.location.replace("http://localhost:8080/templates/register/register.html"); //Button that redirects to register page
    });

    user = JSON.parse(readCookie('mainuser'));

    wsSendMessage(user["id"]);
    var img = document.getElementById("studentImage"); //This puts the profile picture of the student to the home page.
    img.src = String(user["ppic"]);

    var img = document.getElementById("studentImage2"); //This puts the profile picture of the student to the home page.
    img.src = String(user["ppic"]);

    var img = document.getElementById("studentImage3"); //This puts the profile picture of the student to the home page.
    img.src = String(user["ppic"]);

    $('#studentName').html(user["name"] + " " + user["surname"])
    $('#studentButtonName').html(user["name"] + " " + user["surname"])
    $('#stuName').html(user["name"] + " " + user["surname"])
    $('#userIdHeader').html(user["id"]);


    userlistobj=getUserList();
    userlist=JSON.parse(userlistobj.responseText);
    var captions=["User Id","Name","Surname","E-Mail","Role"];
    $('#userlist').html(createUserTable(userlist,captions));

    $('.btn-info').click(function () {

        var row=parseInt($(this)[0].id.substr(6)); //Row ids are update#(number) so first 6 characters("update") is not important.

        var user=userlist[row];// After parsing row, now we have row index for userlist

        createCookie('user',JSON.stringify(user),1); // A cookie is created for the upload page.Cookie has the information about user and keeps it as a JSON.

        window.location.replace("http://localhost:8080/templates/register/update.html"); //That redirects to update page
    });


    $('.btn-danger').click(function () {
        var row=parseInt($(this)[0].id.substr(6)); //Row ids are update#(number) so first 6 characters("update") is not important.

        var user=userlist[row];// After parsing row, now we have row index for userlist

        deleteUser(user["id"]);

        window.location.replace("http://localhost:8080/templates/home/admin-home.html"); //That redirects to update page

    });



});

