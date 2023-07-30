<%-- 
    Document   : PackageSubcribe.jsp
    Created on : Jul 8, 2023, 12:37:01 AM
    Author     : FPT
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <meta name="description" content="">
        <meta name="author" content="Template Mo">
        <link href="https://fonts.googleapis.com/css?family=Poppins:100,200,300,400,500,600,700,800,900" rel="stylesheet">

        <title>Education Template - Meeting Detail Page</title>

        <!-- Bootstrap core CSS -->
        <link href="vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>

        <!-- Additional CSS Files -->
        <link rel="stylesheet" href="assets/css/fontawesome.css">
        <link rel="stylesheet" href="assets/css/templatemo-edu-meeting.css">
        <link rel="stylesheet" href="assets/css/owl.css">
        <link rel="stylesheet" href="assets/css/lightbox.css">
        <style>
            .pricePackageWrapper{
                display: flex;
                justify-content: space-around
            }

            .pricePackageItem{
                background-color: white;
                /*                background-image: linear-gradient(315deg, #bdd4e7 0%, #8693ab 74%);*/
                color: #0a3d51;
                padding: 10px 5px;
                border-radius: 20px
            }
            .packageInfo{
                min-height: 210px
            }
            .registerbtn button{
                padding: 8px 15px;
                border-radius: 25px;
                background-color: #892020;
                color: #c8c8c8;
                border: none
            }
            .noti{
                margin-top: 50px !important;
            }
        </style>
    </head>

    <body>
        <jsp:include page="header.jsp"/>
        <section class="heading-page header-text">
            <c:if test="${requestScope.currentSubscription == null}">
                <h4 style="color: white; margin-bottom: 50px; font-size: 200%">Đăng kí góp vip</h4>
                <div class="container">
                    <div class="row">
                        <div class="pricePackageWrapper" style="padding: 10px;">

                            <c:forEach items="${requestScope.packageList}" var="p">
                                <c:if test="${p.isPack_status() == true}">
                                    <div class="pricePackageItem" style="border: 1px solid black; width: ${Math.floor(100/requestScope.packageList.size())-5}%">
                                        <div class="packageInfo">
                                            <h4>${p.getPackage_name()}</h4>
                                            <c:if test="${p.getDuration() <= 10}">
                                                <h4>Thời hạn: ${p.getDuration()} tháng</h4>
                                            </c:if>
                                            <c:if test="${p.getDuration() == 100000}">
                                                <h4>Thời hạn: vô hạn</h4>
                                            </c:if>
                                            <h4>Giá cả: ${p.getPriceFormated()}</h4>
                                            <p>${p.getDescription()}</p>
                                        </div>
                                        <div class="registerbtn">
                                            <button onclick="Subcribe(${p.getPackage_id()}, ${p.getPrice()}, ${p.getDuration()})">Đăng ký</button>

                                        </div>
                                    </div>
                                </c:if>
                            </c:forEach>

                        </div>
                    </div>
                    <div class="row noti">
                        <h5 style="color: #bea340">*Bạn vẫn có thể học các khoá học của chúng tôi mà không cần đăng kí thành viên nhưng sẽ không thể sử dụng một số tính năng như làm quiz, theo dõi tiến độ của bản thân, ...</h5>
                    </div>
                </div>
            </c:if>
            <c:if test="${requestScope.currentSubscription != null}">
                <h4 style="color: white; margin-bottom: 50px; font-size: 200%">Gói hiện tại</h4>
                <div class="container">
                    <div class="row">
                        <div class="pricePackageWrapper" style="padding: 10px;">
                            <div class="pricePackageItem" style="border: 1px solid black; width: ${Math.floor(100/requestScope.packageList.size())-5}%">
                                <div class="packageInfo">
                                    <h4>${requestScope.currentSubscription.getCurrentPackage().getPackage_name()}</h4>
                                    <p>Ngày đăng kí: ${requestScope.currentSubscription.getReg_time()}</p>
                                    <c:if test="${requestScope.currentSubscription.getReg_time()==requestScope.currentSubscription.getExpireDate()}">
                                        <p>Thời hạn: vô hạn</p>
                                    </c:if>
                                    <c:if test="${requestScope.currentSubscription.getReg_time()!=requestScope.currentSubscription.getExpireDate()}">
                                        <p>Thời hạn: ${requestScope.currentSubscription.getExpireDate()}</p>
                                    </c:if>

                                    <p>${requestScope.currentSubscription.getCurrentPackage().getDescription()}</p>
                                </div>

                            </div>
                        </div>
                    </div>

                </div>

            </c:if>
            <div class="modal" id="myModal">
                <div class="modal-dialog modal-dialog-centered">
                    <div class="modal-content">

                        <!-- Modal Header -->
                        <div class="modal-header">
                            <h6 style="color: black" class="modal-title">Bạn không đủ tiền trong ví để thực hiện giao dịch này</h6>

                        </div>

                        <!-- Modal body -->
                        <div class="modal-body">
                            Nạp thêm tiền tại <a href="Deposit">đây</a>
                        </div>

                        <!-- Modal footer -->
                        <div class="modal-footer">
                            <button type="button" class="btn btn-danger" data-bs-dismiss="modal">Close</button>
                        </div>

                    </div>
                </div>
            </div>
        </section>



        <jsp:include page="footer.jsp"/>
        <script>

            function Subcribe(packageId, packagePrice, packageDuration) {
                var wallet = ${requestScope.currUser.getUserWallet()};
                if (wallet < packagePrice) {
                    $('#myModal').modal('show');
                } else {
                    var userId = ${requestScope.currUser.getUserId()}
                    var url = "PricePackageSubcription?userId=" + encodeURIComponent(userId) + "&packageId=" + encodeURIComponent(packageId) + "&price=" + encodeURIComponent(packagePrice) + "&duration=" + encodeURIComponent(packageDuration);
                    var xmlHttp = new XMLHttpRequest();
                    xmlHttp.open("POST", url, true);
                    xmlHttp.onreadystatechange = function () {
                        if (xmlHttp.readyState === 4 && xmlHttp.status === 200) {
                            console.log(xmlHttp.responseText);
                            window.location.href = "PricePackageSubcription";
                        }
                    };
                    xmlHttp.send();

                }
            }
        </script>
    </body>
</html>
