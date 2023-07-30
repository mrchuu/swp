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
                margin-bottom: 10px;
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
        </style>
    </head>
    <body>
        <jsp:include page="header.jsp"/>

        <!-- Cach su dung template: dung 2 the include de lay header va footer, sau do pass section o be duoi vao 
        giua roi chen noi dug trang vao phan ben duoi -->


        <section class="heading-page header-text">

            <div class="container">
                <div class="row">
                    <form method="post" enctype="multipart/form-data" action="addOrUpdateSlider">
                        <c:if test="${requestScope.update}">
                            <input type="hidden" name="slider_id" value="${requestScope.slider.getSlider_id()}">
                        </c:if>
                        <input type="hidden" name="update" value="${requestScope.update}"><!-- update or add slider -->
                        <!-- Các trường nhập liệu -->
                        <div class="slider-detail">
                            <div class="slider-detail-header">
                                <h2>${requestScope.update ? "Slider Details Edit" : "Add Slider"}</h2>
                            </div>
                            <div class="form-group">
                                <label for="slider-title">Title:</label>
                                <input type="text" name="slider_title" placeholder="Title" value="${requestScope.update ? slider.getSlider_title() : null}" required>
                            </div>
                            <div class="form-group">
                                <label for="slider-backlink">Backlink:</label>
                                <input type="text" name="slider_link" placeholder="Black link" value="${requestScope.update ? slider.getSlider_link() : null}" required>
                            </div>

                            <div class="form-group">
                                <label for="slider-note">Note:</label>
                                <textarea type="text" name="slider_note" placeholder="Enter note">${requestScope.update ? slider.getSlider_note() : null} </textarea>
                            </div>

                            <div class="form-group" style="display: flex;align-items: center;">
                                <label for="slider-image" style="margin-right: 10px; color: white">Slider Image:</label>
                                <div class="input-wrapper">
                                    <input type="file" id="slider-image" name="slider_image" accept="image/*" style="color: white">
                                </div>
                            </div>
                            <div class="form-group" style=" width: 760px">
                                <label for="preview-image" style="display: none; width: 760px;
                                       ">Preview:</label>
                                <img id="preview-image" src="${slider.getSlider_img()}" alt="Preview image" style="width: 100%; padding: 0">
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
            const sliderImageInput = document.getElementById('slider-image');
            const previewImage = document.getElementById('preview-image');

            sliderImageInput.addEventListener('change', () => {
                const file = sliderImageInput.files[0];
                const reader = new FileReader();

                reader.onload = (event) => {
                    previewImage.src = event.target.result;
                };

                reader.readAsDataURL(file);
            });
        </script>
    </body>
</html>
