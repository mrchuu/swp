<%-- 
    Document   : template
    Created on : May 18, 2023, 8:35:04 PM
    Author     : Phan Nguyen Tu Anh
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
            label{

                margin: 10px
            }
            #target {
                width: 450px;
                height: 250px;
                resize: both;
                position: relative;
                margin: 10px auto 30px auto
            }
            iframe {
                width: 100%;
                height: 100%;
                border: none;
            }
        </style>
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
                            <div class="col-lg-6">
                                <form id="contact" action="addnewLessonTosection" method="post">
                                    <div class="row">
                                        <div class="col-lg-12">
                                            <h2 class="text-center" >Thêm bài học video</h2>
                                        </div>
                                        <div class="col-lg-12">
                                            <fieldset>
                                                <label>Tên Bài Học</label>
                                                <input name="lesson_Name" type="text" id="lesson_Name" required="">
                                            </fieldset>
                                        </div>
                                    </div>
                                    <div class="col-lg-12" style="display: none">
                                        <fieldset>
                                            <label>Id course</label>
                                            <input name="Course_id" type="text" id="Course_id" value="${requestScope.Course_id}"  required="">
                                            <input name="section_id" type="text" id="Section_id" value="${requestScope.Section_id}"  required="">
                                        </fieldset>
                                    </div>
                                    <div class="col-lg-12">
                                        <fieldset>
                                            <label>Video Bài Giảng</label>
                                            <input name="lesson_Video" type="text" id="lesson_Video" onchange="handleVideoChange()" value="${requestScope.lesson_Video}" required="">
                                        </fieldset>
                                        <div class="col-lg-12">
                                            <div class="alert alert-danger" id="linkFormatAlert" style="display: none">
                                                <strong>link video phải ở dạng embed: "https://www.youtube.com/embed/XXXXXXXXXXX", xem hướng dẫn ở <a href="https://www.youtube.com/watch?v=yX3FDQZpdOw&ab_channel=EZSVN">đây</a></strong>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-lg-12 iframeWrapper">
                                        <fieldset>
                                            <div id="target">
                                                <iframe id="demoVid" src=""></iframe>
                                            </div> 
                                        </fieldset>
                                    </div>
                                    <!--                                        them phan dem ki tu da nhap theo thoi gian / gioi han input-->
                                    <div class="col-lg-12">
                                        <fieldset>
                                            <label>Mô tả bài học</label>
                                            <textarea name="Lesson_desc" cols="40" rows="6" ></textarea>
                                        </fieldset>
                                    </div>
                                    <div class="col-lg-12">
                                        <fieldset>
                                            <button type="submit" id="form-submit" class="button">Thêm bài học</button>
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
    <script>
        function validateYouTubeEmbedLink(inputValue) {
            var pattern = /^(https?:\/\/(?:www\.)?youtube\.com\/embed\/[a-zA-Z0-9_-]{11})$/;
            return pattern.test(inputValue);
        }
        function handleVideoChange() {

            var vidSrc = document.getElementById("lesson_Video"); // Replace "yourInputId" with the actual ID of your input element

            var inputValue = vidSrc.value;
            var currentDisplay = document.getElementById("linkFormatAlert");
            if (validateYouTubeEmbedLink(inputValue)) {
                // Valid YouTube embed link
                console.log("Valid YouTube embed link!");
                var demoVid = document.getElementById("demoVid");
                demoVid.src = inputValue;
                currentDisplay.style.display = currentDisplay.style.display == "block" ? "none" : "none";
            } else {
                // Invalid YouTube embed link
                console.log("Invalid YouTube embed link!");

                currentDisplay.style.display = currentDisplay.style.display != "none" ? "none" : "block";
            }


        }






    </script>
</body>
</html>
