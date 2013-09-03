<?xml version="1.0" encoding="ISO-8859-1" ?>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
	<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
	<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
	
<html xmlns="http://www.w3.org/1999/xhtml">

<head>
	<script type="text/javascript" src="http://code.jquery.com/jquery-1.10.2.js"></script>

	<title>Stock Graph</title>
	<link href="resources/css/main.css" rel="stylesheet" type="text/css" media="screen" />
</head>
<body>

	<div class="sectionHeading">
		Moving Average
	</div>

	<select id="selectMovingDayAverage" class="dropdownlist" name="selectedMovingDayAverage" onchange="movingDaySelectionChange(this)">
		<option value="" selected id="${MovingAverageOptions.selectedAverage.id}" />
		<c:forEach var="average" items="${MovingAverageOptions.movingAverageOptions}">
			<c:set var="selected" value=""/>
			<c:if test="${(MovingAverageOptions.selectedAverage.id == average.id)}">
				<c:set var="selected" value="selected"/>
			</c:if>
			<option value="${average.id}" ${selected}>
				<c:out value="${average.displayValue}" />
			</option>
		</c:forEach>
	</select>

	<div id="container" style="width:100%; height:400px;"></div>

	<P class="serverTime">The time on the server is ${serverTime}.</P>
	
</body>
</html>
