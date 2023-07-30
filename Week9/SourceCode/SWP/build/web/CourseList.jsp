<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="entity.Course" %>
<%@ page import="java.util.Locale" %>
<%@ page import="java.text.NumberFormat" %>
<%-- 
    Document   : CourseList
    Created on : May 16, 2023, 10:21:25 AM
    Author     : Phan Nguyen Tu Anh
--%>


<!DOCTYPE html>
<html lang="en">

    <head>

        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <meta name="description" content="">
        <meta name="author" content="Template Mo">
        <link href="https://fonts.googleapis.com/css?family=Poppins:100,200,300,400,500,600,700,800,900" rel="stylesheet">
        <title> CourseList </title>

        <!-- Bootstrap core CSS -->
        <link href="vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">


        <!-- Additional CSS Files -->
        <link rel="stylesheet" href="assets/css/fontawesome.css">
        <link rel="stylesheet" href="assets/css/templatemo-edu-meeting.css">
        <link rel="stylesheet" href="assets/css/owl.css">
        <link rel="stylesheet" href="assets/css/lightbox.css">
        <link rel="stylesheet" href="assets/css/styling.css?version=10">
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
            .down-content{
                min-height: 214px
            }
            .thumb img{
                height: 300px; 
                border-bottom: 1px solid black
            }
           
        </style>
        <!--
        
        TemplateMo 569 Edu Meeting
        
        https://templatemo.com/tm-569-edu-meeting
        
        -->
    </head>
    <p id="result" style="color:white"></p>
    <body>
        <!-- ***** Header Area Start ***** -->
        <jsp:include page="header.jsp"/>
        <!-- ***** Header Area End ***** -->

        <section class="heading-page header-text" id="top">
            <div class="container">
                <div class="row">
                    <div class="col-lg-12">
                        <h6>THÔNG TIN KHÓA HỌC</h6>
                        <h6 style="color: white; font-size: 250%; font-weight: bold">Các khóa học online hiệu quả</h6>
                    </div>
                </div>
            </div>
        </section>

        <section class="meetings-page" id="meetings" style="padding-top: 50px">
            <div class="topnav">
                <div class="search-container">
                    <form action="#">
                        <input type="text" placeholder="Search.." name="search">
                        <button type="submit"><i class="fa fa-search"></i></button>
                    </form>
                </div>
            </div>
            <div class="container">
                <div class="row">
                    <div class="col-lg-12">
                        <div class="row">
                            <div class="col-lg-12">
                                <div class="filters">
                                    <form>
                                        <ul>
                                            <a href="?sub_id=0"><li>All</li></a>
                                                    <c:forEach items="${requestScope.subjectList}" var="subject">
<!--                                                <input type="button" onclick="filterBySubject(${subject.getSub_id()})" value="${subject.getSub_name()}"/>-->
                                                <a href="courseList?sub_id=${subject.getSub_id()}" class="filter-link"><li>${subject.getSub_name()}</li></a>
                                                    </c:forEach>
                                        </ul>
                                    </form>
                                </div>
                            </div>
                            <div style="color: white;margin-bottom: 50px" class="col-lg-12">
                                Sort By <select id="sortType">
                                    <option value="recent">Recent days</option>
                                    <option value="name">Name</option>
                                    <option value="price">Price</option>
                                    <option value="Mostparticipant">Most participant</option>
                                </select>
                            </div>

                            <div class="col-lg-12">
                                <div class="row grid">
                                    <c:if test="${requestScope.courseToDisplay.size() == 0}">
                                        <div class="emptyCourse">
                                            <img src="img/My project.png" alt="alt"/>
                                            <h4 style="color: white">bạn chưa tham gia khoá học nào cả, nhấn vào <a href="courseList">đây</a> để tìm kiếm khoá học phù hợp nhé</h4>

                                        </div>
                                    </c:if>
                                    <c:forEach items="${requestScope.courseToDisplay}" var="course">
                                        <div class="col-lg-4 templatemo-item-col all soon">
                                            <div class="meeting-item">
                                                <div class="thumb">
                                                    <div class="price">
                                                        <c:set var="price" value="${course.getCourse_price()}"/>
                                                        <fmt:setLocale value="vi_VN"/>
                                                        <span><fmt:formatNumber type="currency" value="${price}" currencySymbol="đ"/></span>
                                                    </div>
                                                    <a href="/SWP/courseDetails?course_id=${course.getCourse_id()}"><img src="${course.getCourse_img()}" alt=""></a>
                                                </div>
                                                <div class="down-content">
                                                    <div class="date">
                                                        <h6>${course.getlastUpdatedFormated()}</h6>
                                                    </div>
                                                    <br>
                                                    <div>
                                                        <a href="/SWP/courseDetails?course_id=${course.getCourse_id()}"><h4 style="font-size: 100%">${course.getCourse_name()}</h4></a><br>
                                                        <p>${course.getCourseTilte()}</p>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </c:forEach>   
                                </div>
                            </div>
                            <div class="col-lg-12">

                                <c:if test="${totalPages > 1}">
                                    <div class="pagination">
                                        <ul>
                                            <c:choose>
                                                <c:when test="${currentPage > 1}">
                                                    <li><a href="?currentPage=${currentPage - 1}"><i class="fa fa-angle-left"></i></a></li>
                                                        </c:when>
                                                        <c:otherwise>
                                                    <span class="disabled">Previous</span>
                                                </c:otherwise>
                                            </c:choose>

                                            <c:forEach var="i" begin="1" end="${totalPages}">
                                                <c:choose>
                                                    <c:when test="${currentPage == i}">
                                                        <li class="active"><a href="current">${i}</a></li>
                                                        </c:when>
                                                        <c:otherwise>
                                                        <li><a href="?currentPage=${i}">${i}</a></li>
                                                        </c:otherwise>
                                                    </c:choose>
                                                </c:forEach>

                                            <c:choose>
                                                <c:when test="${currentPage < totalPages}">
                                                    <li><a href="?currentPage=${currentPage + 1}"><i class="fa fa-angle-right"></i></a></li>
                                                        </c:when>
                                                        <c:otherwise>
                                                    <span class="disabled">Next</span>
                                                </c:otherwise>
                                            </c:choose>
                                        </ul>
                                    </div>
                                </c:if>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="footer">
                <p>Copyright © 2022 Edu Meeting Co., Ltd. All Rights Reserved. 
                    <br>Design: <a href="https://templatemo.com/page/1" target="_parent" title="website templates">TemplateMo</a></p>
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
        <script src="assets/js/isotope.js"></script>
        <script src="assets/js/video.js"></script>
        <script src="assets/js/slick-slider.js"></script>
        <script src="assets/js/custom.js"></script>
        <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
        <script>
            //according to loftblog tut
            $('.nav li:first').addClass('active');

            // set sort_type
            const sortType = document.getElementById("sortType");
            sortType.addEventListener("change", function () {
                if (sortType.value === "recent") {
                    window.location.href = "?sort_type=recent";
                } else if (sortType.value === "name") {
                    window.location.href = "?sort_type=name";
                } else if (sortType.value === "Mostparticipant") {
                    window.location.href = "?sort_type=Mostparticipant";
                }
            });

            var paramValue = "${sessionScope.sort_type}";
            for (var i = 0; i < sortType.options.length; i++) {
                if (sortType.options[i].value === paramValue) {
                    sortType.options[i].selected = true;
                    break;
                }
            }

            function filterBySubject(sub_id) {
                console.log(sub_id);
                var url = "CourseListFilter?sub_id=" + encodeURIComponent(sub_id);
                var xmlHttp = new XMLHttpRequest();
                xmlHttp.open("GET", url, true);

                xmlHttp.onreadystatechange = function () {
                    if (xmlHttp.readyState === 4 && xmlHttp.status === 200) {
                        var result = document.getElementById("result");
                        result.innerHTML = xmlHttp.responseText;
//                                                                var responseObj = JSON.parse(xmlHttp.responseText);
//                                                                var courseToDisplay = responseObj.jsonDatacourseToDisplay;
//                                                                var totalPage = responseObj.jsonTotalPage;
//                                                                var currentPage = responseObj.jsonCurrentPage;
//                                                                console.log("courseToDisplay: " + jsonDatacourseToDisplay);
//                                                                console.log("totalPage: " + jsonTotalPage);
//                                                                console.log("currentPage: " + jsonCurrentPage);
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
