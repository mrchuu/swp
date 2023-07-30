<%-- 
    Document   : AdminReportedMessages
    Created on : Jul 24, 2023, 10:44:13 AM
    Author     : FPT
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <style>
            .From h6{
                color: white;
                text-align: left
            }
            .To h6{
                color: white;
                text-align: right
            }
            .To p{
                text-align: right
            }
            .From p{
                text-align: left
            }
            .Item{
                border-bottom: white solid 1px;
                padding: 20px 5px
            }
            .content{
                text-align: left
            }
            .nav-item{
                margin: 30px auto !important;
            }
            .active a{
                color: #e3a31e
            }
        </style>
    </head>
    <body>
        <jsp:include page="header.jsp"/>

        <!-- Cach su dung template: dung 2 the include de lay header va footer, sau do pass section o be duoi vao 
        giua roi chen noi dug trang vao phan ben duoi -->
        <section class="heading-page header-text">
            <div class="container-fluid">
                <div class="row" style="height: 600px">
                    <div class="col-2" style="background-color: rgb(40,45,49, 0.6); ">
                        <div class="nav-item">
                            <a href="AdminDashBoard">Admin DashBoard</a>
                        </div>
                        <div class="nav-item active">
                            <a href="ReportedMessages">Reported Messages</a>
                        </div>
                        <div class="nav-item">
                            <a href="AdminUserList">UserList</a>
                        </div>
                        <div class="nav-item">
                            <a href="">Pending Courses</a>
                        </div>

                    </div>
                    <div class="col-10" style="background-color: rgb(0,0,0, 0.3);overflow-y: auto">
                        <div class="row">
                            <h4 style="color: white">Reported Messages</h4>                      
                        </div>
                        <div class="row">
                            <c:forEach items="${requestScope.displayList}" var="message">
                                <div class="Item">
                                    <div class="row" style="margin-bottom: 10px">
                                        <div class="From col-6">
                                            <h6>Từ: ${message.getSender().getFullName()} </h6>
                                            <p style="color: #8a8a8a; padding-left: 33px" >${message.getSender().getUserEmail()}</p>
                                        </div>
                                        <div class="To col-6">
                                            <h6>Người báo cáo: ${message.getReceiver().getFullName()} </h6>
                                            <p style="color: #8a8a8a; padding-left: 33px" >${message.getReceiver().getUserEmail()}</p>
                                        </div>
                                    </div>
                                    <div class="content">
                                        <h6 style="display: inline-block">Nội dung: </h6>
                                        <p style="color: #8a8a8a; padding-left: 33px; display: inline-block;width: 85%" >${message.getContent()}</p>
                                    </div>
                                </div>
                            </c:forEach>

                        </div>
                    </div>
                </div>
            </div>
        </section>

        <jsp:include page="footer.jsp"/>
    </body>
</html>
