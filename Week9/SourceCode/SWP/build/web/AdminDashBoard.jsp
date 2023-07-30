<%-- 
    Document   : AdminDashBoard.jsp
    Created on : Jul 24, 2023, 9:00:43 AM
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
        <style>
            .dbSquare{
                padding-top: 20px;
                border-radius: 20px
            }
            h3{
                margin-top: 35px !important;
                display: inline-block
            }
            .adminIcon{
                display: block;
                width: 100%;
                color: #4a5d6d
            }
            .nav-item{
                margin: 30px auto !important;
            }
            .active a{
                color: #e3a31e
            }
        </style>
    </head>
    <body>
        <jsp:include page="header.jsp"/>

        <!-- Cach su dung template: dung 2 the include de lay header va footer, sau do pass section o be duoi vao 
        giua roi chen noi dug trang vao phan ben duoi -->
        <section class="heading-page header-text">
            <div class="container-fluid">
                <div class="row" style="min-height: 600px">
                    <div class="col-2" style="background-color: rgb(40,45,49, 0.6); ">
                        <div class="nav-item active">
                            <a href="AdminDashBoard">Admin DashBoard</a>
                        </div>
                        <div class="nav-item">
                            <a href="ReportedMessages">Reported Messages</a>
                        </div>
                        <div class="nav-item">
                            <a href="AdminUserList">UserList</a>
                        </div>
                        <div class="nav-item">
                            <a href="">Pending Courses</a>
                        </div>

                    </div>
                    <div class="col-10" style="background-color: rgb(0,0,0, 0.3);">
                        <div class="row">
                            <h4 style="color: white">Admin DashBoard</h4>                      
                        </div>
                        <div class="row" style="justify-content: space-evenly; padding-top: 70px">
                            <div class="col-3 dbSquare" style="background-color: #9cdee5; height: 300px">
                                <i class="fa fa-graduation-cap fa-8x adminIcon"></i>
                                <h3 style="color: #276cad">${requestScope.UserCount} </h3><h3> &nbsp;học viên</h3>
                            </div>    
                            <div class="col-3 dbSquare" style="background-color: #da6692; height: 300px">
                                <i class="fas fa-chalkboard-teacher fa-8x adminIcon"></i>
                                <h3 style="color: #276cad">${requestScope.LecturerCount} </h3><h3> &nbsp;Giảng viên</h3>
                            </div>    
                            <div class="col-3 dbSquare" style="background-color: #f0c78d; height: 300px">
                                <i class="fa fa-book fa-8x adminIcon"></i>
                                <h3 style="color: #276cad">${requestScope.CourseCount} </h3><h3> &nbsp;Khoá học</h3>
                                <h3 style="display: block; margin-top: 0 !important">đang hoạt động</h3>
                            </div>    
                        </div>
                    </div>
                </div>
            </div>
        </section>

        <jsp:include page="footer.jsp"/>
    </body>
</html>
