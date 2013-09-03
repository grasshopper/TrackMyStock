<?xml version="1.0" encoding="ISO-8859-1" ?>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
	<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
	<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
	
<html xmlns="http://www.w3.org/1999/xhtml">

<script type="text/javascript" src="http://code.jquery.com/jquery-1.10.2.js"></script>


<head>
	<title>View Account Stocks</title>
	<link href="resources/css/main.css" rel="stylesheet" type="text/css" media="screen" />
</head>
<body>

	<!-- 
	<TABLE>
		<TR>
			<TD>
				<form:form commandName="AccountOwnedStocks">
					<c:choose>
						<c:when test="${!empty AccountOwnedStocks}">
							<c:out value="AccountOwnedStocks is not empty"></c:out>
							<br />
							
							<c:out value="${fn:length(AccountOwnedStocks)}"></c:out>
							<br />
						</c:when>
						
						<c:otherwise>
							<c:out value="AccountOwnedStocks is empty"></c:out>
							<br />
						</c:otherwise>
					</c:choose>				
				</form:form>
			</TD>

			<TD>
				<form:form commandName="tickerBean">
					<c:choose>
						<c:when test="${!empty tickerBean}">
							<c:out value="tickerBean is not empty"></c:out>
							<br />
						</c:when>
						
						<c:otherwise>
							<c:out value="tickerBean is empty"></c:out>
							<br />
						</c:otherwise>
					</c:choose>				
				</form:form>

			</TD>
		</TR>
	</TABLE>
	 -->	

	<!-- 
	<form:form commandName="AccountOwnedStocks">
		<TABLE>
			<TR>
				<TD>
					<form:checkboxes items="${AccountOwnedStocks}" path="" 
						itemValue="id" itemLabel="name" onchange="alert('onchange');" delimiter="<BR/>" />
				</TD>
			</TR>
		</TABLE>
	</form:form>
	 -->	

	<div class="sectionHeading">
		Select the stocks to view
	</div>

	<TABLE id="ownedStocks">
		<c:set var="rowCnt" value="0"/>
		<c:forEach var="stock" items="${AccountOwnedStocks}">
			<c:set var="name" value="${stock.name}"/>
			<c:set var="id" value="${stock.id}"/>
			<c:set var="checked" value="${stock.viewTicker}"/>
	
			<c:if test="${(rowCnt == 0)}">
				<TR>
			</c:if>

			<TD>
				<INPUT type="checkbox" id="${id}" name="stocksChosen" onchange="stockSelectionChange(this)" >			
					<c:out value="${name}"/>
				</INPUT>
			</TD>

			<c:set var="rowCnt2" value="${rowCnt + 1}"/>
			<c:set var="rowCnt" value="${rowCnt2}"/>
			
			<c:if test="${(rowCnt > 1)}">
				 </TR>
				 
				<c:set var="rowCnt2" value="0"/>
				<c:set var="rowCnt" value="${rowCnt2}"/>
			</c:if>
		</c:forEach>
		
		<c:if test="${(rowCnt == 1)}">
			<TD>&nbsp;</TD>
			</TR>
		</c:if>
		
	</TABLE>



	<P class="serverTime">The time on the server is ${serverTime}.</P>
		
	<div id="stockGraph">
	  <P class="textToBeReplaceByAjaxCall">display the stock graphs here</P>
	</div>
	
</body>
</html>
