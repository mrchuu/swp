<%-- 
    Document   : Register
    Created on : May 15, 2023, 1:45:24 PM
    Author     : FPT
--%>
<%@page import="entity.User" %>
<%@page import="entity.Province" %>
<%@page import="entity.District" %>
<%@page import="dao.DistrictDAO" %>
<%@page import="dao.ProvinceDAO" %>
<%@page import="entity.Gender" %>
<%@page import="entity.Role" %>
<%@page import="dao.RoleDAO" %>
<%@page import="dao.UserDAO" %>
<%@page import="dao.GenderDAO" %>
<%@page import="java.util.Vector" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%
    GenderDAO gd = new GenderDAO();
    RoleDAO rd = new RoleDAO();
    ProvinceDAO pd = new ProvinceDAO();
    DistrictDAO dd = new DistrictDAO();
    Vector<Province> provinceList = pd.getAllProvince();
    Vector<Gender> genderList = gd.getAllGender();
    Vector<Role> roleList = rd.getRegisterRoles();
    pageContext.setAttribute("genderList", genderList);
    pageContext.setAttribute("roleList", roleList);
    pageContext.setAttribute("provinceList", provinceList);
    String pname = "";
%>

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
        <!--
        
        TemplateMo 569 Edu Meeting
        
        https://templatemo.com/tm-569-edu-meeting
        
        -->
    </head>

    <body>



         <jsp:include page="header.jsp"/>
        <!-- ***** Header Area End ***** -->

        <section class="heading-page header-text" id="top">
            <div class="container">
                <div class="row">
                    <div class="col-lg-12">
                        <h2>ĐĂNG KÝ TÀI KHOẢN</h2>
                    </div>
                </div>
            </div>
        </section>

        <section class="contact-us" id="contact">
            <div class="container">
                <div class="row">
                    <div class="col-lg-12 align-self-center">
                        <div class="row align-content-center justify-content-center">
                            <div class="col-lg-6">
                                <form id="contact" action="register" method="post" enctype="multipart/form-data">
                                    <div class="row">
                                        <div class="col-lg-12">
                                            <h2 class="text-center" >Đăng ký</h2>
                                        </div>
                                        <c:if test="${duplicateEmailErr != null}">
                                            <div class="col-lg-12">
                                                <div class="alert alert-warning">
                                                    <strong>Warning!</strong> ${duplicateEmailErr}
                                                </div>
                                            </div>
                                        </c:if>
                                        <c:if test="${emailerr != null}">
                                            <div class="col-lg-12">
                                                <div class="alert alert-warning">
                                                    <strong>Warning!</strong> ${emailerr}
                                                </div>
                                            </div>
                                        </c:if>
                                        <div class="col-lg-12">
                                            <fieldset>
                                                <input name="email" type="text" id="email" placeholder="YOUR EMAIL..." required="">
                                            </fieldset>
                                        </div>
                                        <c:if test="${PasswordPatternErr != null}">
                                            <div class="col-lg-12">
                                                <div class="alert alert-warning">
                                                    <strong>Warning!</strong> ${PasswordPatternErr}
                                                </div>
                                            </div>
                                        </c:if>
                                        <div class="col-lg-12">
                                            <fieldset>
                                                <input name="password1" type="password" id="password" placeholder="YOUR PASSWORD..." required="">
                                            </fieldset>
                                        </div>
                                        <c:if test="${passworderr != null}">
                                            <div class="col-lg-12">
                                                <div class="alert alert-warning">
                                                    <strong>Warning!</strong> ${passworderr}
                                                </div>
                                            </div>
                                        </c:if>
                                        <div class="col-lg-12">
                                            <fieldset>
                                                <input name="password2" type="password" id="password" placeholder="RE-ENTER YOUR PASSWORD..." required="">
                                            </fieldset>
                                        </div>
                                        
                                        <div class="col-lg-12">
                                            <fieldset>
                                                <input name="fullname" type="text" id="fullname" placeholder="YOUR FULLNAME..." required="">
                                            </fieldset>
                                        </div>
                                        <div class="col-lg-12" style="margin-bottom: 25px ; margin-left: 10px">
                                            <p style="opacity: 0.7">YOUR PROFILE PICTURE</p>
                                            <input name="userImg" type="file" accept="image/*" id="userImg" style="all:unset"/>
                                        </div>
                                        <div class="col-lg-12" style="margin-bottom: 25px ; margin-left: 10px">
                                            <p style="opacity: 0.7">YOUR GENDER</p>
                                            <select name="gender">
                                                <c:forEach items="${genderList}" var="g">
                                                    <option value="${g.getGenderId()}">${g.getGenderName()}</option>
                                                </c:forEach>
                                            </select>
                                        </div>
                                        <c:if test="${ageErr != null}">
                                            <div class="col-lg-12">
                                                <div class="alert alert-warning">
                                                    <strong>Warning!</strong> ${ageErr}
                                                </div>
                                            </div>
                                        </c:if>
                                        <div class="col-lg-12">
                                            <p style="opacity: 0.7; margin-left: 10px">YOUR DATE OF BIRTH</p>
                                            <fieldset>
                                                <input name="dob" type="date" id="date" value="2000-1-1" required="">
                                            </fieldset>
                                        </div>
                                        <div class="col-lg-12" style="margin-bottom: 25px ; margin-left: 10px">
                                            <p style="opacity: 0.7">ADDRESS: </p>                                            
                                            <select name="address" id="province">
                                                <c:forEach items="${provinceList}" var="p">
                                                    <option value="${p.getName()}">${p.getName()}</option>
                                                </c:forEach>
                                            </select>                                         
                                            <!--                                                <input type="text" id="temp" name="temp">-->
                                        </div>
                                        <c:if test="${phoneErr != null}">
                                            <div class="col-lg-12">
                                                <div class="alert alert-warning">
                                                    <strong>Warning!</strong> ${phoneErr}
                                                </div>
                                            </div>
                                        </c:if>
                                        <div class="col-lg-12">
                                            <fieldset>
                                                <input name="phone" type="text" id="phone" placeholder="YOUR PHONE NUMBER..." required="">

                                            </fieldset>   
                                        </div>
                                        <div class="col-lg-12" style="margin-bottom: 25px ; margin-left: 10px">
                                            <p style="opacity: 0.7; display: inline-block">SIGN UP AS:&emsp;</p>
                                            <select name="role">
                                                <c:forEach items="${roleList}" var="r">
                                                    <option value="${r.getRoleId()}">${r.getRoleName()}</option>
                                                </c:forEach>
                                            </select>
                                        </div>
                                        <div class="col-lg-12">
                                            <fieldset>
                                                <button type="submit" id="form-submit" class="button">SIGN UP</button>
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
            document.getElementById("province").addEventListener("input", function () {
                document.getElementById("temp").value = this.value;
            });


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

