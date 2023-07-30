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

            table, th, tr, td{
                border-collapse: collapse;
            }
            table, td, th {
                border: 2px solid #828282 !important;
                background-color: #e9e8e1
            }

            table, td, th {
                border: 1px solid white !important;
            }

            table {
                width: 100%;
                border-collapse: collapse;
            }
            td {
                padding: 8px;
                border: 1px solid #ddd;
                word-wrap: break-word;
                vertical-align: top;
              
            }
            th{
                color: white;
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
                width: 70%;
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
                    <div class="addAndSearch">
                        <div class="search-box col-lg-6" >
                            <form action="#" method="post">
                                <input type="text" name="search_name" placeholder="   Search...">
                                <button type="submit"><i class="fa fa-search"></i></button>
                            </form>
                        </div>
                        <div class="addBtn col-lg-6">
                            <button class="add-post-button" onclick="window.location.href = 'postDetailsEdit?type=add'">Add Post</button>
                        </div>

                    </div>

                    <table class="table table-striped">
                        <tr>
                            <th>Title</th>
                            <th>Image</th>
                            <th>Date</th>
                            <th>Status</th>
                            <th>Blog_id</th>
                            <th>Action</th>
                        </tr>
                        <c:forEach items="${requestScope.postList}" var="post" varStatus="loop">
                            <tr id="post_${post.getPost_id()}">
                                <td class="title col-lg-4">${post.getPost_title()}</td>
                                <td class="postImg col-lg-3"><img src="${post.getPost_img()}"/></td>
                                <td>${post.getPost_dateFormated()}</td>
                                <td>${(post.getPost_status()) ? "Active" : "Inactive"}</td>
                                <td>${post.getBlog_id()}</td>
                                <td>
                                    <button class="delete-post-button" onclick="deletePost(${post.getPost_id()})">Delete</button>
                                    <button class="edit-post-button" onclick="window.location.href = 'postDetailsEdit?post_id=${post.getPost_id()}&type=edit'">Edit</button>
                                </td>
                            </tr>             
                        </c:forEach>
                    </table>
                </div>
        </section>
        <jsp:include page="footer.jsp"/>
        <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
        <script>
                                        function deletePost(postId) {
                                            //Send an AJAX request to your server-side script
                                            console.log("delete post");
                                            $.ajax({
                                                url: "deletePost",
                                                type: "POST",
                                                data: {post_id: postId},
                                                success: function (response) {
                                                    // Remove the row from the table
                                                    $("#post_" + postId).remove();
                                                }
                                            });
                                        }
        </script>
    </body>
</html>
