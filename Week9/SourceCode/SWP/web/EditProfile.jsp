<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="entity.Province" %>
<%@page import="dao.ProvinceDAO" %>
<%@page import="entity.Gender" %>
<%@page import="dao.GenderDAO" %>
<%@page import="java.util.Vector" %>
<%
    GenderDAO gd = new GenderDAO();
    ProvinceDAO pd = new ProvinceDAO();
    Vector<Province> provinceList = pd.getAllProvince();
    Vector<Gender> genderList = gd.getAllGender();
    pageContext.setAttribute("genderList", genderList);
    pageContext.setAttribute("provinceList", provinceList);
    String pname = "";
%>
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


        <!-- Additional CSS Files -->
        <link rel="stylesheet" href="assets/css/fontawesome.css">
        <link rel="stylesheet" href="assets/css/templatemo-edu-meeting.css">
        <link rel="stylesheet" href="assets/css/owl.css">
        <link rel="stylesheet" href="assets/css/lightbox.css">
        <link rel="stylesheet" href="assets/css/styling.css?version=20">
    </head>
    <body>
        <jsp:include page="header.jsp"/>

        <!-- Cach su dung template: dung 2 the include de lay header va footer, sau do pass section o be duoi vao 
        giua roi chen noi dug trang vao phan ben duoi -->
        <section class="contact-us" id="contact">
            <div class="container">
                <div class="row">
                    <div class="col-lg-12 align-self-center">
                        <div class="row align-content-center justify-content-center">
                            <c:if test="${sessionScope.msgSuccess != null}">
                                <div class="col-lg-12">
                                    <div class="alert alert-success">
                                        <strong>${msgSuccess}</strong> 
                                    </div>
                                </div>
                            </c:if>
                            <c:remove var="msgSuccess" scope="session" />
                            <div class="col-lg-6">
                                <form id="contact" action="${pageContext.request.contextPath}/editProfile" method="post" >
                                    <div class="row">
                                        <div class="col-lg-12">
                                            <h2 class="text-center" >Edit Profile</h2>
                                        </div>
                                        <input name="uid" type="text" value="${currUser.getUserId()}" hidden="">
                                        <div class="col-lg-12 noDirectEdit">
                                            <p>Email: ${currUser.getUserEmail()}</p>
                                        </div>

                                        <div class="col-lg-12 noDirectEdit">
                                            <p>Change your password <a href="changePassword.jsp">here</a>  </p>
                                        </div>

                                        <div class="col-lg-12">
                                            <fieldset>
                                                <input name="fullname" type="text" id="fullname" placeholder="YOUR FULLNAME..." value="${currUser.getFullName()}" required="">
                                            </fieldset>
                                        </div>
                                        <div class="col-lg-12" style="margin-bottom: 25px ; margin-left: 10px">
                                            <p style="opacity: 0.7">YOUR GENDER</p>
                                            <select name="gender">
                                                <c:forEach items="${genderList}" var="g">
                                                    <option value="${g.getGenderId()}">${g.getGenderName()}</option>
                                                </c:forEach>
                                            </select>
                                        </div>
                                        <div class="col-lg-12">
                                            <p style="opacity: 0.7; margin-left: 10px">YOUR DATE OF BIRTH</p>
                                            <fieldset>
                                                <input name="dob" type="date" id="date" value="${currUser.getDob()}" required="">
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
                                        <c:if test="${sessionScope.phoneErr != null}">
                                            <div class="col-lg-12">
                                                <div class="alert alert-warning">
                                                    <strong>Warning!</strong> ${phoneErr}
                                                </div>
                                            </div>
                                        </c:if>
                                        <c:remove var="phoneErr" scope="session" />
                                        <div class="col-lg-12">
                                            <fieldset>
                                                <input name="phone" type="text" id="phone" value="${currUser.getUserPhone()}" placeholder="YOUR PHONE NUMBER..." required="">

                                            </fieldset>   
                                        </div>
                                        <div class="col-lg-12">
                                            <fieldset>
                                                <button type="submit" id="form-submit" class="button">Update</button>
                                            </fieldset>
                                        </div>
                                    </div>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </section>

        <jsp:include page="footer.jsp"/>

    </body>
</html>
