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

        <title> Quiz Lesson List </title>

        <!-- Bootstrap core CSS -->
        <link href="vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>

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
        </style>
        <!--
        
        TemplateMo 569 Edu Meeting
        
        https://templatemo.com/tm-569-edu-meeting
        
        -->
    </head>
    
    <body>


        <jsp:include page="header.jsp"/>
        <!-- ***** Header Area End ***** -->

        <section class="heading-page header-text tQuiz" id="top">
            <div class="container">
                <div class="row">
                    <div class="col-lg-12">
                        <h6>Thông tin quiz</h6>
                        <h2>${requestScope.quiz.getQuiz_name()}</h2>
                    </div>
                </div>
            </div>
        </section>
        <section class="meetings-page" style="padding-top: 0px;" id="meetings" >

            <div class="container p-3" style="color: white;">
                <div class="row">
                    <div class="col-lg-12">
                        <h4>Lịch sử làm Quiz</h4>
                    </div>
                </div>
            </div>


            <div style="color: white;margin-bottom: 50px; margin-left: 115px" class="col-lg-12">

                Sort By <select id="sortType">
                    <option value="recent">Recent</option>
                    <option value="name">Highest Scores</option>
                </select>
            </div>

            <div class="row">
                <h6 style="color: #33CAFD; text-align: center">Làm bài quiz với kết quả >= 5 sẽ cộng thêm 10 điểm tích luỹ ;)</h6>
            </div>
            <div class="row" style="text-align: right">
                <i class="fa-sharp fa-solid fa-ranking-star fa-2x" style="color: white"></i>
            </div>
            <div class="main-button-red" style="margin-bottom: 30px">
                <a href="QuizHandle?quiz_id=${requestScope.quiz_id}"> Bắt đầu làm </a>

            </div>

            <div class="container" >
                <div class="row">
                    <div class="col-lg-12">
                        <table class="table table-dark">
                            <thead>
                                <tr>
                                    <th scope="col">Attempt</th>
                                    <th scope="col">State</th>
                                    <th scope="col">Start</th>
                                    <th scope="col">Grade/10</th>
                                    <th scope="col">Review</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach items="${requestScope.quizResultList}" var="quizResult">
                                    <tr>
                                        <th scope="row">${quizResult.getAttempt()}</th>
                                        <td>
                                            <p style="color: white;">${quizResult.isQuiz_status() ? "Pass" : "Not Pass"}</p>
                                            <fmt:formatDate value="${quizResult.getQuiz_end()}" pattern="HH:mm:ss dd/MM/yyyy" />
                                        </td>
                                        <td>
                                            <fmt:formatDate value="${quizResult.getQuiz_start()}" pattern="HH:mm:ss dd/MM/yyyy" />
                                        </td>
                                        <td>${quizResult.getQuiz_grade()}</td>
                                        <td>
                                            <a href="QuizReview?quiz_result_id=${quizResult.getQuiz_result_id()}">Review</a>
                                        </td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
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
        <script>
            //according to loftblog tut
            $('.nav li:first').addClass('active');
            var currSub = '${requestScope.currSubscription}';
            var currUserRole = '${requestScope.currUser.getRoleId()}';
            document.addEventListener("DOMContentLoaded", function () {
                if (currSub == "" && currUserRole == 2) {
                    var myModal = new bootstrap.Modal(document.getElementById("myModal"));
                    myModal.show();

                }
            });
            // set sort_type
            const
                    sortType = document.getElementById("sortType");
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

            function CheckSubscription(subscription) {

                if (subscription == "") {
                    console.log(subscription)
                    $('#myModal').modal('show');
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

