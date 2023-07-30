<%-- 
    Document   : login
    Created on : May 16, 2023, 6:00:20 PM
    Author     : admin
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
    <head>

        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <meta name="description" content="">
        <meta name="author" content="Template Mo">
        <link href="https://fonts.googleapis.com/css?family=Poppins:100,200,300,400,500,600,700,800,900;subset=cyrillic,cyrillic-ext,greek,greek-ext,latin-ext,vietnamese" rel="stylesheet">
        <title> Deposit </title>

        <!-- Bootstrap core CSS -->
        <link href="vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">


        <!-- Additional CSS Files -->
        <link rel="stylesheet" href="assets/css/fontawesome.css">
        <link rel="stylesheet" href="assets/css/templatemo-edu-meeting.css?version=12">
        <link rel="stylesheet" href="assets/css/owl.css?version=10">
        <link rel="stylesheet" href="assets/css/lightbox.css?version=10">
        <link rel="stylesheet" href="assets/css/styling.css?version=27">
        <!--
        
        TemplateMo 569 Edu Meeting
        
        https://templatemo.com/tm-569-edu-meeting
        
        -->
    </head>

    <body>



        <jsp:include page="header.jsp"/>
        <!-- ***** Header Area End ***** -->

        <section style="padding-top: 300px" class="contact-us" id="contact">
            <div class="container">
                <div class="row">
                    <div class="col-lg-12 align-self-center">
                        <div class="row align-content-center justify-content-center">
                            <div class="col-lg-6">
                                <form id="contact" action="Deposit" method="post">
                                    <div class="row">
                                        <div class="col-lg-12">
                                            <h2 class="text-center" >Nạp thêm tiền</h2>
                                        </div>
                                        <div id="MoneyFormatErr" style="display: none">
                                            <div class="col-lg-12">
                                                <div class="alert alert-danger">
                                                    <strong>Số tiền không hợp lệ</strong> ${err}
                                                </div>
                                            </div>
                                        </div>
                                        <c:if test="${requestScope.success != null}">
                                            <div class="col-lg-12">
                                                <div class="col-lg-12">
                                                    <div class="alert alert-success">
                                                        <strong>RESET THÀNH CÔNG</strong> 
                                                    </div>
                                                </div>
                                            </div>
                                        </c:if>


                                        <div class="col-lg-12">
                                            <fieldset>
                                                <input name="depoAmount" type="text" id="depoAmount" onchange="MULTIPLYValue()" placeholder="Số tiền muốn nạp thêm..." required="">
                                            </fieldset>
                                        </div>
                                        <c:if test="${requestScope.passwordErr != null}">
                                            <div class="col-lg-12">
                                                <div class="alert alert-danger">
                                                    <strong>${requestScope.passwordErr}</strong>
                                                </div>
                                            </div>
                                        </c:if>

                                        <div class="col-lg-12">
                                            <fieldset>
                                                <input name="password" type="password" id="password" placeholder="Nhập mật khẩu...." required="">
                                            </fieldset>
                                        </div>

                                        <div class="col-lg-12">
                                            <fieldset>
                                                <button type="submit" id="form-submit" class="button">RESET</button>
                                            </fieldset>
                                        </div>
                                    </div>
                                </form>
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

                                                    function  MULTIPLYValue() {
                                                        var numberPattern = /^\d+(\.\d+)?$/;
                                                        var currentAmount = parseFloat(document.getElementById("depoAmount").value * 1000);
                                                        if (!numberPattern.test(currentAmount)) {
                                                            document.getElementById("depoAmount").value = "";
                                                            document.getElementById("MoneyFormatErr").style.display = document.getElementById("MoneyFormatErr").style.display == "none" ? "block" : "none";
                                                        } else {
                                                            console.log(currentAmount.toLocaleString('vi-VN', {style: 'currency', currency: 'VND'}));
                                                            document.getElementById("depoAmount").value = currentAmount.toLocaleString('vi-VN', {style: 'currency', currency: 'VND'});

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

