<%-- 
    Document   : template
    Created on : May 18, 2023, 8:35:04 PM
    Author     : Phan Nguyen Tu Anh
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
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css" integrity="sha512-iecdLmaskl7CVkqkXNQ/ZH/XLlvWZOJyj7Yy7tcenmpD1ypASozpmT/E0iPtmFIB46ZmdtAc9eNBvH0H/ZpiBw==" crossorigin="anonymous" referrerpolicy="no-referrer" />


        <title>Education Template - Meeting Detail Page</title>

        <!-- Bootstrap core CSS -->
        <link href="vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>

        <!-- Additional CSS Files -->
        <link rel="stylesheet" href="assets/css/fontawesome.css">
        <link rel="stylesheet" href="assets/css/templatemo-edu-meeting.css">
        <link rel="stylesheet" href="assets/css/owl.css">
        <link rel="stylesheet" href="assets/css/lightbox.css">
        <link rel="stylesheet" href="assets/css/styling.css?version=75"/>

        <style>
            .importBtn{
                position: relative;
                right: 100px;
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
                    <div class="QuestionListEdit">
                        <button id="importBtn" class="btn btn-danger" onclick="showModal()" style="position: relative; right: -500px; top: 30px">
                            Import question from files
                        </button>
                        <form action="EditQuizContent" method="Post" id="EditQuizForm">
                            <div class="col-12">

                                <input style="font-size: 150%" type="text" name="quiz_name" value="${requestScope.quiz_name}" placeholder="nhập tên bài học">
                            </div>
                            <div class="col-12" style="display: none">
                                <h4>Tên bài quiz</h4>
                                <input type="text" name="quiz_id" value="${requestScope.quiz_id}" placeholder="nhập tên bài học">
                            </div>
                            <c:forEach items="${questionList}" var="q">
                                <div class="col-12 QuestionEdit" id="QuestionEdit${q.getQues_id()}">
                                    <input type="text" name="quesContent" value="${q.getQues_content()}">
                                    <button style=" margin-left: 33px" type="button" onclick="storeQuestionDeletion('${q.getQues_id()}')">
                                        <i style="color: #c93460" class="fa-solid fa-trash-can"></i>
                                    </button> 
                                    <div id="ChoiceList${q.getQues_id()}">
                                        <c:forEach items="${q.getChoices()}" var="c">
                                            <div class="choiceEdit" id="choiceEdit${c.getChoice_id()}">
                                                <input class="tick" type="radio" name="rightChoiceFor${q.getQues_id()}" value="${c.getChoice_id()}"
                                                       <c:if test="${c.isIs_true() == true}">
                                                           checked="true"
                                                       </c:if>
                                                       >
                                                <input type="text" name="EditedChoiceContent" value="${c.getChoice_content()}">
                                                <button type="button" onclick="storeChoiceDeletion('${c.getChoice_id()}')">
                                                    <i class="fa-solid fa-trash-can"></i>
                                                </button>

                                            </div>
                                        </c:forEach>
                                    </div>
                                    <button class="addnewchoicebtn" type="button" onclick="addnewChoice('${q.getQues_id()}', '${requestScope.quiz_id}')">
                                        <h4 style="color: #142254; font-size: 100%; margin: 10px"> <i class="fa-solid fa-plus"></i> Add new choice to this question</h4>
                                    </button>
                                </div>


                            </c:forEach>
                            <div id="addedQuestion">

                            </div>
                            <input id="result" type="text" name="deletionchoice" style="display: none"/>
                            <input id="result1" type="text" name="deletionquestion" style="display: none"/>
                            <button class="addnewQuestionBtn" type="button" onclick="adnewQuestion('${requestScope.quiz_id}')">
                                <i class="fa-solid fa-circle-plus fa-2x" style="color: #af4646;"></i> &nbsp; Add a question to this quizz lesson
                            </button>  
                            <input style="display: block" id="Editsubmit" type="submit" name="name" value="Edit"/>
                        </form>
                    </div>
                </div>
            </div>
            <div class="modal show" id="myModal">
                <div class="modal-dialog modal-dialog-centered">
                    <div class="modal-content">

                        <!-- Modal Header -->
                        <div class="modal-header">
                            <h6 style="color: black" class="modal-title">Tuân thủ định dạng file của chúng tôi</h6>
                        </div>

                        <!-- Modal body -->
                        <div class="modal-body">
                            <form id="contact" action="ImportQuestion" method="Post" enctype="multipart/form-data">

                                <div class="col-lg-12" style="margin-bottom: 25px ; margin-left: 10px">
                                    <h6 style="color: black">Tính năng thêm câu hỏi bằng file excel sẽ được thêm vào trong thời gian không xa, hiện tại, hệ thống chỉ có thể nhận file word với định dạng như sau</h6>
                                    <img src="img/dinhdang.png"/>
                                    <input name="quiz_name" type="text" value="${requestScope.quiz_name}" style="display: none"/>
                                    <input name="quiz_id" type="text" value="${requestScope.quiz_id}" style="display: none"/>
                                    <input name="QuizImport" type="file" accept=".txt, .docx" onchange="this.form.submit()" id="userImg" style="all:unset"/>
                                </div>



                            </form>
                        </div>

                        <!-- Modal footer -->


                    </div>
                </div>
            </div>
        </section>
        <jsp:include page="footer.jsp"/>
        <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
        <script>
                                    function showModal() {
                             
                                        var myModal = new bootstrap.Modal(document.getElementById("myModal"));
                                        myModal.show();
                                    }
                                    function updateChoiceContent(choice_id) {
                                        var a = document.getElementById("EditedChoiceContent" + choice_id);
                                        var url = "UpdateChoiceOnChange?choice_id=" + encodeURIComponent(choice_id) + "&currentVal=" + encodeURIComponent(a.value);
                                        var xmlHttp = new XMLHttpRequest();
                                        xmlHttp.open("GET", url, true);
                                        xmlHttp.onreadystatechange = function () {
                                            if (xmlHttp.readyState === 4 && xmlHttp.status === 200) {
                                                a.value = xmlHttp.responseText;
                                                console.log(xmlHttp.responseText);
                                            }
                                        };
                                        xmlHttp.send();
                                    }
                                    function storeChoiceDeletion(choice_id) {
                                        var currentDisplay1 = document.getElementById("choiceEdit" + choice_id);
                                        currentDisplay1.style.display = currentDisplay1.style.display != "none" ? "none" : "block";
                                        var url = "HandleChoiceDeletion?choice_id=" + encodeURIComponent(choice_id);
                                        var xmlHttp = new XMLHttpRequest();
                                        xmlHttp.open("POST", url, true);
                                        xmlHttp.onreadystatechange = function () {
                                            if (xmlHttp.readyState === 4 && xmlHttp.status === 200) {
                                                var result = document.getElementById("result");
                                                result.value += xmlHttp.responseText + ",";
                                            }
                                        };
                                        xmlHttp.send();
                                    }
                                    function storeQuestionDeletion(ques_id) {
                                        var a = document.getElementById("QuestionEdit" + ques_id);
                                        a.style.display = a.style.display != "none" ? "none" : "block";
                                        var url = "HandleChoiceDeletion?question_id=" + encodeURIComponent(ques_id);
                                        console.log(url)
                                        var xmlHttp = new XMLHttpRequest();
                                        xmlHttp.open("GET", url, true);
                                        xmlHttp.onreadystatechange = function () {
                                            if (xmlHttp.readyState === 4 && xmlHttp.status === 200) {
                                                var result = document.getElementById("result1");
                                                result.value += xmlHttp.responseText + ",";
                                            }
                                        };
                                        xmlHttp.send();
                                    }
                                    function addnewChoice(quesId, quizId) {
//                                        var a = document.getElementById('QuestionEdit' + quesId);
//                                    var a = document.getElementById('ChoiceList' + quesId);
                                        var url = "AddChoice?quesId=" + encodeURIComponent(quesId) + "&Quizid=" + encodeURIComponent(quizId);
                                        var xmlHttp = new XMLHttpRequest();
                                        xmlHttp.open("GET", url, true);
                                        xmlHttp.onreadystatechange = function () {
                                            if (xmlHttp.readyState === 4 && xmlHttp.status === 200) {
                                                var response = JSON.parse(xmlHttp.responseText);
                                                var a = document.getElementById('ChoiceList' + quesId);
                                                a.innerHTML += "<div class='choiceEdit' id='choiceEdit" + response.maxChoiceId + "'><input class='tick' type='radio' name='rightChoiceFor" + quesId + "' value='" + response.maxChoiceId + "'><input type='text' id='EditedChoiceContent" + response.maxChoiceId + "' name='EditedChoiceContent' value='" + response.defaultChoiceContent + "' onChange='updateChoiceContent(" + response.maxChoiceId + ")'><button type='button' onclick='storeChoiceDeletion(" + response.maxChoiceId + ")'><i class='fa-solid fa-trash-can'></i></button></div>";
                                                console.log(xmlHttp.responseText);

                                            }
                                        };
                                        xmlHttp.send();
                                    }
                                    function adnewQuestion(quizId) {
                                        var a = document.getElementById("addedQuestion");
                                        var url = "AddQuestion?quizId=" + encodeURIComponent(quizId);
                                        var xmlHttp = new XMLHttpRequest();
                                        xmlHttp.open("GET", url, true);
                                        xmlHttp.onreadystatechange = function () {
                                            if (xmlHttp.readyState === 4 && xmlHttp.status === 200) {
                                                a.innerHTML += "<div class='col-12 QuestionEdit' id='QuestionEdit" + xmlHttp.responseText + "'><input type='text' name='quesContent' value='Nhập nội dung cho câu hỏi'><button style='margin-left: 33px' type='button' onclick='storeQuestionDeletion(" + xmlHttp.responseText + ")'/><i style='color: #c93460' class='fa-solid fa-trash-can'></i></button><div id='ChoiceList" + xmlHttp.responseText + "'></div><button class='addnewchoicebtn' type='button' onclick='addnewChoice(" + xmlHttp.responseText + ", " +${requestScope.quiz_id} + ")'><h4 style='color: #142254; font-size: 100%; margin: 10px'> <i class='fa-solid fa-plus'></i> Add new choice to this question</h4></button></div>";

                                                console.log("aaaaaa");
                                            }
                                        };
                                        xmlHttp.send();



                                    }
        </script>
    </body>
</html>
