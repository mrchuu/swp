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

            .slider-detail {
                max-width: 800px;
                margin: 0 auto;
                padding: 20px;
            }

            .slider-detail-header {
                text-align: center;
                margin-bottom: 20px;
            }

            .slider-detail-content {
                border: 1px solid #ccc;
                padding: 20px;
            }

            .form-group {
                margin-bottom: 20px;

            }

            .form-group label {
                display: block;
                font-weight: bold;
            }

            .form-group input[type="text"],
            .form-group select,
            .form-group textarea {
                width: 100%;
                padding: 5px;
                border-radius: 3px;
                border: 1px solid #ccc;
            }

            .form-group select {
                height: 30px;
            }

            .form-group textarea {
                height: 100px;
            }

            .form-group button {
                background-color: #007bff;
                color: #fff;
                border: none;
                padding: 10px 20px;
                border-radius: 3px;
                cursor: pointer;
            }

            .form-group button:hover {
                background-color: #0069d9;
            }
            .form-group input-wrapper {
                display: flex;
                align-items: center;
            }

            .form-group img {
                width: 760px;
                margin-left: 10px;
                border: 1px solid #ddd;
                padding: 300px;
                box-sizing: border-box;
                border-radius: 3px;
            }
            .form-group preview-image {
                max-width: 100%;
                width: 300px;
                margin-left: 10px;
                border: 1px solid #ddd;
                padding: 300px;
                box-sizing: border-box;
                border-radius: 3px;
            }
            .quantity{
                text-align: center;
                margin-right: 608px;

            }
            .quantity label {
                color: white;
                font-weight: bold;
                margin-right: 10px;
            }

            .quantity input[type="number"] {
                width: 60px;
                padding: 5px;
                border-radius: 3px;
                border: 1px solid #ccc;
            }

            .form-group1{
                margin-bottom: 20px;
            }
            .quantity1{
                 text-align: center;
                margin-right: 592px;
                margin-top: 10px;
                

            }
            


            /*.quantity input[type="number"]::-webkit-inner-spin-button,
            .quantity input[type="number"]::-webkit-outer-spin-button {
              -webkit-appearance: none;
              margin: 0;
            }*/

            /*.quantity input[type="number"]:focus {
              outline: none;
              box-shadow: 0 0 3px #007bff;
            }*/

        </style>
    </head>
    <body>
        <jsp:include page="header.jsp"/>

        <!-- Cach su dung template: dung 2 the include de lay header va footer, sau do pass section o be duoi vao 
        giua roi chen noi dug trang vao phan ben duoi -->


        <section class="heading-page header-text">

            <div class="container">
                <div class="row">

                    <form method="post" action="addOrUpdateCourse" enctype="multipart/form-data">
                        <input type="hidden" name="update" value="${requestScope.update}"><!-- update hay add -->
                        <c:if test="${requestScope.update == true}">
                            <input type="hidden" name="course_id" value="${requestScope.course.getCourse_id()}">
                        </c:if>
                        <!-- Các trường nhập liệu -->
                        <div class="slider-detail">
                            <div class="slider-detail-header">
                                <h2> ${requestScope.update ? "COURSE DETAIL EDIT" : "ADD COURSE"}</h2>
                            </div>

                            <div class="form-group">
                                <label for="post-title">Name:</label>
                                <input type="text" id="post-title" name="course_name" placeholder="Enter course name" value="${requestScope.update ? requestScope.course.getCourse_name() : null}" required>
                                <label for="post-title">Title:</label>
                                <input type="text" id="post-title" name="course_title" placeholder="Enter title" value="${requestScope.update ? requestScope.course.getCourseTilte() : null}" required>

                            </div>
                            <div class="form-group" style="display: flex;align-items: center;">
                                <label for="post-category" style="margin-right: 25px; color: white">Subject:</label>
                                <select id="post-category" name="sub_id" style="width: 200px">
                                    <c:forEach items="${requestScope.subList}" var="subject">
                                        <option value="${subject.getSub_id()}">${subject.getSub_name()}</option>
                                    </c:forEach>
                                </select>
                            </div>

                            <div class="form-group" style="display: flex;align-items: center;">
                                <label for="level" style="margin-right: 45px; color: white">Level: </label>
                                <select id="level" name="level_id" style="width: 160px">
                                    <c:forEach items="${requestScope.levelList}" var="level">
                                        <option value="${level.getLevel_id()}">${level.getLevel_name()}</option>
                                    </c:forEach>
                                </select>
                            </div>
                            <div class="quantity">
                                <label for="duration" style="color: white; ">Duration: </label>
                                <input type="number" id="duration" name="duration" min="1" max="1000" value="30">
                            </div>

                            <div class="quantity1">
                                <label for="price" style="color: white; ">Price: </label>
                                <input type="number" id="price" name="price" min="0" max="1000000" step="1000" value="60000">
                            </div>
                            <div class="quantity1">
                                <label for="active" style="color: white; ">Status: </label>
                                <input type="checkbox" name="active" checked="true" value="true">
                            </div>
                            <div class="form-group">
                                <textarea name="course_des" placeholder="Enter description"  style="margin: 15px 0;">${requestScope.update ? requestScope.course.getCourse_desc() : null}</textarea>
                            </div>

                            <div class="form-group1" style="display: flex;align-items: center;">
                                <label for="post-image" style="margin-right: 10px; color: white"> Image:</label>
                                <div class="input-wrapper">
                                    <input type="file" id="post-image" name="post_image" style="color: white">
                                </div>
                            </div>
                            <div class="form-group" style=" width: 740px;">
                                <img  id="preview-image" src="${requestScope.course.getCourse_img()}" alt="Preview image" style="width: 100%">
                            </div>
                        </div>  
                        <div >
                            <button style="padding: 0 20px" type="submit">Save</button>
                        </div>
                    </form>
                </div>
            </div>

        </section>
        <jsp:include page="footer.jsp"/>
        <script>
            const postImageInput = document.getElementById('post-image');
            const previewImage = document.getElementById('preview-image');

            postImageInput.addEventListener('change', () => {
                const file = postImageInput.files[0];
                const reader = new FileReader();

                reader.onload = (event) => {
                    previewImage.src = event.target.result;
                };

                reader.readAsDataURL(file);
            });
        </script>
    </body>
</html>
