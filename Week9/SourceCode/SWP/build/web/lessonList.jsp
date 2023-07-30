<%-- 
    Document   : quizzLesson
    Created on : May 28, 2023, 3:47:02 PM
    Author     : admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="entity.Course" %>
<%@ page import="entity.Lesson" %>
<%@ page import="entity.Section" %>
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
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css" integrity="sha512-iecdLmaskl7CVkqkXNQ/ZH/XLlvWZOJyj7Yy7tcenmpD1ypASozpmT/E0iPtmFIB46ZmdtAc9eNBvH0H/ZpiBw==" crossorigin="anonymous" referrerpolicy="no-referrer" />
        <title> Lesson List </title>

        <!-- Bootstrap core CSS -->
        <link href="vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">

        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
        <!-- Additional CSS Files -->
        <link rel="stylesheet" href="assets/css/fontawesome.css">
        <link rel="stylesheet" href="assets/css/templatemo-edu-meeting.css">
        <link rel="stylesheet" href="assets/css/owl.css">
        <link rel="stylesheet" href="assets/css/lightbox.css">
        <link rel="stylesheet" href="assets/css/styling.css?version=20"/>
        <!--
        
        TemplateMo 569 Edu Meeting
        
        https://templatemo.com/tm-569-edu-meeting
        
        -->
        <style>
            hr{
                color: white !important;
                margin-top: 20px !important;
            }
        </style>
    </head>

    <body>
        <jsp:include page="header.jsp"/>

        <section class="heading-page header-text">
            <div class="container">
                <div class="row">
                    <h4 style="color: white; font-size: 250%; margin-bottom: 30px">${requestScope.courseName}</h4>
                </div>


                <div class="row">
                    <div class="CourseContent">
                        <c:forEach items="${SectionList}" var="s">
                            <div class="sectionHeader container-fluid" style="color:white">
                                <i class="fa fa-flag fa-2x"></i><h4>.   ${s.getSection_name()}</h4>
                                <h4 style="display: block; margin: 20px 0 0 63px">Video Lessons</h4>
                                <c:forEach items="${s.getLessonList()}" var="l">
                                    <c:if test="${l.isLesson_status() == true}">
                                        <div class="LessonDesc row">
                                            <div class="left col-10 les${l.getLesson_id()}">
                                                <a href="LessonDetail?lId=${l.getLesson_id()}" style="font-size: 150%">${l.getLesson_name()}</a>
                                                <button class="btnDes" onclick="toggleDisplay('des${l.getLesson_id()}')">
                                                    <i class="fa fa-caret-down fa-2x" style="color: white" aria-hidden="true"></i>
                                                </button>
                                                <p id="des${l.getLesson_id()}" style="color:white; display: none">${l.getLesson_desc()}</p>
                                            </div>
                                            <div class="lessonListEditright col-2">
                                                <c:if test="${currUser.getRoleId() == 3 || currUser.getRoleId() == 1}">
                                                    <button class="DeactivateButton" style="display: inline-block" onclick="SetLessonStatus(0, ${l.getLesson_id()}, ${Course_id})">
                                                        <i class="fa-solid fa-ban fa-2x" style="margin-right: 10px"></i>
                                                    </button>
                                                    <p style="color: white; display: inline-block">/&emsp;</p>
                                                    <a href="EditLessonContent?lesson_id=${l.getLesson_id()}&lesson_Name=${l.getLesson_name()}&lesson_Video=${l.getLesson_video()}&Lesson_desc=${l.getLesson_desc()}">
                                                        <i class="fas fa-edit fa-2x" style="color:#31c8ff"></i>
                                                    </a>
                                                </c:if>
                                            </div>
                                            <hr>
                                        </div>

                                    </c:if>

                                </c:forEach>
                                <c:if test="${currUser.getRoleId() == 3 || currUser.getRoleId() == 1}">
                                    <div class="addnewToSection">
                                        <a href="addnewLessonTosection?section_id=${s.getSection_id()}&course_Id=${Course_id}">
                                            <h4> <i class="fa-solid fa-plus"></i> Add new lesson to this section</h4>

                                        </a>
                                    </div>
                                </c:if>
                                <h4 style="display: block; margin: 20px 0 0 63px">Practice Quizzes</h4>
                                <c:forEach items="${s.getQuizList()}" var="q">
                                    <c:if test="${q.isQuiz_status() == true}">
                                        <div class="LessonDesc row">
                                            <c:if test="${q.isQuiz_status() == true}">
                                                <div class="left col-10">
                                                    <a  href="QuizLesson?quiz_id=${q.getQuiz_id()}" style="font-size: 150%" onclick="CheckSubscription('${requestScope.currSubscription}', '${requestScope.currUser.getRoleId()}', '${q.getQuiz_id()}')">${q.getQuiz_name()}</a>
                                                    <p id="quiz${q.getQuiz_id()}" style="color:white">${q.getQuiz_desc()}</p>
                                                </div>
                                                <div class="lessonListEditright col-2">
                                                    <c:if test="${currUser.getRoleId() == 3 || currUser.getRoleId() == 1}">
                                                        <button class="DeactivateButton" style="display: inline-block" onclick="SetQuizStatus(0, ${q.getQuiz_id()}, ${Course_id})">
                                                            <i class="fa-solid fa-ban fa-2x" style="margin-right: 10px"></i>
                                                        </button>
                                                        <p style="color: white; display: inline-block">/&emsp;</p>
                                                        <a href="EditQuizContent?quiz_id=${q.getQuiz_id()}&quiz_name=${q.getQuiz_name()}">
                                                            <i class="fas fa-edit fa-2x" style="color:#31c8ff"></i>
                                                        </a>
                                                    </c:if>
                                                </div>

                                            </c:if>
                                            <hr>
                                        </div>

                                    </c:if>

                                </c:forEach>
                                <c:if test="${currUser.getRoleId() == 3 || currUser.getRoleId() == 1}">
                                    <div class="addnewToSection">
                                        <a href="addnewQuizTosection?section_id=${s.getSection_id()}">
                                            <h4> <i class="fa-solid fa-plus"></i> Add new quizzes to this section</h4>

                                        </a>
                                    </div>
                                </c:if>
                            </div>
                            <c:if test="${currUser.getRoleId() == 3 || currUser.getRoleId() == 1}">
                                <div class="disabledLesson" style="margin-top: 30px">
                                    <div class="disabledLessonheader" style="color: white">
                                        <i class="fa-solid fa-trash-can fa-shake fa-2x"></i></i><h4>Disabled lessons</h4>
                                    </div>

                                    <c:forEach items="${s.getLessonList()}" var="l">
                                        <c:if test="${l.isLesson_status() == false}">
                                            <div class="LessonDesc row">
                                                <div class="left col-10 les${l.getLesson_id()}">
                                                    <a href="LessonDetail?lId=${l.getLesson_id()}" style="font-size: 150%">${l.getLesson_name()}</a>
                                                    <button class="btnDes" onclick="toggleDisplay('des${l.getLesson_id()}')">
                                                        <i class="fa fa-caret-down fa-2x" style="color: white" aria-hidden="true"></i>
                                                    </button>
                                                    <p id="des${l.getLesson_id()}" style="color:white; display: none">${l.getLesson_desc()}</p>
                                                </div>
                                                <div class="lessonListEditright col-2">
                                                    <c:if test="${currUser.getRoleId() == 3 || currUser.getRoleId() == 1}">
                                                        <button class="activateButton" style="display: inline-block" onclick="SetLessonStatus(1, ${l.getLesson_id()}, ${Course_id})">
                                                            <i class="fa-solid fa-check fa-2x" style="margin-right: 10px"></i>
                                                        </button>
                                                        <p style="color: white; display: inline-block">/&emsp;</p>
                                                        <a href="EditLessonContent?lesson_id=${l.getLesson_id()}&lesson_Name=${l.getLesson_name()}&lesson_Video=${l.getLesson_video()}&Lesson_desc=${l.getLesson_desc()}">
                                                            <i class="fas fa-edit fa-2x" style="color:#31c8ff"></i>
                                                        </a>
                                                    </c:if>
                                                </div>
                                                <hr>
                                            </div>

                                        </c:if>


                                    </c:forEach>

                                </div>
                                <div class="disabledLesson">
                                    <div class="disabledLessonheader" style="color: white">
                                        <i class="fa-solid fa-trash-can fa-shake fa-2x"></i></i><h4>Disabled quizzes</h4>
                                    </div>

                                    <c:forEach items="${s.getQuizList()}" var="q">

                                        <c:if test="${q.isQuiz_status() == false}">
                                            <div class="LessonDesc row">
                                                <div class="left col-10">
                                                    <a href="QuizLesson?quiz_id=${q.getQuiz_id()}" style="font-size: 150%">${q.getQuiz_name()}</a>
                                                    <p id="quiz${q.getQuiz_id()}" style="color:white">${q.getQuiz_desc()}</p>
                                                </div>
                                                <div class="lessonListEditright col-2">
                                                    <c:if test="${currUser.getRoleId() == 3 || currUser.getRoleId() == 1}">
                                                        <button class="activateButton" style="display: inline-block" onclick="SetQuizStatus(1, ${q.getQuiz_id()}, ${Course_id})">
                                                            <i class="fa-solid fa-check fa-2x" style="margin-right: 10px"></i>
                                                        </button>
                                                        <p style="color: white; display: inline-block">/&emsp;</p>
                                                        <a href="EditQuizContent?quiz_id=${q.getQuiz_id()}&quiz_name=${q.getQuiz_name()}"><i class="fas fa-edit fa-2x" style="color:#31c8ff"></i></a>

                                                    </c:if>
                                                </div>
                                                <hr>
                                            </div>
                                        </c:if>


                                    </c:forEach>

                                </div>
                            </c:if>


                        </c:forEach>
                        <c:if test="${currUser.getRoleId() == 3 || currUser.getRoleId() == 1}">
                            <a id="addnewsecBtn" href="AddnewSection?CourseId=${Course_id}"><h4 style="color: white; text-align: left; padding: 10px; border: white solid 1px"><i class="fa fa-plus"></i> Add new Section to this course</h4></a>
                        </c:if>

                    </div>
                </div>
            </div>
        </section>
        <div class="modal" id="myModal">
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
                    <div class="modal-footer">
                        <button type="button" class="btn btn-danger" data-bs-dismiss="modal">Close</button>
                    </div>

                </div>
            </div>
        </div>
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
                                                                if (sortType.value === "recent") {
                                                                    window.location.href = "?sort_type=recent";
                                                                } else if (sortType.value === "name") {
                                                                    window.location.href = "?sort_type=name";
                                                                }
                                                            });

                                                            var paramValue = "${sessionScope.sort_type}";
                                                            for (var i = 0; i < sortType.options.length; i++) {
                                                                if (sortType.options[i].value === paramValue) {
                                                                    sortType.options[i].selected = true;
                                                                    break;
                                                                }
                                                            }

                                                            function SetLessonStatus(updateStatus, lessonId, courseId) {
                                                                //Send an AJAX request to your server-side script

                                                                $.ajax({
                                                                    url: "UpdateLessonStatus",
                                                                    type: "GET",
                                                                    data: {lessonId: lessonId,
                                                                        updateStatus: updateStatus
                                                                    },

                                                                    success: function (response) {
                                                                        console.log("success");
                                                                        window.location.href = "LessonListController?Course_id=" + courseId;
                                                                    }
                                                                });
                                                            }
                                                            function CheckSubscription(subscription, role, quizId) {
                                                                event.preventDefault();
                                                                if (subscription == "" && role == 2) {
                                                                    $('#myModal').modal('show');
                                                                } else {
                                                                    window.location.href = "QuizLesson?quiz_id=" + quizId
                                                                }
                                                            }
                                                            function SetQuizStatus(updateStatus, QuizId, courseId) {
                                                                //Send an AJAX request to your server-side script
                                                                $.ajax({
                                                                    url: "UpdateQuizStatus",
                                                                    type: "POST",
                                                                    data: {Quizid: QuizId,
                                                                        updateStatus: updateStatus
                                                                    },

                                                                    success: function (response) {
                                                                        console.log("success");
                                                                        window.location.href = "LessonListController?Course_id=" + courseId;
                                                                    }
                                                                });
                                                            }
                                                            function toggleDisplay(DesId) {
                                                                var currentDisplay = document.getElementById(DesId);
                                                                currentDisplay.style.display = currentDisplay.style.display != "none" ? "none" : "block";
                                                                console.log(DesId);
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

