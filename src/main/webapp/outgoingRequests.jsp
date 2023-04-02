<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.technology.model.User" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Raleway:ital,wght@0,300;0,400;0,500;0,600;0,700;1,800&display=swap">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css">
    <link rel="stylesheet" href="css/style.css">
    <title>Outgoing Requests</title>
</head>
<body>
<div class="container p-30">
    <div class="row">
        <jsp:include page="header.jsp"/>
        <div class="col-md-12 main-datatable">
            <div class="card_body">
                <div class="row d-flex">
                    <div class="col-sm-12 add_flex">
                        <div class="form-group searchInput">
                            <input type="search" class="form-control" id="filterbox" name="search" placeholder="">
                        </div>
                    </div>
                </div>
                <div class="overflow-x">
                    <table
                            style="width: 100%;"
                            id="filtertable"
                            aria-description="users"
                            class="table cust-datatable dataTable no-footer"
                    >
                        <thead>
                        <tr>
                            <th style="min-width: 50px;">ID</th>
                            <th style="min-width: 150px;">Name</th>
                            <th style="min-width: 150px;"></th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${outgoingRequests}" var="user">
                            <tr>
                                <td>
                                    <c:out value="${user.id}"/>
                                </td>
                                <td>
                                    <c:out value="${user.name}"/>
                                </td>
                                <td>
                                    <form action="outgoingRequests?requestFriendId=${user.getId()}" method="post">
                                        <button>Delete request</button>
                                    </form>
                                </td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>