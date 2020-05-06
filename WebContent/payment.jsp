<%@page import="model.Payment"%>
<%@page import="model.Payment"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Payment management</title>
<link href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
</head>
<body> 


<div class="container">
		<div class="row">
			<div class="col-6">

				<h1>Payment Management</h1>
				<form id="formPayment" name="formPayment" method="" action="">
					Payment Details: 
					<input id="paydetails" name="paydetails" type="text"
						class="form-control form-control-sm"> <br> 
					Payment Date:
					<input id="date" name="date" type="text"
						class="form-control form-control-sm"> <br> 
					Payment Amount:
					<input id="amount" name="amount" type="text"
						class="form-control form-control-sm"> <br> 
					<input
						id="btnSave" name="btnSave" type="button" value="Save"
						class="btn btn-primary"> 
					<input type="hidden"
						id="hidpidSave" name="hidpidSave" value="">
				</form>

				<div id="alertSuccess" class = "alert alert-success">
					<%
						out.print(session.getAttribute("statusMsg"));
					%>
				</div>

				<div id="alertError" class="alert alert-danger"></div>
				<br>
				<%
					Payment paymentObj = new Payment();
					out.print(paymentObj.readpayment());
				%>
				
				
				
			</div>
		
			
		
		</div>
	</div>


<script src="https://code.jquery.com/jquery-3.5.0.min.js" integrity="sha256-xNzN2a4ltkB44Mc/Jz3pT4iU1cmeR0FkXs4pru/JxaQ=" crossorigin="anonymous"></script>
<script type="text/javascript" src="./Components/Newpayment.js"></script>

</body>
</html>