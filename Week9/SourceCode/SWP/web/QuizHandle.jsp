<%-- 
    Document   : BlogDetails
    Created on : May 19, 2023, 1:09:09 PM
    Author     : Phan Nguyen Tu Anh
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ page import="java.sql.Timestamp" %>
<%@ page import="entity.Question" %>
<%@ page import="entity.Choice" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <meta name="description" content="">
        <meta name="author" content="Template Mo">
        <link href="https://fonts.googleapis.com/css?family=Poppins:100,200,300,400,500,600,700,800,900" rel="stylesheet">

        <title>Education Template - Meeting Detail Page</title>

        <!-- Bootstrap core CSS -->
        <link href="vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>



        <!-- Additional CSS Files -->
        <link rel="stylesheet" href="assets/css/fontawesome.css">
        <link rel="stylesheet" href="assets/css/templatemo-edu-meeting.css">
        <link rel="stylesheet" href="assets/css/owl.css">
        <link rel="stylesheet" href="assets/css/lightbox.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">

        <style>

            .submit1{
                background-color: #ced3b6; /* Màu nền của nút */
                border: none; /* Không có viền */
                color: white; /* Màu chữ */
                padding: 10px 20px; /* Kích thước lề trong nút */
                text-align: center; /* Căn giữa chữ trong nút */
                text-decoration: none; /* Không gạch chân chữ */
                display: inline-block; /* Hiển thị nút như một khối */
                font-size: 16px; /* Kích thước chữ */
                cursor: pointer; /* Con trỏ chuột trở thành bàn tay khi di chuột vào nút */
                border-radius: 4px; /* Đường cong viền của nút */
            }
            th, td {
                text-align: left;
                padding: 8px;
                border: 3px solid #b9b9b9 !important;
                color: Black;
                background-color: #e7e9dc;
            }

            .table-container {
                text-align: center;
            }

            table {
                display: block;
                margin: 0 auto;
                width: 1000px;
                border-radius: 15px
            }
            td{
                width: 850px;
            }
            th, td {
                text-align: left;
                padding: 8px;
                border: 3px solid #ddd;
            }
            .sidebar {
                width: 100%;
                padding: 20px;
                background-color: #edeee7;
                box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
            }
            .sidebar h3 {
                margin-top: 0;
            }
            .search-form {
                margin-bottom: 20px;
            }
            .search-form input[type="text"] {
                width: 100%;
                padding: 10px;
                border: none;
                border-radius: 5px;
                box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
            }
            .widget-title {
                color: black;
            }
            .widgetre ul a {
                border: 1px solid black;
                border-radius: 5px;
                padding: 5px;
                margin-right: 5px;
            }
            .add-post-button {
                float: left;
                margin: 10px;
            }
            .navbar2 {
                left: -10px;
                padding-top: 10px;
                padding-bottom: 10px;
            }

            .navbar2-brand {
                font-size: 20px;
                font-weight: bold;
                color: #333;
                margin-right: 10px;
                margin-left: 10px;
            }
            .navbar2-brand-divider{
                color: #2ea7be;
            }
            .navbar2-divider {
                font-size: 20px;
                color: #333;
                margin-right: 10px;
                margin-left: 10px;
            }
            .quiz-all {
                display: flex;
                flex-wrap: wrap;
                justify-content:  flex-start;

            }

            .quiz-all a{
                color: #646464
            }
            .quiz-square {
                width: 30px;
                height: 30px;
                margin: 3px;
                background-color: #ccc;
                display: flex;
                justify-content: center;
                align-items: center;
                font-size: 15px;
            }
            .table2 {
                border-collapse: collapse;
                width: 80%;
                margin: 0 auto;
            }

            .vertical-header {
                padding: 40px 20px;
            }
            .small-text{
                font-family: Arial, sans-serif;

                font-size: 12px;
                text-align: start;

            }

            .flag-icon.active {
                color: red;
            }

            .flag-icon {
                color: black;
            }

            .col-sm-9 table {
                width: 100%;
            }
            .quiz-all mb-9{
                background-color: #E05A7D;

            }
            .quiz-square{
                background-color: #9edcde;
            }
            .answered{
                background-color: #00ff00
            }
            .flagged{
                background-color: #e05a7d !important
            }
        </style>
    </head>
    <!--    chay dong ho khi trang load-->
    <body onload="clock()"> 
        <jsp:include page="header.jsp"/>

        <!-- Cach su dung template: dung 2 the include de lay header va footer, sau do pass section o be duoi vao 
        giua roi chen noi dug trang vao phan ben duoi -->


        <section class="heading-page header-text" style="padding-top: 100px;">
            <div class="navbar2" style="text-align: left; margin-top: 30px">
                <div class="container2" style="display: inline-block; border-bottom: 1px solid #2EA7BE; margin-left: 7%">
                    <span class="navbar2-brand"><a href="LessonListController?Course_id=${requestScope.Course.getCourse_id()}">${requestScope.Course.getCourse_name()}</a></span>
                    <span class="navbar2-brand-divider">/</span>
                    <span class="navbar2-brand"><a href="" style="color: #f4c463;pointer-events: none;cursor: default;">${requestScope.requestedQuiz.getQuiz_name()}</a></span>

                </div>
            </div>
            <!-- Form start -->
            <form method="post" action="SubmitQuiz">
                <input type="hidden" name="quiz_id" value="${requestScope.quiz_id}"><!-- send quiz_id to SubmitQuiz -->
                <div class="container" style="margin-top: 30px;">
                    <div class="row">
                        <div class="col-sm-9">
                            <table>
                                <tr>
                                    <th>Start on:</th>
                                    <td>1</td>
                                </tr>
                                <tr>
                                    <th>State</th>
                                    <td>finish</td>
                                </tr>
                                <tr>
                                    <th>complete on</th>
                                    <td>Sample Category</td>
                                </tr>
                                <tr>
                                    <th>Time taken</th>
                                    <td><span id="clock"></span></td>
                                <input type="hidden" name="start_time"><!-- truyen parameter start_time sang servlet -->
                                </tr>
                                <tr>
                                    <th>Mark</th>
                                    <td>Active</td>
                                </tr>
                                <tr>
                                    <th>Grade</th>
                                    <td>John Doe</td>
                                </tr>     
                            </table>

                            <%
                                Vector<Question> quesList = (Vector<Question>)request.getAttribute("quesList"); 
                            %>
                            <table class="Table 2" style="margin: 50px 0">
                                <% for(int i = 1; i <= quesList.size(); i++){ %>
                                <tr id="ques-section<%=i%>">
                                    <th class="vertical-header top-header" rowspan="2">Question <%=i%><br><span class="small-text">(Complete)</span><br><span class="small-text">Mark:1.0</span><div class="small-text"">
                                            <input type="hidden" name="flag<%=i%>" id="flag<%=i%>" value="false">
                                            <input type="hidden" name="ques<%=i%>" value="<%= quesList.get(i-1).getQues_id() %>"><!-- send question id to servlet -->
                                            <a onclick="toggleFlag('<%=i%>')">
                                                <i class="far fa-flag  flag-icon" ><br>
                                                    <span class="small-text"> Flag question</span>
                                                </i>
                                            </a>
                                        </div></th>
                                    <td><i class="fa-sharp fa-light fa-flag-pennant"></i><%= quesList.get(i-1).getQues_content() %></td>
                                <i class="fa-sharp fa-light fa-flag-pennant"></i>
                                </tr>
                                <tr>
                                    <td>
                                        <%
                                            boolean hasSelectedOption = false;
                                            for(Choice c : quesList.get(i-1).getChoices()){
                                        %>
                                        <input type="radio" name="answer<%=i%>" value="<%=c.getChoice_content()%>"
                                               onclick="HandleChoiceSelection('<%=i%>', '<%=c.getChoice_id()%>')"
                                               id="radio<%=c.getChoice_id()%>"
                                               ><%=c.getChoice_content()%><br> 
                                        <% } %>
                                        <input type="radio" name="answer<%=i%>" value="not answered" checked hidden>
                                    </td>

                                </tr>
                                <% } %>
                            </table>
                        </div>
                        <!--                        chuyen trang-->
                        <div class="col-sm-3">
                            <div class="card bg-light mb-3" style="position: fixed; width: 25%">
                                <div class="sidebar">
                                    <p>Quiz Navigation</p>
                                    <div class="quiz-all mb-9">
                                        <%
                                        for(int i = 1; i <= quesList.size(); i++){
                                        if(i<10){%>
                                        <a href="#ques-section<%=i%>"><div id="question-square-<%=i%>" class="quiz-square rounded question-square-<%=i%>">0<%=i%></div></a>
                                        <%}else{%>
                                        <a href="#ques-section<%=i%>"><div id="question-square-<%=i%>" class="quiz-square rounded question-square-<%=i%>"><%=i%></div></a>
                                            <%}}%>
                                    </div>
                                </div>
                                <div class = "submit1">
                                    <input type="submit" value="Submit">
                                </div>          
                            </div>
                        </div> 
                    </div>
            </form>
        </div>
    </div>
    <div class="modal show" id="myModal" data-bs-backdrop="static">
        <div class="modal-dialog modal-dialog-centered">
            <div class="modal-content">

                <!-- Modal Header -->
                <div class="modal-header">
                    <h6 style="color: black" class="modal-title">Bạn chưa có quyền sử dụng tính năng này</h6>

                </div>

                <!-- Modal body -->
                <div class="modal-body">
                    Hãy đăng kí các gói vip của chúng tôi để mở khoá tính năng làm quiz và theo dõi tiến độ của bản thân tại <a href="PricePackageSubcription">đây</a>
                </div>

                <!-- Modal footer -->


            </div>
        </div>
    </div>

</section>



<jsp:include page="footer.jsp"/>

</body>
<script>
    var currSub = '${requestScope.currentSubscription}';
    document.addEventListener("DOMContentLoaded", function () {
        if (currSub == "") {
            var myModal = new bootstrap.Modal(document.getElementById("myModal"));
            myModal.show();
            setTimeout(() => {
                window.location.href = "PricePackageSubcription"
            }, 5000);
        }
        
    });
    function toggleFlag(i) {
        var questionBox = document.getElementById("question-square-" + i);
        var flag = document.getElementById("flag" + i);
//        console.log(questionBox.class)
        if (questionBox.classList.contains("flagged")) {
            questionBox.classList.remove("flagged");
            flag.value = "false";
        } else {
            flag.value = "true";
            questionBox.classList.add("flagged");
        }

    }
    function HandleChoiceSelection(i, choice_id) {
        var questionBox = document.getElementById("question-square-" + i);
        var radioButton = document.getElementById("radio" + choice_id);
        questionBox.classList.add("answered")
    }

    var referenceTime = new Date(); // start with the current time
    var year = referenceTime.getFullYear().toString();
    var month = ('0' + (referenceTime.getMonth() + 1)).slice(-2);
    var day = ('0' + referenceTime.getDate()).slice(-2);
    var hours = ('0' + referenceTime.getHours()).slice(-2);
    var minutes = ('0' + referenceTime.getMinutes()).slice(-2);
    var seconds = ('0' + referenceTime.getSeconds()).slice(-2);
    var milliseconds = ('00' + referenceTime.getMilliseconds()).slice(-3);
    var formattedTime = year + '-' + month + '-' + day + ' ' + hours + ':' + minutes + ':' + seconds + '.' + milliseconds;
    document.getElementsByName("start_time")[0].value = formattedTime;

    function clock() {
        var now = new Date();
        var timeDiff = Math.floor((now.getTime() - referenceTime.getTime()) / 1000); // get the difference in seconds
        var hoursPassed = Math.floor(timeDiff / (60 * 60)); // convert to hours
        var minutesPassed = Math.floor((timeDiff / 60) % 60); // convert to minutes
        var secondsPassed = timeDiff % 60; // get the remaining seconds

        // add leading zeros to hours, minutes, and seconds if needed
        hoursPassed = (hoursPassed < 10 ? "0" : "") + hoursPassed;
        minutesPassed = (minutesPassed < 10 ? "0" : "") + minutesPassed;
        secondsPassed = (secondsPassed < 10 ? "0" : "") + secondsPassed;

        // display the formatted time
        document.getElementById("clock").innerHTML = hoursPassed + ":" + minutesPassed + ":" + secondsPassed;

        // update the clock every second
        setTimeout(function () {
            clock();
        }, 1000);
    }

    function moveToElement(element) {
        document.getElementByName(element).scrollIntoView();
    }
</script>
</html>