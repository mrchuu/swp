<%-- 
    Document   : header.jsp
    Created on : May 18, 2023, 8:30:02 PM
    Author     : Phan Nguyen Tu Anh
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="entity.User"%>
<%@page import="dao.UserDAO"%>
<%@page import="jakarta.servlet.http.Cookie" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
        Cookie[] cookies = request.getCookies();
        int user_id = 0;
        User currUser = null;
        UserDAO ud = new UserDAO();
        if (cookies != null) {

            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("currUserId")) {
                    user_id = Integer.parseInt(cookie.getValue());
                    currUser = ud.getUserById(user_id);
                }
            }
        } 
        pageContext.setAttribute("currUser", currUser);
        pageContext.setAttribute("currUserId", user_id);
%>
<html>
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <meta name="description" content="">
        <meta name="author" content="Template Mo">
        <link href="https://fonts.googleapis.com/css?family=Poppins:100,200,300,400,500,600,700,800,900;subset=cyrillic,cyrillic-ext,greek,greek-ext,latin-ext,vietnamese" rel="stylesheet">

        <title>Education Template - Meeting Detail Page</title>

        <!-- Bootstrap core CSS -->
        <link href="vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">


        <!-- Additional CSS Files -->
        <link rel="stylesheet" href="assets/css/fontawesome.css">
        <link rel="stylesheet" href="assets/css/templatemo-edu-meeting.css">
        <link rel="stylesheet" href="assets/css/owl.css">
        <link rel="stylesheet" href="assets/css/lightbox.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css" integrity="sha512-iecdLmaskl7CVkqkXNQ/ZH/XLlvWZOJyj7Yy7tcenmpD1ypASozpmT/E0iPtmFIB46ZmdtAc9eNBvH0H/ZpiBw==" crossorigin="anonymous" referrerpolicy="no-referrer" />
        <style>
            .ContactIcon{
                display: inline-block;
                height: 30px;
                width: 30px;
                position: fixed;
                right: 60px;
                bottom: 50px;
                z-index: 9999;
                pointer-events: auto;
            }
            #MessageBox{
                background-color: black;
                width: 300px;
                padding: 10px;
                height: 370px;
                position: fixed;
                right: 100px;
                bottom: 70px;
                z-index: 999;
                border: 1px solid #2f7479
            }
            .ContactIcon i{
                color: #52c7d0;
                cursor: pointer
            }
            .Toast{
                display: inline-block;
                width: 15%;
                position: fixed;
                top: 100px;
                right: 0;
                z-index: 999;
                padding: 15px 10px
            }
            #Suggestion{
                position: fixed;
                background-color: #fdfffd;
                box-shadow: rgba(0, 0, 0, 0.35) 0px 5px 15px;
                width: 279px;
                top: 55%;
                max-height: 200px;
                overflow-y: auto;
                z-index: 9999;
            }
            .suggestEmail{
                padding: 5px;
                padding-left: 15px;
                border-bottom: 1px solid #ababab
            }
        </style>
    </head>

    <body>
        <c:if test="${currUser != null}">
            <div class="Toast" id="Toast" style="background-color: #66ff66; color: white; display: none">Message Sent Successfully!!</div>
            <div class="ContactIcon"><i onclick="ToggleMessageBox(); checkValidEmail()" class="far fa-comment-dots fa-2x"></i></div>
            <div id="MessageBox" class="MessageBox" style="display: none">
                <form action="SendMessage" method="Post">
                    <div class="input-group mb-3">
                        <p class="form-text col-12" style="color: white">Send To: </p>
                        <input id="receiverEmail" oninput="Suggest()" onchange="Suggest()" type="text" name="receiver" class="form-control" placeholder="example@gmail.com" aria-label="Recipient's username" aria-describedby="basic-addon2">
                        <div id="Suggestion">
                            <ul></ul>
                        </div>
                    </div>
                    <div class="input-group mb-3">
                        <p class="form-text col-12" style="color: white">Content: </p>
                        <textarea id="content" class="col-12" rows="7" name="content">
                        
                        </textarea>
                    </div>
                    <div class="input-group mb-3" style="justify-content: right; margin-bottom: 10px !important">
                        <button type="button" onclick="SendMessage()" class="btn btn-info"><i class="fa fa-paper-plane-o"></i></button>
                    </div>

                </form>
            </div>
        </c:if>
        <div class="sub-header">
            <div class="container">
                <div class="row">
                    <div class="col-lg-8 col-sm-8">
                        <div class="left-content">
                            <p>Đây là một website giúp các bạn tìm kiếm khóa học online phù hợp</p>
                        </div>
                    </div>
                    <div class="col-lg-4 col-sm-4">
                        <div class="right-icons">

                        </div>
                    </div>
                </div>
            </div>
        </div>

        <!-- ***** Header Area Start ***** -->
        <header class="header-area header-sticky">
            <div class="container">
                <div class="row">
                    <div class="col-12">
                        <nav class="main-nav">
                            <!-- *** Logo Start *** -->
                            <a href="homepage" class="logo">
                                Edu Meeting
                            </a>
                            <!-- *** Logo End *** -->
                            <!-- *** Menu Start *** -->
                            <ul class="nav">
                                <li><a href="LeaderBoard">BXH người dùng <i class="fa-sharp fa-solid fa-ranking-star fa-2x"></i></a></li>
                                <li><a href="mycourselistservlet">KHÓA HỌC CỦA TÔI</a></li>
                                <li><a href="MailBox?mode=inbox">HÒM THƯ</a></li>
                                    <c:if test="${currUser != null}">

                                    <c:if test="${currUser.getRoleId() == 1 || currUser.getRoleId() == 3 || currUser.getRoleId() == 4 || currUser.getRoleId() == 5}">
                                        <li class="has-sub">
                                            <a href="javascript:void(0)">Pages</a>
                                            <ul class="sub-menu">
                                                <c:if test="${currUser.getRoleId() == 1 || currUser.getRoleId() == 4}">

                                                    <li><a href="DashBoard">DashBoard</a></li>
                                                    <li><a href="postListEdit">POST LIST EDIT</a></li>
                                                    <li><a href="slidersListEdit">SLIDER LIST EDIT</a></li>
                                                    <li><a href="pricePackageEdit">Chỉnh sửa gói đăng kí</a></li>
                                                    </c:if>
                                                    <c:if test="${currUser.getRoleId() == 3}">

                                                    <li><a href="courseListEdit">Edit Course List</a></li>                                        
                                                    </c:if>

                                                <c:if test="${currUser.getRoleId() == 1}">

                                                    <li><a href="AdminDashBoard">Admin DashBoard</a></li>
                                                    <li><a href="subjectEdit">Chỉnh sửa danh sách môn học</a></li>
                                                    <li><a href="postCategoryEdit">Chỉnh sửa danh mục các post</a></li>
                                                    </c:if>
                                            </ul>

                                        </li>
                                    </c:if>

                                </c:if>

                                <li><a href="PersonalAccountServlet?viewerId=${currUserId}&ProfileId=${currUserId}">TRANG CÁ NHÂN</a></li> 
                            </ul>        
                            <a class='menu-trigger'>
                                <span>Menu</span>
                            </a>

                            <!-- *** Menu End *** -->
                        </nav>
                    </div>
                </div>
            </div>
        </header>


    </body>
    <script>


        var emailList;


        document.getElementById("content").addEventListener("click", function () {
            // Set focus on the text area
            this.focus();

            // Set the cursor to the top left position (index 0)
            this.setSelectionRange(0, 0);
        });
        function ToggleMessageBox() {
            var mb = document.getElementById("MessageBox");
            if (mb.style.display == "none") {
                mb.style.display = "inline-block";
            } else {
                mb.style.display = "none";
            }
        }
        function Suggest() {
            var receiverEmail = document.getElementById("receiverEmail").value;
            var Suggestion = document.getElementById("Suggestion");
            Suggestion.innerHTML = "";
            emailList.forEach(function (e) {
                if (e.includes(receiverEmail) && receiverEmail !== "" && receiverEmail !== e) {
                    let li = document.createElement('li');
                    let suggestionDiv = document.createElement('div');
                    suggestionDiv.classList.add("suggestEmail");
                    const email = document.createElement('p');
                    email.textContent = e;
                    li.onmouseover = function () {
                        document.getElementById("receiverEmail").value = e;
//                        li.style.backgroundColor = "#8BF0F2";
                    }
                    suggestionDiv.appendChild(email);
                    li.appendChild(suggestionDiv);
                    Suggestion.appendChild(li);
                }
            });
        }
        function checkValidEmail() {
            var xhr = new XMLHttpRequest();
            xhr.open('POST', 'GetAllEmail', true);
            xhr.onreadystatechange = function () {
                if (xhr.readyState === XMLHttpRequest.DONE && xhr.status === 200) {
                    // Parse the response text as JSON to get the array
                    var response = xhr.responseText;
                    // Make sure emailList is an array before using forEach
                    emailList = response.split(',');
                    emailList.forEach(function (e) {
                        console.log(e);
                    });
                }
            };
            xhr.send();
        }

        function SendMessage() {
            var receiverEmail = document.getElementById("receiverEmail").value;
            var content = document.getElementById("content").value;
            var Toast = document.getElementById("Toast");
            var url = "SendMessage?receiver=" + encodeURIComponent(receiverEmail) + "&content=" + encodeURIComponent(content);
            console.log(url)
            var xmlHttp = new XMLHttpRequest();
            xmlHttp.open("Post", url, true);
            xmlHttp.onreadystatechange = function () {
                if (xmlHttp.readyState === 4 && xmlHttp.status === 200) {
                    if (xmlHttp.responseText == 1) {
                        Toast.style.display = "inline-block";
                        setTimeout(() => {
                            Toast.style.display = "none";
                        }, 3000);

                    } else {
                        window.location.href = "banned.jsp";

                    }
                }
            };
            xmlHttp.send();
        }
    </script>
    <script src="vendor/jquery/jquery.min.js"></script>
    <script src="vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

    <script src="assets/js/isotope.min.js"></script>
    <script src="assets/js/owl-carousel.js"></script>
    <script src="assets/js/lightbox.js"></script>
    <script src="assets/js/tabs.js"></script>
    <script src="assets/js/video.js"></script>
    <script src="assets/js/slick-slider.js"></script>
    <script src="assets/js/custom.js"></script>
</html>
