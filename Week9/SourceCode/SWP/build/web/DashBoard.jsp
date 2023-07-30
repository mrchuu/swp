<%-- 
    Document   : template
    Created on : May 18, 2023, 8:35:04 PM
    Author     : Phan Nguyen Tu Anh
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
        <link rel="stylesheet" href="assets/css/styling.css?version=19"/>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.5.0/Chart.min.js"></script>
    </head>
    <body>
        <%
            String[] keys = (String[])request.getAttribute("key");
            Integer[] value = (Integer[])request.getAttribute("values");
            String[] keys1 = (String[])request.getAttribute("key1");
            Integer[] value1 = (Integer[])request.getAttribute("values1");
            Integer[] key2 = (Integer[])request.getAttribute("key2");
            Integer[] value2 = (Integer[])request.getAttribute("values2");
        %>

        <jsp:include page="header.jsp"/>
        <section class="heading-page header-text">
            <div class="container">
                <div class="row">
                    Sort:
                    <select class="dashBoardInput" name="sortTypePop" id="sortTypePop">
                        <option value="most">Nhiều học sinh nhất</option>
                        <option value="least">Ít học sinh nhất</option>
                    </select>
                    <canvas class="myChart" id="population" style="width:100%"></canvas>
                </div>

                <div class="row">
                    <input type="date" id="DateInput" onchange="dateChange()" class="dashBoardInput" style="position: relative; left: 880px"/>
                    <canvas class="myChart" id="Revenue" style="width:100%"></canvas>
                </div>
                <div class="row">
                    Sort:
                    <select class="dashBoardInput" name="sortTypePar" id="sortTypePar">
                        <option value="most">Nhiều học sinh nhất</option>
                        <option value="least">Ít học sinh nhất</option>
                    </select>
                    <canvas class="myChart" id="participant" style="width:100%"></canvas>
                </div>
            </div>
        </section>

        <jsp:include page="footer.jsp"/>
        <script>
            if ('${sessionScope.DateInput}' === null || '${sessionScope.DateInput}' === "") {
                const currentDate = new Date();

                // Format the date as "yyyy-mm-dd" for the input value
                const formattedDate = currentDate.toISOString().slice(0, 10);

                // Set the formatted date as the default value for the input
                document.getElementById("DateInput").value = formattedDate;
            } else {
                document.getElementById("DateInput").value = '${sessionScope.DateInput}';
            }

            var xValuesPop = [<% 
               for (int i = 0; i < keys.length; i++) {
                    out.print("'" + keys[i] + "'");
                    if (i < keys.length - 1) {
                      out.print(", ");
                    }
                }
            %>];
            var yValuesPop = [<% 
               for (int i = 0; i < value.length; i++) {
                    out.print("'" + value[i] + "'");
                    if (i < value.length - 1) {
                      out.print(", ");
                    }
                }
            %>];
            var barColors = ["#FA8C51", "#EA98DA", "#6CE2D2", "#f5f588", "#EB9898"];
            new Chart("population", {
                type: "bar",
                data: {
                    labels: xValuesPop,
                    datasets: [{
                            backgroundColor: barColors,
                            data: yValuesPop,
                            label: "aa"
                        }]
                },

                options: {
                    title: {
                        display: true,
                        fontColor: 'white',
                        text: '5 tỉnh thành',
                        fontSize: 35
                    },
                    scales: {
                        xAxes: [{
                                ticks: {
                                    fontColor: '#E8E8E8',
                                    fontSize: 15,

                                },
                                gridLines: {
                                    color: '#A09F9F',
                                    lineWidth: 1
                                }
                            }],
                        yAxes: [{

                                ticks: {
                                    fontColor: '#E8E8E8',
                                    beginAtZero: true,
                                    fontSize: 15,
                                    callback: function (value) {
                                        if (value % 1 === 0) {
                                            return value;
                                        }
                                    }
                                },
                                gridLines: {
                                    color: '#A09F9F',
                                    lineWidth: 1
                                }

                            }]
                    },

                }
            });
            var xValuesPar = [<% 
               for (int i = 0; i < keys1.length; i++) {
                    out.print("'" + keys1[i] + "'");
                    if (i < keys1.length - 1) {
                      out.print(", ");
                    }
                }
            %>];
            var yValuesPar = [<% 
               for (int i = 0; i < value1.length; i++) {
                    out.print("'" + value1[i] + "'");
                    if (i < value1.length - 1) {
                      out.print(", ");
                    }
                }
            %>];
            var barColors = ["#FA8C51", "#EA98DA", "#6CE2D2", "#f5f588", "#EB9898"];
            new Chart("participant", {
                type: "bar",
                data: {
                    labels: xValuesPar,
                    datasets: [{
                            backgroundColor: barColors,
                            data: yValuesPar,
                            label: "aa"
                        }]
                },

                options: {
                    title: {
                        display: true,
                        fontColor: 'white',
                        text: '5 khoá học',
                        fontSize: 35
                    },
                    scales: {
                        xAxes: [{
                                ticks: {
                                    fontColor: '#E8E8E8',
                                    fontSize: 10,

                                },
                                gridLines: {
                                    color: '#A09F9F',
                                    lineWidth: 1
                                },

                            }],
                        yAxes: [{

                                ticks: {
                                    fontColor: '#E8E8E8',
                                    beginAtZero: true,
                                    fontSize: 15,
                                    callback: function (value) {
                                        if (value % 1 === 0) {
                                            return value;
                                        }
                                    }
                                },
                                gridLines: {
                                    color: '#A09F9F',
                                    lineWidth: 1
                                }

                            }]
                    },

                }
            });
            var xValuesRevenue = [<% 
               for (int i = 0; i < key2.length; i++) {
                    out.print("'" + key2[i] + "'");
                    if (i < key2.length - 1) {
                      out.print(", ");
                    }
                }
            %>];
            var yValuesRevenue = [<% 
               for (int i = 0; i < value2.length; i++) {
                    out.print("'" + value2[i] + "'");
                    if (i < value2.length - 1) {
                      out.print(", ");
                    }
                }
            %>];
            new Chart("Revenue", {
                type: "line",
                data: {
                    labels: xValuesRevenue,
                    datasets: [{
                            fill: false,
                            data: yValuesRevenue,
                            borderColor: '#fa8c51',
                            tension: 0.1
                        }]
                },

                options: {
                    title: {
                        display: true,
                        fontColor: 'white',
                        text: 'Doanh thu từ tháng 1 đến',
                        fontSize: 35
                    },
                    scales: {
                        xAxes: [{
                                ticks: {
                                    fontColor: '#E8E8E8',
                                    fontSize: 10

                                },
                                gridLines: {
                                    color: '#A09F9F',
                                    lineWidth: 1
                                },

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

                }
            });
            const sortType = document.getElementById("sortTypePop");
            sortType.addEventListener("change", function () {
                if (sortType.value === "most") {
                    window.location.href = "?sortTypePop=most";
                } else if (sortType.value === "least") {
                    window.location.href = "?sortTypePop=least";

                }

            });
            var paramValue = "${sessionScope.sort_type}";
            for (var i = 0; i < sortType.options.length; i++) {
                if (sortType.options[i].value === paramValue) {
                    sortType.options[i].selected = true;
                    break;
                }
            }
            function dateChange() {
                var dateValue = document.getElementById("DateInput").value;
                console.log(dateValue);
                window.location.href = "?DateInput=" + dateValue;
            }


            const sortTypePar = document.getElementById("sortTypePar");
            sortTypePar.addEventListener("change", function () {
                if (sortTypePar.value === "most") {
                    window.location.href = "?sortTypePar=most";
                } else if (sortTypePar.value === "least") {
                    window.location.href = "?sortTypePar=least";

                }

            });
            var paramValuePar = "${sessionScope.sort_typePar}";
            for (var i = 0; i < sortType.options.length; i++) {
                if (sortTypePar.options[i].value === paramValuePar) {
                    sortTypePar.options[i].selected = true;
                    break;
                }
            }
        </script>

    </body>
</html>
