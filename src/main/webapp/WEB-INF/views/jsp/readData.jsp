<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
<title>Data - read</title>
 
<spring:url value="/resources/core/css/hello.css" var="coreCss" />
<spring:url value="/resources/core/css/bootstrap.min.css" var="bootstrapCss" />
<link href="${bootstrapCss}" rel="stylesheet" />
<link href="${coreCss}" rel="stylesheet" />
</head>
<p align="center">Metrics data are as follows :- </p>
<table align = "center" border="1">
<th>
<tr><td>Base Weight</td><td>Current Weight</td><td>Date</td></tr>
<th>
<tbody>
<c:forEach items="${dataList}" var="item">
<tr><td>${item.baseWeight}</td><td>${item.currentWeight}</td><td>${item.timeStamp}</td></tr>
</c:forEach>
</tbody>
<table>
<spring:url value="/resources/core/css/hello.js" var="coreJs" />
<spring:url value="/resources/core/css/bootstrap.min.js" var="bootstrapJs" />
 
<script src="${coreJs}"></script>
<script src="${bootstrapJs}"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
 
</body>
</html>