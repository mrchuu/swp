<%-- 
    Document   : PersonalAccount
    Created on : May 18, 2023, 6:37:48 PM
    Author     : FPT
--%>
<%@page import="entity.User" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

    <head>

        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <meta name="description" content="">
        <meta name="author" content="Template Mo">
        <link href="https://fonts.googleapis.com/css?family=Poppins:100,200,300,400,500,600,700,800,900;subset=cyrillic,cyrillic-ext,greek,greek-ext,latin-ext,vietnamese" rel="stylesheet">
        <title> Personal Info </title>

        <!-- Bootstrap core CSS -->
        <link href="vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">


        <!-- Additional CSS Files -->
        <link rel="stylesheet" href="assets/css/fontawesome.css">
        <link rel="stylesheet" href="assets/css/templatemo-edu-meeting.css?version=12">
        <link rel="stylesheet" href="assets/css/owl.css?version=10">
        <link rel="stylesheet" href="assets/css/lightbox.css?version=10">
        <link rel="stylesheet" href="assets/css/styling.css?version=97">
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
                <div class="row">
                    <div class="col-lg-3 offset-1">
                        <img class="rounded-circle shadow-4-strong" src="${profile.getUserImg()}" alt="alt"/>
                        <p style="color: white; margin-top: 15px">"Lorem ipsum dolor sit amet, consectetur adipiscing elit."</p>
                    </div>
                    <div class="col-lg-8 Per">
                        <h2 style="font-size: 150%; display: inline-block; position: relative; left: -85px">Thông tin cá nhân</h2> 
                        <c:if test="${requestScope.viewOwn == true}">
                            <div class="EditProfileButton">
                                <a href="${pageContext.request.contextPath}/editProfile?uid=${profile.getUserId()}">Chỉnh sửa hồ sơ</a>
                            </div>
                        </c:if>

                        <div class="PersonalInfo">
                            <div style="color: white;" class="PersonalInfo-left">                 
                                <h4>Tên người dùng:</h4>
                                <h4>Giới tính:</h4>
                                <h4>Ngày sinh:</h4>
                                <h4>Số điện thoại:</h4>
                                <h4>Địa chỉ: </h4>
                                <c:if test="${requestScope.viewOwn == true}">
                                    <h4>Ví: </h4>
                                    <h4>Subscription: </h4>
                                </c:if>
                                <c:if test="${profile.getRoleId()==2}">
                                    <h4>Điểm tích luỹ: </h4>
                                </c:if>
                                <h4>Ngày tham gia: </h4>
                            </div>
                            <div style="color: white;" class="PersonalInfo-right">
                                <h4>${profile.getFullName()}(${role.getRoleName()})</h4>
                                <h4>${gender.getGenderName()}</h4>
                                <h4>${profile.getDobFormated()}</h4>
                                <h4>${profile.getUserPhone()}</h4>
                                <h4>${profile.getUserAddress()}</h4>
                                <c:if test="${requestScope.viewOwn == true}">
                                    <h4>${profile.getUserWalletFormatted()} &nbsp; <a href="Deposit" class="fa fa-plus-square"></a></h4>
                                    <c:if test="${requestScope.currSubscription != null}">
                                        <a href="PricePackageSubcription">${requestScope.currSubscription.getCurrentPackage().getPackage_name()}</a>
                                    </c:if>
                                        <c:if test="${requestScope.currSubscription == null}">
                                        <a href="PricePackageSubcription">Miễn phí</a>
                                    </c:if>
                                </c:if>
                                <c:if test="${profile.getRoleId()==2}">
                                    <h4>${profile.getScore()}</h4>
                                </c:if>
                                <h4>${profile.getUserTimeFormated()}</h4>
                            </div>
                        </div>
                    </div>
                </div>
                <hr>
                <c:if test="${profile.getRoleId() == 2 || profile.getRoleId() == 3}">
                    <div class="row myCourse">
                        <div class="col-lg-10 offset-1">
                            <h4 style="color: white; font-size: 200%; text-align: left">Khoá học của tôi</h4> 
                        </div>
                        <div class="cucourseList col-lg-10 offset-1">
                            <ul class="dropdown">
                                <c:if test="${profileCourses.size() == 0}">
                                    <div class="emptyCourse">
                                        <img src="img/My project.png" alt="alt"/>
                                        <h4 style="color: white">bạn chưa tham gia khoá học nào cả, nhấn vào <a href="courseList">đây</a> để tìm kiếm khoá học phù hợp nhé</h4>

                                    </div>
                                </c:if>
                                <c:forEach items="${profileCourses}" var="cuc">
                                    <li>
                                        <a href="courseDetails?course_id=${cuc.getUserCourse().getCourse_id()}">
                                            <div class="CourseCell">
                                                <img src="${cuc.getUserCourse().getCourse_img()}" alt="alt"/>
                                                <div class="CourseCell_Info">
                                                    <h4>${cuc.getUserCourse().getCourse_name()}</h4>
                                                    <p>${cuc.getUserCourse().getCourse_desc()}</p>
                                                    <c:if test="${requestScope.profile.getRoleId()==2}">
                                                        <p class="courseDate"><i class="fa fa-calendar"></i> &nbsp ${cuc.getStartDate()} &nbsp&nbsp To &nbsp&nbsp ${cuc.getEndDate()}</p>
                                                    </c:if>
                                                    <div class="CourseStatus">
                                                        <c:if test="${requestScope.viewOwn == true}">

                                                            <c:if test="${profile.getRoleId() == 2}">
                                                                <c:if test="${cuc.isDone() == true}">
                                                                    <p  style="color: #41c86a; margin-top: 15px"><i class="fa fa-graduation-cap" style="color: #41c86a;"></i>&nbsp&nbspFinished</p>
                                                                </c:if>
                                                                <c:if test="${cuc.isDone() == false}">
                                                                    <p style="color: #ccd656; margin-top: 15px"><i class="fa fa-graduation-cap" style="color: #ccd656;"></i>&nbsp&nbspUnfinished</p> 
                                                                </c:if>
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
                </c:if>
                <c:if test="${requestScope.viewOwn == true}">
                    <div class="row LogOutButton">
                        <a href="logout">Log Out</a>
                    </div>
                </c:if>

            </div>
        </section>

        <section class="meetings-page" id="meetings" style="padding-top: 0">

            <div class="footer" style="margin-top: 0">
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
        <script>
            //according to loftblog tut
            $('.nav li:first').addClass('active');

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