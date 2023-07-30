<%-- 
    Document   : LeaderBoard
    Created on : Jul 20, 2023, 12:40:19 AM
    Author     : FPT
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="entity.User" %>
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
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">

        <!-- Additional CSS Files -->
        <link rel="stylesheet" href="assets/css/fontawesome.css">
        <link rel="stylesheet" href="assets/css/templatemo-edu-meeting.css">
        <link rel="stylesheet" href="assets/css/owl.css">
        <link rel="stylesheet" href="assets/css/lightbox.css">
        <script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.5.0/Chart.min.js"></script>

        <style>
            .studentList{
                background-color: #d5f4f3;
                overflow-y: auto;
                height: 500px !important;
            }
            thead th{
                position: Sticky;
                top: -5px;
                background-color: #f4d5d6 !important
            }
            .listTable{

                width: 100%;
            }
            .top3User{
                justify-content: space-evenly
            }
            .top3User img{
                height: 150px;
                width: 150px;
                border-radius: 100px 100px
            }
        </style>
    </head>
    <%
            Integer[] keys = (Integer[])request.getAttribute("key");
            User[] value = (User[])request.getAttribute("value");
    %>
    <body>
        <jsp:include page="header.jsp"/>

        <!-- Cach su dung template: dung 2 the include de lay header va footer, sau do pass section o be duoi vao 
        giua roi chen noi dug trang vao phan ben duoi -->

        <section class="heading-page header-text">
            <div class="container">
                <h4 style="color: white; font-size: 200%">Bảng thi đua</h4>
                <div class="row top3">
                    <div class="row">
                        <canvas class="myChart" id="top3" style="width:50% !important; margin: 20px auto"></canvas>
                    </div>
                </div>
                <div class="row studentList">
                    <table class="table table-striped listTable">
                        <thead>
                            <tr>
                                <th scope="col">Tên</th>
                                <th scope="col">Email</th>
                                <th scope="col">Ngày sinh</th>
                                <th scope="col">Địa chỉ</th>
                                <th scope="col">Ngày tham gia</th>
                                <th scope="col">Điểm tích luỹ</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach items="${requestScope.StudentList}" var="student">
                                <tr>
                                    <td> <a style="color: #f1a225" href="PersonalAccountServlet?viewerId=${requestScope.currUserId}&ProfileId=${student.getUserId()}">${student.getFullName()}</a></td>
                                    <td>${student.getUserEmail()}</td>
                                    <td>${student.getDobFormated()}</td>
                                    <td>${student.getUserAddress()}</td>
                                    <td>${student.getUserTimeFormated()}</td>
                                    <td>${student.getScore()}</td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                    </table>
                </div>
            </div>
        </section>

        <jsp:include page="footer.jsp"/>
        <script>
            var yValues = [<% 
               for (int i = 0; i < keys.length; i++) {
                    out.print("'" + keys[i] + "'");
                    if (i < keys.length - 1) {
                      out.print(", ");
                    }
                }
            %>];
            var xValues = [<% 
               for (int i = 0; i < value.length; i++) {
                    out.print("'" + value[i].getFullName() + "'");
                    if (i < value.length - 1) {
                      out.print(", ");
                    }
                }
            %>];
            var imgPaths = [<%
                for (int i = 0; i < value.length; i++) {
                    out.print("'" + value[i].getUserImg()+ "'");
                    if (i < value.length - 1) {
                      out.print(", ");
                    }
                }
            %>];
            async function loadImages() {
                var loadedImages = [];
                for (const path of imgPaths) {
                    const image = new Image();
                    await new Promise((resolve) => {
                        image.onload = () => resolve();
                        image.src = path;
                    });
                    loadedImages.push(image);
                }
                return loadedImages;
            }



            var ctx = document.getElementById('top3').getContext('2d');
            var barColors = ["#FFDD43", "RGB(192, 204, 226)", "#DD8343"];


            async function renderChart() {
                var images = await loadImages();
                new Chart(ctx, {
                    type: "bar",
                    data: {
                        labels: xValues,
                        datasets: [{
                                backgroundColor: barColors,
                                data: yValues,
                            }]
                    },
                    options: {
                        title: {
                            display: true,
                            fontColor: 'white',
                            fontSize: 100
                        },
                        legend: {
                            display: false
                        },
                        scales: {
                            xAxes: [{
                                    ticks: {
                                        fontColor: '#E8E8E8',
                                        fontSize: 15,

                                    },
                                    gridLines: {
                                        color: '#A09F9F',
                                        lineWidth: 1,
                                        
                                    }

                                }],
                            yAxes: [{

                                    ticks: {

                                        fontColor: '#E8E8E8',
                                        beginAtZero: true,
                                        fontSize: 15,

                                    },
                                    gridLines: {
                                        color: '#A09F9F',
                                        lineWidth: 1
                                    }

                                }]
                        },
                        tooltips: {
                            enabled: true
                        },
                        hover: {
                            animationDuration: 1
                        },
                        animation: {
                            duration: 1,
                            onComplete: function () {
                                var chartInstance = this.chart,
                                        ctx = chartInstance.ctx;
                                ctx.textAlign = 'center';
//                            ctx.fillStyle = "white";
                                ctx.textBaseline = 'bottom';

                                this.data.datasets.forEach(function (dataset, i) {
                                    var meta = chartInstance.controller.getDatasetMeta(i);
                                    meta.data.forEach(function (bar, index) {
                                        var data = dataset.data[index];
                                        ctx.font = "15px Arial";
                                        var x = bar._model.x;
                                        var y = bar._model.y;
                                        var imgWidth = 70;
                                        var imgHeight = 70;
                                        // Draw the image on top of the bar with resizing
                                        ctx.drawImage(images[index], x - (imgWidth / 2), y - (imgHeight + 10), imgWidth, imgHeight);
                                        ctx.fillText(data, x, y - (images[index].height + 15));

                                    });
                                });
                            }
                        }
                    }
                });
            }
            renderChart();

        </script>
    </body>
</html>
