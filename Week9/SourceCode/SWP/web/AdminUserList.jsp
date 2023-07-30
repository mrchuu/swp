<%-- 
    Document   : AdminUserList
    Created on : Jul 24, 2023, 2:37:40 PM
    Author     : FPT
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
        <style>
            .nav-item{
                margin: 30px auto !important;
            }
            .active a{
                color: #e3a31e
            }
        </style>
    </head>
    <body>
        <jsp:include page="header.jsp"/>

        <!-- Cach su dung template: dung 2 the include de lay header va footer, sau do pass section o be duoi vao 
        giua roi chen noi dug trang vao phan ben duoi -->
        <section class="heading-page header-text">
            <div class="container-fluid">
                <div class="row" style="min-height: 600px">
                    <div class="col-2" style="background-color: rgb(40,45,49, 0.6); ">
                        <div class="nav-item">
                            <a href="AdminDashBoard">Admin DashBoard</a>
                        </div>
                        <div class="nav-item">
                            <a href="ReportedMessages">Reported Messages</a>
                        </div>
                        <div class="nav-item active">
                            <a href="AdminUserList">UserList</a>
                        </div>
                        <div class="nav-item">
                            <a href="">Pending Courses</a>
                        </div>

                    </div>
                    <div class="col-10" style="background-color: rgb(0,0,0, 0.3);">
                        <div class="row">
                            <h4 style="color: white">User List</h4>                      
                        </div>
                        <div class="row">
                            <div class="input-group">
                                <div class="form-outline">
                                    <input id="searchBar" type="search" id="form1" class="form-control" />
                                    <label class="form-label" for="form1">Search</label>
                                </div>
                                <button id="search-button" type="button" class="btn btn-primary"  style="height: 38px">
                                    <i class="fas fa-search"></i>
                                </button>
                            </div>
                        </div>
                        <div class="row">
                            <table id="userTable" class="table" style="color: white">
                                <thead>
                                    <tr>
                                        <th>ID</th>
                                        <th>User Name</th>
                                        <th>Email</th>
                                        <th>Phone Number</th>
                                        <th>Action</th>

                                    </tr>
                                </thead>
                                <tbody>

                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
            </div>
        </section>


        <jsp:include page="footer.jsp"/>
        <script>
            // Parse the JSON data passed from the servlet
            var users = ${requestScope.userJson};
            console.log(users.length);
            //            Function to render the table based on search input
            function renderTable(searchTerm) {
                var tableBody = $("#userTable tbody");
                tableBody.empty(); // Clear existing table rows

                users.forEach(function (user) {
                    // Check if the user name contains the search term
                    if (user.userEmail.includes(searchTerm)) {
                        // Append a new table row with user data
                        var newRow = $("<tr>");
                        newRow.append($("<td>").text(user.userId));
                        newRow.append($("<td>").text(user.fullName));
                        newRow.append($("<td>").text(user.userEmail));
                        newRow.append($("<td>").text(user.userPhone));
                        if (user.userStatus === true) {
                            newRow.append($("<td>").html('<div onClick="banUser(' + user.userId + ')"><i class="fa-solid fa-user-lock" style="color: red" id="ban' + user.userId + '"></i></div>'));
                        } else {
                            newRow.append($("<td>").html('<div onClick="UnbanUser(' + user.userId + ')"><i class="fa-solid fa-user-lock" style="color: RGB(39 108 173)" id="unban' + user.userId + '"></i></div>'));
                        }
//                        
                        console.log(user.userStatus)
                        // Add more columns for other properties if needed
                        tableBody.append(newRow);
                    }
                });
            }
            function banUser(userid) {
                var url = "AdminUserList?user_id=" + encodeURIComponent(userid) + "&status=" + encodeURIComponent(0);
                var button = document.getElementById("ban" + userid);
                var xmlHttp = new XMLHttpRequest();
                xmlHttp.open("Post", url, true);
                xmlHttp.onreadystatechange = function () {
                    if (xmlHttp.readyState === 4 && xmlHttp.status === 200) {
                        window.location.reload();
                    }
                };
                xmlHttp.send();
                renderTable("");
            }

            function UnbanUser(userid) {
                var url = "AdminUserList?user_id=" + encodeURIComponent(userid) + "&status=" + encodeURIComponent(1);
                var xmlHttp = new XMLHttpRequest();
                xmlHttp.open("Post", url, true);
                xmlHttp.onreadystatechange = function () {
                    if (xmlHttp.readyState === 4 && xmlHttp.status === 200) {
                        console.log(xmlHttp.responseText)
                        window.location.reload();
                    }
                };
                xmlHttp.send();
                console.log("aaa")
            }
            // Initialize the table with all users
            window.onload = function () {
                renderTable("");
            };

            // Add event listener to update table on search input
            $("#searchBar").on("input", function () {
                var searchTerm = $(this).val().toLowerCase();
                renderTable(searchTerm);
            });
        </script>
    </body>
</html>
