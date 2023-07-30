<%-- 
    Document   : PersonalAccount
    Created on : May 18, 2023, 6:37:48 PM
    Author     : FPT
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>



<!DOCTYPE html>
<html lang="en">

    <head>

        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <meta name="description" content="">
        <meta name="author" content="Template Mo">
        <link href="https://fonts.googleapis.com/css?family=Poppins:100,200,300,400,500,600,700,800,900" rel="stylesheet">

        <title> Personal Info </title>

        <!-- Bootstrap core CSS -->
        <link href="vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.15.4/css/all.css" integrity="sha384-DyZ88mC6Up2uqS4h/KRgHuoeGwBcD4Ng9SiP4dIRy0EXTlnuz47vAwmeGwVChigm" crossorigin="anonymous"/>

        <!-- Additional CSS Files -->
        <link rel="stylesheet" href="assets/css/fontawesome.css">
        <link rel="stylesheet" href="assets/css/templatemo-edu-meeting.css?version=12">
        <link rel="stylesheet" href="assets/css/owl.css?version=10">
        <link rel="stylesheet" href="assets/css/lightbox.css?version=10">
        <link rel="stylesheet" href="assets/css/styling.css?version=111">
        <!--
        
        TemplateMo 569 Edu Meeting
        
        https://templatemo.com/tm-569-edu-meeting
        
        -->
        <style>
            .CourseStatus{
                display: flex;
                justify-content: space-around
            }
        </style>
    </head>

    <body>



        <jsp:include page="header.jsp"/>
        <!-- ***** Header Area End ***** -->

        <section class="heading-page header-text">
            <div class="container">
                <div class="row pageT">
                    <div class="col-lg-10 offset-1">
                        <h2>My Courses</h2> 

                    </div>
                </div>
                <div class="row search-container" style="margin-bottom: 30px">
                    <form action="#">
                        <input type="text" placeholder="Search.." name="search">
                        <button type="submit" style="background-color: #d6d6d6; border: none; height: 29px; width: 29px; "><i style="color: #959595" class="fa fa-search"></i></button>
                    </form>
                </div>
                <div class="row">
                    <div class="col-lg-12">
                        <div class="filters">
                            <ul>
                                <c:forEach items="${requestScope.subjectList}" var="subject">
                                    <a href="?sub_id=${subject.getSub_id()}">
                                        <div class="subjectfilter">
                                            <li>${subject.getSub_name()}</li>
                                        </div>
                                    </a>
                                </c:forEach>
                            </ul>
                        </div>
                    </div>
                </div>

                <div class="row">
                    <div style="color: white;margin-bottom: 30px; position: relative; left: -33%;" class="col-lg-12">
                        Sort By 
                        <select id="sortType">
                            <option value="name">Name</option>
                            <option value="recent">Recent days</option>

                        </select>
                    </div>
                </div>

                <div class="row userCourseList">

                    <c:if test="${myCourses.size() == 0}">
                        <div class="emptyCourse">
                            <img src="img/My project.png" alt="alt"/>
                            <h4 style="color: white">bạn chưa tham gia khoá học nào cả, nhấn vào <a href="courseList">đây</a> để tìm kiếm khoá học phù hợp nhé</h4>

                        </div>
                    </c:if>

                    <div class="myCourse">

                        <div class="cucourseList col-lg-10 offset-1">
                            <ul>
                                <c:forEach items="${myCourses}" var="cuc">
                                    <li>
                                        <a href="courseDetails?course_id=${cuc.getUserCourse().getCourse_id()}">
                                            <div class="CourseCell">
                                                <img src="${cuc.getUserCourse().getCourse_img()}" alt="alt"/>
                                                <div class="CourseCell_Info">
                                                    <h4>${cuc.getUserCourse().getCourse_name()}</h4>
                                                    <p>${cuc.getUserCourse().getCourse_desc()}</p>
                                                    <c:if test="${requestScope.currUser.getRoleId()==2}">
                                                        <p class="courseDate"><i class="fa fa-calendar"></i> &nbsp ${cuc.getStartDateFormated()} &nbsp&nbsp To &nbsp&nbsp ${cuc.getEndDateFormated()}</p>
                                                    </c:if>
                                                    <div class="CourseStatus">
                                                        <c:if test="${requestScope.currUser.getRoleId() == 2}">
                                                            <c:if test="${cuc.isDone() == true}">
                                                                <p  style="color: #41c86a; margin-top: 15px"><i class="fa fa-graduation-cap" style="color: #41c86a;"></i>&nbsp&nbspFinished</p>
                                                            </c:if>
                                                            <c:if test="${cuc.isDone() == false}">
                                                                <p style="color: #ccd656; margin-top: 15px"><i class="fa fa-graduation-cap" style="color: #ccd656;"></i>&nbsp&nbspUnfinished</p> 
                                                            </c:if>
                                                        </c:if>

                                                        <c:if test="${cuc.getUserCourse().getCourse_status() == false}">
                                                            <p style="color: #ff8d8d; margin-top: 15px"><i class="fa fa-circle" style="color: #ff8d8d;"></i>&nbsp&nbspInActive</p> 
                                                        </c:if>   
                                                        <c:if test="${cuc.getUserCourse().getCourse_status() == true}">
                                                            <p style="color: #41c86a; margin-top: 15px"><i class="fa fa-circle" style="color: #41c86a;"></i>&nbsp&nbspActive</p> 
                                                        </c:if>   
                                                    </div>
                                                </div>
                                            </div>
                                        </a>
                                    </li>
                                </c:forEach>

                            </ul>
                        </div>
                    </div>

                </div>
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
                                            //according to loftblog tut
                                            $('.nav li:first').addClass('active');

                                            
                                            // set sort_type
                                            const sortType = document.getElementById("sortType");
                                            sortType.addEventListener("change", function () {
                                                if (sortType.value === "name") {
                                                    window.location.href = "?sort_type=name";
                                                } else if (sortType.value === "recent") {
                                                    window.location.href = "?sort_type=recent";

                                                }

                                            });
                                            var paramValue = "${sessionScope.sort_type}";
                                            for (var i = 0; i < sortType.options.length; i++) {
                                                if (sortType.options[i].value === paramValue) {
                                                    sortType.options[i].selected = true;
                                                    break;
                                                }
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