<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>

<html>
<head>
	<title>Home</title>
	<link href="resources/css/main.css" rel="stylesheet" type="text/css" media="screen" />
</head>
<body>
	<%@ include file="/WEB-INF/views/menu.jsp" %>

	<h1>
		Hello world!
	</h1>
	
	<h2>
		This will be the home to Track My Stocks		  
	</h2>

	<P class="serverTime">The time on the server is ${serverTime}. </P>
	
	
</body>
</html>

