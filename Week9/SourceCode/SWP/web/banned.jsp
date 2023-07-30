<%-- 
    Document   : banned.jsp
    Created on : Jul 23, 2023, 11:42:48 AM
    Author     : FPT
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
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

    </head>
    <body>
        
        <jsp:include page="header.jsp"/>

        <!-- Cach su dung template: dung 2 the include de lay header va footer, sau do pass section o be duoi vao 
        giua roi chen noi dug trang vao phan ben duoi -->
        <section class="heading-page header-text">
            <div class="container">
                <div class="row" style="min-height: 600px">
                    <h5 style="color: white">Bạn đã bị cấm chat do ngôn từ xúc phạm, nếu đây là một sự nhầm lẫn, vui lòng liên hệ với admin manh@gmail.com</h5>
                </div>
            </div>
        </section>

        <jsp:include page="footer.jsp"/>

    </body>
</html>
