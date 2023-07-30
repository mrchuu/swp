<%-- 
    Document   : CourseDetails
    Created on : May 16, 2023, 6:34:53 PM
    Author     : Phan Nguyen Tu Anh
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="entity.Course" %>
<%@ page import="java.util.Locale" %>
<%@ page import="java.text.NumberFormat" %>

<!DOCTYPE html>
<html lang="en">

    <head>

        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <meta name="description" content="">
        <meta name="author" content="Template Mo">
        <link href="https://fonts.googleapis.com/css?family=Poppins:100,200,300,400,500,600,700,800,900" rel="stylesheet">

        <title>Education Template - Meeting Detail Page</title>

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
            .publisher{
                border: 2px solid #dbdbda;
                display: flex;
                align-items: center;
                padding: 10px;
                border-radius: 15px;
                background-color: #f0f2e5;
                margin-top: 5px
            }
            .publisher img{
                width: 60px;
                height: 60px;
                border-radius: 50px;
                margin-right: 20px
            }
            @media only screen and (max-width: 600px) {
                .publisher{
                    margin-top: 20px
                }
            }
        </style>
        <!--
        
        TemplateMo 569 Edu Meeting
        
        https://templatemo.com/tm-569-edu-meeting
        
        -->
    </head>

    <body>
        <!-- Sub Header -->
        <!-- ***** Header Area Start ***** -->
        <jsp:include page="header.jsp"/>
        <!-- ***** Header Area End ***** -->

        <section class="heading-page header-text" id="top">
            <div class="container">
                <div class="row">
                    <div class="col-lg-12">
                        <!--          <h6>Get all details</h6>-->
                        <h2>Chi tiết khóa học</h2>
                    </div>
                </div>
            </div>
        </section>

        <section class="meetings-page" id="meetings">
            <div class="topnav">

            </div>

            <div class="container">
                <div class="row">
                    <div class="col-lg-12">
                        <div class="row">
                            <div class="col-lg-12">

                                <div class="meeting-single-item">
                                    <div class="thumb">
                                        <div class="price">
                                            <%
                                                Course course = (Course)request.getAttribute("course");
                                                float price = course.getCourse_price();
                                                Locale locale = new Locale("vi", "VN");
                                                NumberFormat format = NumberFormat.getCurrencyInstance(locale);
                                                String formattedPrice = format.format(price);
                                            %>

                                            <span><%=formattedPrice%></span>

                                        </div>
                                        <div class="date">
                                            <h6>Nov <span>12</span></h6>
                                        </div>
                                        <a href="meeting-details.html"><img style="border: 1px solid #dbdbda" src="${requestScope.course.getCourse_img()}" alt=""></a>
                                    </div>
                                    <div class="down-content">
                                        <div class="row" style="display: flex; align-items: start">
                                            <div class="col-lg-9 col-sm-12 left">
                                                <a href="meeting-details.html"><h4>${requestScope.course.getCourse_name()}</h4></a>
                                                <p>${requestScope.course.getCourseTilte()}</p>
                                            </div>
                                            <div class="col-lg-3 col-sm-12 right">
                                                <h6>Xuất bản bởi: &nbsp;</h6>
                                                <a href="PersonalAccountServlet?viewerId=${requestScope.viewerId}&ProfileId=${requestScope.publisher.getUserId()}">
                                                    <div class="publisher">
                                                        <img src="${requestScope.publisher.getUserImg()}" alt="alt"/>
                                                        <p>${requestScope.publisher.getFullName()}<br>${requestScope.publisher.getUserEmail()}</p>
                                                    </div>
                                                </a>
                                            </div>
                                        </div>
                                        <p class="description">
                                            ${requestScope.course.getCourse_desc()}
                                        </p>

                                        <div class="row">
                                            <div class="col-lg-12">
                                                <div class="share">
                                                    <a href="/SWP/courseList">Back to course list</a>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="col-lg-12">
                                <div class="main-button-red">
                                    <h1>${requestScope.registerd}</h1>
                                    <c:if test="${requestScope.currUser == null || requestScope.currUser.getRoleId() == 2|| requestScope.currUser.getRoleId() == 3}">
                                        <diV  onclick="checkActive(${requestScope.course.getCourse_status()})">
                                            <c:choose>
                                                <c:when test="${requestScope.registerd == true && requestScope.currUser.getRoleId()==2}">
                                                    <a href="LessonListController?Course_id=${requestScope.course.getCourse_id()}"> Bạn đã đăng kí khoá học này, học ngay </a>
                                                </c:when>
                                                <c:when test="${requestScope.publisher.getUserId() == requestScope.currUser.getUserId()}">
                                                    <a href="LessonListController?Course_id=${requestScope.course.getCourse_id()}"> Chỉnh sửa khoá học </a>
                                                </c:when>
                                                <c:otherwise>
                                                    <a href="CourseRegister?course_id=${requestScope.course.getCourse_id()}"> ĐĂNG KÍ NGAY </a>
                                                </c:otherwise>
                                            </c:choose>
                                        </diV>
                                    </c:if>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="footer">
                <p>Copyright © 2022 Edu Meeting Co., Ltd. All Rights Reserved. 
                    <br>Design: <a href="https://templatemo.com" target="_parent" title="free css templates">TemplateMo</a></p>
            </div>
        </section>


        <!-- Scripts -->
        <!-- Bootstrap core JavaScript -->
        <script src="vendor/jquery/jquery.min.js"></script>
        <script src="vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

        <script src="assets/js/isotope.min.js"></script>
        <script src="assets/js/owl-carousel.js"></script>
        <script src="assets/js/lightbox.js"></script>
        <script src="assets/js/tabs.js"></script>
        <script src="assets/js/video.js"></script>
        <script src="assets/js/slick-slider.js"></script>
        <script src="assets/js/custom.js"></script>
        <script>
                                            //according to loftblog tut
                                            $('.nav li:first').addClass('active');


                                            function checkActive(status) {

                                                if (!status) {
                                                    event.preventDefault();
                                                    alert("Khoá học hiện đang bị vô hiệu hoá bởi giảng viên, liên lạc với giảng viên để biết thêm chi tiết");
                                                }
                                                return false;
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
