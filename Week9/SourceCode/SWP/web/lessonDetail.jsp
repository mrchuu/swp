<%-- 
    Document   : quizzLesson
    Created on : May 28, 2023, 3:47:02 PM
    Author     : admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="entity.Course" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Vector" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html lang="en">

    <head>

        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <meta name="description" content="">
        <meta name="author" content="Template Mo">
        <link href="https://fonts.googleapis.com/css?family=Poppins:100,200,300,400,500,600,700,800,900" rel="stylesheet">

        <title> Lesson List </title>

        <!-- Bootstrap core CSS -->
        <link href="vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">


        <!-- Additional CSS Files -->
        <link rel="stylesheet" href="assets/css/fontawesome.css">
        <link rel="stylesheet" href="assets/css/templatemo-edu-meeting.css">
        <link rel="stylesheet" href="assets/css/owl.css">
        <link rel="stylesheet" href="assets/css/lightbox.css">
        <style>
            .topnav {
                width: 20%;
                margin: 0 auto;
                overflow: hidden;
                margin-bottom: 50px;
            }
            .topnav .search-container {
                float: right;
            }

            .topnav input[type=text] {
                padding: 6px;
                margin-top: 8px;
                font-size: 17px;
                border: none;
            }

            .topnav .search-container button {
                float: right;
                padding: 6px 10px;
                margin-top: 8px;
                margin-right: 16px;
                background: #ddd;
                font-size: 17px;
                border: none;
                cursor: pointer;
            }

            .topnav .search-container button:hover {
                background: #ccc;
            }

            .badge-success {
                display: inline-block;
                padding: 5px 10px;
                font-size: 14px;
                font-weight: bold;
                color: #ffffff;
                background-color: #28a745;
                border-radius: 4px;
            }

            .badge-danger {
                display: inline-block;
                padding: 5px 10px;
                font-size: 14px;
                font-weight: bold;
                color: #ffffff;
                background-color: #dc3545;
                border-radius: 4px;
            }
            .lessonDesc h4{
                text-align: left;
                color: white;
                margin: 10px 0;
                display: inline-block;
                width: 50%;
            }
            .lessonDesc{
                margin-top: 30px !important;
                display: flex;
            }
            .markAsDone {
                width: 30%;
                background-color: #336699;
                padding: 20px 0;
                margin: 50px auto !important;
                border-radius: 25px;
            }
            .markAsDone button{
                background-color: inherit;
                border: none
            }
            .Done {
                width: 30%;
                background-color: #47d06e;
                padding: 20px 0;
                margin: 50px auto !important;
                border-radius: 25px;
            }
            .markAsDone h4, .Done h4{
                color: white;
            }
            .markAsDone h4:hover{
                color: #d8e379
            }
            .navigation {
                display: flex !important;
                justify-content: left;
                margin-bottom: 30px;
            }
            .navigation a{
                display: inline-block !important;

            }
            .navigation h4{
                display: inline-block
            }
            .navigation a:nth-child(3){
                position: relative;
                left: 600px;
            }
            .lessonStatus{
                display: flex;
                align-items: center;
                justify-content: end;
                border: 1px solid
            }
            .lessonStatusWrapper{
                display: inline-block;
                width: 50% !important

            }
            .footer{
                margin-top: 0 !important
            }
            .meetings-page{
                padding-top: 0 !important;
            }
        </style>
        <!--
        
        TemplateMo 569 Edu Meeting
        
        https://templatemo.com/tm-569-edu-meeting
        
        -->
    </head>

    <body>
        <jsp:include page="header.jsp"/>

        <!-- Cach su dung template: dung 2 the include de lay header va footer, sau do pass section o be duoi vao 
        giua roi chen noi dug trang vao phan ben duoi -->
        <section class="heading-page header-text">
            <div class="container">
                <div class="navigation">
                    <a href="LessonListController?Course_id=${Course_id}"><h4 style="text-align: left">${requestScope.courseName} / </h4></a>
                    <a href="#"><h4 style="text-align: left; color: #ffcc66">${lesson.getLesson_name()} </h4></a>
                    <c:if test="${requestScope.currUser.getRoleId()==3}">
                        <a href="EditLessonContent?lesson_id=${lesson.getLesson_id()}&lesson_Name=${lesson.getLesson_name()}&lesson_Video=${lesson.getLesson_video()}&Lesson_desc=${lesson.getLesson_desc()}">
                            <i class="fa fa-edit fa-2x" style="color:#31c8ff"></i>
                        </a>
                    </c:if>

                </div>
                <div class="row">
                    <iframe width="400" height="700" src="${lesson.getLesson_video()}" frameborder="0" allowfullscreen></iframe>                   
                </div>
                <div class="row lessonDesc">
                    <h4>Nội dung bài học</h4>
                    <c:if test="${requestScope.lesson.isLesson_status() == true}">
                        <div class="lessonStatusWrapper">
                            <div class="lessonStatus">
                                <i class="fa fa-circle" style="color: #75ff91;"></i><p style="color: #75ff91;">&nbsp;&nbsp;&nbsp;Active</p>
                            </div>
                        </div>
                    </c:if>

                </div>
                <div class="lessonDesc1">
                    <h5 style="color: white; text-align: left">${lesson.getLesson_desc()}</h5>
                </div>
                <c:if test="${requestScope.currUser.getRoleId()==2}">
                    <c:if test="${requestScope.done == false}">
                        <div class="row markAsDone" id="markAsDone">
                            <button onclick="markAsDone(${requestScope.currUser.getUserId()}, ${requestScope.lesson.getLesson_id()})">
                                <h4>Mark as Done</h4>
                            </button>
                        </div>
                    </c:if>
                    <c:if test="${requestScope.done == true}">
                        <div class="row Done" id="Done">
                            <h4>Done</h4>
                        </div>
                    </c:if>
                </c:if>
                <h4 style="all:unset; color: #33ccf4">Hoàn thành 1 bài học video sẽ thêm 5 điểm tích luỹ</h4>
            </div>
        </section>

        <jsp:include page="footer.jsp"/>

        <!-- Scripts -->
        <!-- Bootstrap core JavaScript -->
        <script src="vendor/jquery/jquery.min.js"></script>
        <script src="vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

        <script src="assets/js/isotope.min.js"></script>
        <script src="assets/js/owl-carousel.js"></script>
        <script src="assets/js/lightbox.js"></script>
        <script src="assets/js/tabs.js"></script>
        <script src="assets/js/isotope.js"></script>
        <script src="assets/js/video.js"></script>
        <script src="assets/js/slick-slider.js"></script>
        <script src="assets/js/custom.js"></script>
        <script>
            function markAsDone(userId, lessonId) {
                var url = "markAsDone?userId=" + encodeURIComponent(userId)+"&lessonId="+encodeURIComponent(lessonId);
                console.log(url);
                var xmlHttp = new XMLHttpRequest();
                xmlHttp.open("POST", url, true);
                xmlHttp.onreadystatechange = function () {
                    if (xmlHttp.readyState === 4 && xmlHttp.status === 200) {
                        var StatusButton = document.getElementById("markAsDone");
                        StatusButton.classList.remove("markAsDone");
                        StatusButton.classList.add("Done");
                        StatusButton.innerHTML = "<h4>Done</h4>";
                    }
                };
                xmlHttp.send();
            }


            var showSection = function showSection(section, isAnimate) {
                var
                        direction = section.replace(/#/, ''),
                        reqSection = $('.section').filter('[data-section="' + direction + '"]'),
                        reqSectionPos = reqSection.offset().top - 0;

                if (isAnimate) {
                    $('body, html').animate({
                        scrollTop: reqSectionPos},
                            800);
                } else {
                    $('body, html').scrollTop(reqSectionPos);
                }

            };

            var checkSection = function checkSection() {
                $('.section').each(function () {
                    var
                            $this = $(this),
                            topEdge = $this.offset().top - 80,
                            bottomEdge = topEdge + $this.height(),
                            wScroll = $(window).scrollTop();
                    if (topEdge < wScroll && bottomEdge > wScroll) {
                        var
                                currentId = $this.data('section'),
                                reqLink = $('a').filter('[href*=\\#' + currentId + ']');
                        reqLink.closest('li').addClass('active').
                                siblings().removeClass('active');
                    }
                });
            };

            $('.main-menu, .responsive-menu, .scroll-to-section').on('click', 'a', function (e) {
                e.preventDefault();
                showSection($(this).attr('href'), true);
            });

            $(window).scroll(function () {
                checkSection();
            });
        </script>
    </body>


</body>

</html>

