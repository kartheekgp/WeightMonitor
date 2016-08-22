<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
<title>Test the app</title>
 
<spring:url value="/resources/core/css/hello.css" var="coreCss" />
<spring:url value="/resources/core/css/bootstrap.min.css" var="bootstrapCss" />
<link href="${bootstrapCss}" rel="stylesheet" />
<link href="${coreCss}" rel="stylesheet" />
</head>
 
 Please click here to test the app :- 
<button onclick="trigger();">Test</button>

<spring:url value="/resources/core/css/hello.js" var="coreJs" />
<spring:url value="/resources/core/css/bootstrap.min.js" var="bootstrapJs" />
 
<script src="${coreJs}"></script>
<script src="${bootstrapJs}"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
 <script>
 //Base weight is fetched from the config file
 function httpGet(theUrl)
 {
     var xmlHttp = new XMLHttpRequest();
     xmlHttp.open( "GET", theUrl, false ); // false for synchronous request
     xmlHttp.send( null );
     return xmlHttp.responseText;
 }
 
 function trigger(){
	 for(var i=100;i<200;i = i + 5){
		 httpGet("http://localhost:8080/WeightWatchers/createWithWeightOnly?value="+i);
	 }
 }
 </script>
</body>
</html>