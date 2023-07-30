<%-- 
    Document   : BlogDetails
    Created on : May 19, 2023, 1:09:09 PM
    Author     : Phan Nguyen Tu Anh
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

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
            .table-container {
                text-align: center;
                overflow-x: auto;
            }

            table{
                width:100%;

            }
            .anh{
                background-color: gray;
            }
            table {

                border-collapse: collapse;
            }
            table, td, th {
                border: 2px solid #828282 !important;
                background-color: #e9e8e1
            }
            table{
                width: 100% !important;
            }
            .postImg img{
                width: 100%;
                height: 150px
            }

            .title{
                padding: 10px !important
            }
            table button{
                display: block;
                margin: 5px auto !important;
            }
            .delete-post-button{
                color: white;
                background-color: #cc4040;
                width: 60%;
                border-radius: 15px;
                padding: 7px 15px;
                border: none
            }

            .edit-post-button{
                color: white;
                background-color: #34bceb;
                width: 60%;
                border-radius: 15px;
                padding: 7px 15px;
                border: none
            }

            .table-container {
                text-align: center;
            }

            table {
                width: auto;
                margin: 0 auto;
                
            }
            .addAndSearch{
                display: flex;
                padding: 10px 0 !important
            }
            .search-box{
                text-align: left
            }
            .search-box button{
                background-color: #34BCEB;
                color: white;
                border-radius: 0 15px 15px 0;
                padding: 1px 10px;
                position: relative;
                left: -3px
            }
            .search-box input{
                border-radius: 15px 0 0 15px;
                width: 60%
            }
            .addBtn{
                text-align: right;
                position: relative;
                left: -25px
            }
            .addBtn button{
                color: white;
                background-color: #3aa25d;
                padding: 7px 15px;
                border-radius: 15px
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
                    <li><button class="add-post-button" onclick="window.location.href = 'sliderDetailsEdit?type=add'">Add Slider</button></li>


                    <table border="1">
                        <tr>
                            <th>ID</th>
                            <th>Title</th>
                            <th>Image</th>
                            <th>Blacklink</th>
                            <th>Status</th>
                            <th>Action</th>
                        </tr>
                        <c:forEach items="${requestScope.sliderList}" var="slider" varStatus="loop">
                            <tr id="slider_${slider.getSlider_id()}">
                                <td>${loop.index+1}</td>
                                <td>${slider.getSlider_title()}</td>
                                <td>${slider.getSlider_img()}</td>
                                <td>${slider.getSlider_link()}</td>
                                <td>${(slider.isSlider_status()) ? "Active" : "Inactive"}</td>
                                <td>
                                    <button onclick="deleteSlider(${slider.getSlider_id()})">Delete</button>
                                    <button onclick="window.location.href = 'sliderDetailsEdit?slider_id=${slider.getSlider_id()}&type=edit'">Edit</button>
                                </td>
                            </tr>             
                        </c:forEach>
                    </table>


                </div>

        </section>



        <jsp:include page="footer.jsp"/>
    </body>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script>
                                        function deleteSlider(sliderId) {
                                            //Send an AJAX request to your server-side script
                                            $.ajax({
                                                url: "deleteSlider",
                                                type: "POST",
                                                data: {slider_id: sliderId},
                                                success: function (response) {
                                                    // Remove the row from the table
                                                    $("#slider_" + sliderId).remove();
                                                }
                                            });
                                        }
    </script>
</html>
