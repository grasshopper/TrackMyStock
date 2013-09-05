<?xml version="1.0" encoding="ISO-8859-1" ?>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
	<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
		
<html xmlns="http://www.w3.org/1999/xhtml">

<script type="text/javascript" src="resources/javascript/jquery/2.0.3/jquery.js" />
<script src="http://code.highcharts.com/highcharts.js"></script>


<script type="text/javascript">
	function accountSelectionChange(object) {
		console.log("Starting accountSelectionChange");
		console.log(object.options[object.selectedIndex].value);
		console.log(object.options[object.selectedIndex].text);
		
		$.ajax({
			type : 'POST',
			url : 'viewAccountStocks.html', 
			data : 'accountId=' + object.options[object.selectedIndex].value,
			dataType: 'html',				
			success: function(msg) { 
                // alert( "Data retrieved: " + msg );
                
				document.getElementById("myDiv").innerHTML = msg;
            },
            error: function (jqxhr, status, errorThrown) {
                alert("Failure, Unable to recieve content")
                alert(jqxhr.responseText);
            }
		});
	}

	
	function stockSelectionChange(object) {
		console.log("Starting  stockSelectionChange");
		var atLeastOneIsChecked = $('input[name="stocksChosen"]:checked').length > 0;
		console.log("Is there at least one checked stock? " + atLeastOneIsChecked);
		
		var checkValues = $('input[name=stocksChosen]:checked').map(function() {
		    return $(this).attr('id');
		}).get();
		
		console.log("checkValues: " + checkValues);
		
		// a second way of getting the checked stocks
		/*
		var boxes = $('input[name="stocksChosen"]:checked');
		console.log("Number of checked stock: " + $(boxes).size());
		$(boxes).each(function(){
			console.log("Stock is checked: " + this.id);
		});
		
		console.log("Checked boxes: " + boxes);
		*/
		
		
		// make the ajax call to display the graph for the selected stocks		
		$.ajax({
			type : 'POST',
			url : 'viewStocksMovingAverage.html', 
			data : "stockIds=" + checkValues,
			dataType: 'html',				
			success: function(msg) { 
				document.getElementById("stockGraph").innerHTML = msg;
				
				drawSampleChart();
            },
            error: function (jqxhr, status, errorThrown) {
                alert("Failure, Unable to recieve content")
                alert(jqxhr.responseText);
            }
		});
	}
	
	/*
	 * Handle user selection from the Moving Average Days drop-down listbox 
	 */
	function movingDaySelectionChange(movingAvgDaysObj) {
		console.log("Starting movingDaySelectionChange");
		console.log("Moving Average Days value: " + movingAvgDaysObj.options[movingAvgDaysObj.selectedIndex].value);
		console.log("Moving Average Days text: " + movingAvgDaysObj.options[movingAvgDaysObj.selectedIndex].text);
		
		// make the ajax call to display the graph for the selected stocks		
		$.ajax({
			type : 'POST',
			url : 'updateStocksMovingAverage.html', 
			data : 'movingAvgDaysId=' + movingAvgDaysObj.options[movingAvgDaysObj.selectedIndex].value,
			dataType: 'html',				
			success: function(msg) { 
				document.getElementById("stockGraph").innerHTML = msg;
				
				drawSampleChart();
            },
            error: function (jqxhr, status, errorThrown) {
                alert("Failure, Unable to recieve content")
                alert(jqxhr.responseText);
            }
		});
	}

	
	function drawSampleChart() {
		console.log("Starting drawSampleChart");
	    $('#container').highcharts({
	        chart: {
	            type: 'bar'
	        },
	        title: {
	            text: 'Fruit Consumption'
	        },
	        xAxis: {
	            categories: ['Apples', 'Bananas', 'Oranges']
	        },
	        yAxis: {
	            title: {
	                text: 'Fruit eaten'
	            }
	        },
	        series: [{
	            name: 'Jane',
	            data: [1, 0, 4]
	        }, {
	            name: 'John',
	            data: [5, 7, 3]
	        }]
	    });
	}
	

	
	function WorkedaccountSelectionChange(object) {
		console.log(object.optsions[object.selectedIndex].value);
		console.log(object.options[object.selectedIndex].text);
		
		
		// configure the XMLHttpRequest
		var xmlhttp;
		if (window.XMLHttpRequest) {// code for IE7+, Firefox, Chrome, Opera, Safari
			xmlhttp = new XMLHttpRequest();
		} else {// code for IE6, IE5
			xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
		}
		
		
		// setup the handling of the return value from the server call
		xmlhttp.onreadystatechange = function() {
			if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
				document.getElementById("myDiv").innerHTML = xmlhttp.responseText;
			}
		}
		
		xmlhttp.open("GET", "viewAccountStocks.html", true);
		xmlhttp.send();
	}
	
	function OldAccountSelectionChange(object) {
		console.log(object.options[object.selectedIndex].value);
		console.log(object.options[object.selectedIndex].text);

		// configure the XMLHttpRequest
		var xmlhttp;
		if (window.XMLHttpRequest) {// code for IE7+, Firefox, Chrome, Opera, Safari
			xmlhttp = new XMLHttpRequest();
		} else {// code for IE6, IE5
			xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
		}
		
		// setup the handling of the return value from the server call
		xmlhttp.onreadystatechange = function() {
			if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
				document.getElementById("myDiv").innerHTML = xmlhttp.responseText;
			}
		}
		
		// make the server call
//		xmlhttp.open("POST", "viewAccountStocks.html", true);
//		xmlhttp.setRequestHeader("Content-type","application/x-www-form-urlencoded");
//		xmlhttp.send("fname=Henry&lname=Ford");
		
		xmlhttp.open("GET", "viewAccountStocks.html", true);
		xmlhttp.send();
	}

</script>

<head>
	<title>Select Account</title>
	<link href="resources/css/main.css" rel="stylesheet" type="text/css" media="screen" />
	<link href="resources/css/menu.css" rel="stylesheet" type="text/css" media="screen" />
</head>
<body>
	
	<%@ include file="/WEB-INF/views/menu.jsp" %>

	<BR />
	
	<div class="sectionHeading">
		Select an Account
	</div>

	
	<select id="selectAccount" class="dropdownlist" name="selectedAccount" onchange="accountSelectionChange(this)">
		<option value="" disabled selected style="display:none;">Select an Account</option>
		<c:forEach var="account" items="${PrimaryAccounts}">
			<option value = "${account.id}">
				<c:out value="${account.name}"></c:out>
			</option> 
		</c:forEach>
	</select>

	<P class="serverTime">The time on the server is ${serverTime}.</P>

	<div id="myDiv">
		<P class="textToBeReplaceByAjaxCall">display the stock options here</P>
	</div>

</body>
</html>
