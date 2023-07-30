<%-- 
    Document   : UnauthorizedAccess
    Created on : Jul 20, 2023, 4:07:55 PM
    Author     : FPT
--%>

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
                <div class="row">
                    <img style="max-height: 400px; max-width: 400px; margin: 10px auto; border-radius: 200px" src="img/tempAvatar.jpg" alt="alt"/>
                    
                </div>
                <div class="row" style="display: flex; justify-content: center; padding-right: 200px">
                    <h3 style="color: red; text-align: right" class="col-lg-3">401: </h3>
                    <h4 style="color: white; text-align: left" class="col-lg-3">Unauthorized Access</h4>
                </div>
            </div>
        </section>

        <jsp:include page="footer.jsp"/>
    </body>
</html>
