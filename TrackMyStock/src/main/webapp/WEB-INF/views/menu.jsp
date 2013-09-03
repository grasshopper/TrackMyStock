<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<html xmlns="http://www.w3.org/1999/xhtml">

<head>
	<title>Select Account</title>
	<link href="resources/css/main.css" rel="stylesheet" type="text/css" media="screen" />
	<link rel="stylesheet" href="resources/css/menu.css" type="text/css" media="screen">
</head>
<body>

	
	<nav id="menu-bar">
		<P class="textToBeReplaceByAjaxCall">
			menuState: <c:out value="${menuState}" />		
		</P>
		<br />
	
		<ul id="fancyNav">
		
			<!-- Home menu -->		
			<c:choose>
				<c:when test="${menuState=='HOME'}">          
					<li id="HOME_MENU" class="selected">
						<a href="#" id="selected">
							<span>
								<img src="resources/images/home.png" /> Home
							</span>
						</a>
					</li>
				</c:when>
				<c:otherwise>
					<li id="HOME_MENU">
						<a href="<c:url value='/home.html'/>">
							<span>
								<img src="resources/images/home.png" /> Home
							</span>
						</a>
					</li>
				</c:otherwise>
			</c:choose>
		
			<!-- Account menu -->		
			<c:choose>
				<c:when test="${menuState=='ACCOUNT'}">          
					<li id="ACCOUNT_MENU" class="selected">
						<a href="#" id="selected">
							<span>
								<img src="resources/images/piechart.png" /> Account
							</span>
						</a>
					</li>
				</c:when>
				<c:otherwise>
					<li id="ACCOUNT_MENU">
						<a href="<c:url value='/selectAccount.html'/>"> 
							<span>
								<img src="resources/images/piechart.png" /> Account
							</span>
						</a>
					</li>
				</c:otherwise>
			</c:choose>

			<!-- Buy menu -->		
			<c:choose>
				<c:when test="${menuState=='BUY'}">          
					<li id="BUY_MENU" class="selected">
						<a href="#" id="selected">
							<span>
								<img src="resources/images/top3.png" /> Buy
							</span>
						</a>
					</li>
				</c:when>
				<c:otherwise>
					<li id="BUY_MENU">
						<a href="<c:url value='/buyStock.html'/>"> 
							<span>
								<img src="resources/images/top3.png" /> Buy
							</span>
						</a>
					</li>
				</c:otherwise>
			</c:choose>
			
			
						
			<!-- Sell menu -->		
			<c:choose>
				<c:when test="${menuState=='SELL'}">          
					<li id="SELL_MENU" class="selected">
						<a href="#" id="selected">
							<span>
								<img src="resources/images/top3.png" /> Sell
							</span>
						</a>
					</li>
				</c:when>
				<c:otherwise>
					<li id="SELL_MENU">
						<a href="<c:url value='/sellStock.html'/>"> 
							<span>
								<img src="resources/images/top3.png" /> Sell
							</span>
						</a>
					</li>
				</c:otherwise>
			</c:choose>
			
			
			<li id="RESEARCH_MENU" class="selected" >
				<a href="#"><img src="resources/images/top4.png" /> Research</a>
			</li>
			
			<li id="SANDBOX_MENU">
				<a href="#"><img src="resources/images/top5.png" /> Sandbox</a>
				<div class="subs">
					<div class="col">
						<ul>
							<li><a href="#"><img src="resources/images/bub.png" /> Link 1</a></li>
							<li><a href="#"><img src="resources/images/bub.png" /> Link 2</a></li>
							<li><a href="#"><img src="resources/images/bub.png" /> Link 3</a></li>
							<li><a href="#"><img src="resources/images/bub.png" /> Link 4</a></li>
							<li><a href="#"><span><img src="resources/images/top3.png" />
										Sublinks</span></a>
								<div class="subs">
									<div class="col">
										<ul>
											<li><a href="#"><img src="resources/images/bub.png" /> Link
													41</a></li>
											<li><a href="#"><img src="resources/images/bub.png" /> Link
													42</a></li>
											<li><a href="#"><img src="resources/images/bub.png" /> Link
													43</a></li>
											<li><a href="#"><img src="resources/images/bub.png" /> Link
													44</a></li>
											<li><a href="#"><img src="resources/images/bub.png" /> Link
													45</a></li>
										</ul>
									</div>
									<div class="col">
										<ul>
											<li><a href="#"><img src="resources/images/bub.png" /> Link
													46</a></li>
											<li><a href="#"><img src="resources/images/bub.png" /> Link
													47</a></li>
											<li><a href="#"><img src="resources/images/bub.png" /> Link
													48</a></li>
											<li><a href="#"><img src="resources/images/bub.png" /> Link
													49</a></li>
										</ul>
									</div>
								</div></li>
						</ul>
					</div>
					<div class="col">
						<ul>
							<li><a href="#"><img src="resources/images/bub.png" /> Link 6</a></li>
							<li><a href="#"><img src="resources/images/bub.png" /> Link 7</a></li>
							<li><a href="#"><img src="resources/images/bub.png" /> Link 8</a></li>
							<li><a href="#"><img src="resources/images/bub.png" /> Link 9</a></li>
							<li><a href="#"><img src="resources/images/bub.png" /> Link 10</a></li>
						</ul>
					</div>
				</div>
			</li>
		</ul>
	</nav>
	
	<br />
	<br />
	<br />

</body>
</html>
