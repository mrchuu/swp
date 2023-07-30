<%-- 
    Document   : BlogDetails
    Created on : May 19, 2023, 1:09:09 PM
    Author     : Phan Nguyen Tu Anh
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

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
            table, td, th {
  border: 1px solid white !important;
}

table {
  width: 100%;
  border-collapse: collapse;
}
            th, td {
                padding: 8px;
                border: 1px solid #ddd;
                word-wrap: break-word;
                vertical-align: top;
                white-space: nowrap;
                overflow: hidden;
                text-overflow: ellipsis;
            }
            table {
                width: 100%;
                margin-bottom: 1em;
                border: 2px solid #ddd;
                table-layout: fixed;
            }

            th, td {
                padding: 8px;
                border: 1px solid #ddd;
                word-wrap: break-word;
                vertical-align: top;
            }
            td {
                padding: 8px;
                border: 1px solid #ddd;
                word-wrap: break-word;
                vertical-align: top;
                color: white; /* Add this line to change the text color to white */
            }
            th{
                color: white;
            }

            table{
                width:100%;

            }
            .anh{
                background-color: white
                    ;
            }
            /*            table {
                            border-collapse: collapse;
                            width: 100%;
                            margin-bottom: 1em;
                            border: 2px solid #ddd;
                        }*/
            table {
                width: 100%;
                margin-bottom: 1em;
                border: 2px solid #ddd;
                table-layout: fixed;
            }
            /*            th, td {
                            text-align: left;
                            padding: 8px;
                            border: 1px solid #ddd;
                            color: white;
                        }*/
            th, td {
                padding: 8px;
                border: 1px solid #ddd;
                word-wrap: break-word;
                vertical-align: top;
            }
            .table-container {
                text-align: center;
            }

            table {
                width: auto;
                margin: 0 auto;
            }

            /*            th, td {
                            text-align: left;
                            padding: 8px;
                            border: 1px solid #ddd;
            background-color: #f2f2f2;
                        }*/
            .sidebar {
                width: 100%;

                padding: 20px;
                background-color: white;
                box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
            }
            .sidebar h3 {
                margin-top: 0;
            }
            .search-form {
                margin-bottom: 20px;
            }
            .search-form input[type="text"] {
                width: 500px;
                padding: 10px;
                border: none;
                border-radius: 5px;
                box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
            }

            .widget-title {
                color: white;
            }
            .widgetre ul a {
                border: 1px solid black;
                border-radius: 5px;
                padding: 5px;
                margin-right: 5px;
            }
            .add-post-button {
                float: left;
                margin: 10px;
            }
            .search-box {
                float: right;
                left:  370px;
                position: absolute;
                margin: -40px 0;
            }

            .search-box input[type="text"] {
                width: 500px;
                padding: 5px 5px;
                border-radius: 5px;
                border: 1px solid #ccc;
            }
            .search-box button[type="submit"] {
                width: 100px;
                height: 30px;
            }

        </style>
    </head>
    <body>
        <jsp:include page="header.jsp"/>

        <!-- Cach su dung template: dung 2 the include de lay header va footer, sau do pass section o be duoi vao 
        giua roi chen noi dug trang vao phan ben duoi -->


        <section class="heading-page header-text">

            <div class="container">
                <div class="row">
                    <div class="search-box" >
                        <form action="search" method="get">
                            <input type="text" name="txt" placeholder="Search...">
                            <button type="submit">Search</button>
                        </form>
                    </div>
                    <li><button class="add-post-button" onclick="window.location.href = 'courseDetailsEdit?type=add'">Add Course</button></li>


                    <table border="1">
                        <tr>
                            <th>course id</th>
                            <th>course_name</th>
                            <th>course_img</th>
                            <th>course_price</th>
                            <th>sub_id</th>
                            <th>course_status</th>
                            <th>duration</th>
                            <th>action</th>
                        </tr>
                        <c:forEach items="${requestScope.courseList}" var="course" varStatus="loop">
                            <tr id="course_${course.getCourse_id()}">
                                <td>${loop.index+1}</td>
                                <td>${course.getCourse_name()}</td>
                                <td>${course.getCourse_img()}</td>
                                <td>${course.getCourse_price()}</td>
                                <td>${course.getSub_id()}</td>
                                <td>${course.getCourse_status()}</td>               
                                <td>${course.getDuration()}</td>    
                                <td>
                                    <button onclick="window.location.href = 'courseDetailsEdit?course_id=${course.getCourse_id()}&type=edit'">Edit</button>
                                </td>
                               
                            </tr>             
                        </c:forEach>
                    </table>
                </div>
        </section>
        <jsp:include page="footer.jsp"/>
        <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
        <script>

        </script>
    </body>
</html>
