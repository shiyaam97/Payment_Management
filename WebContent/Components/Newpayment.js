$(document).ready(function() 
		{  
	   
		$("#alertSuccess").hide();  
        $("#alertError").hide(); 
        
		});

//save-------------------
$(document).on("click", "#btnSave", function(event) 
		{  	
			$("#alertSuccess").text("");  
			$("#alertSuccess").hide();  
			$("#alertError").text("");  
			$("#alertError").hide(); 
			
			
			var status = validatePaymentForm();  
			if (status != true)  
			{   
				$("#alertError").text(status);   
				$("#alertError").show();   
				return;  
			} 
			
			//If valid-------------------
			
			var type = ($("#hidpidSave").val() == "") ? "POST" : "PUT"; 
			
			$.ajax( 
			{  
				url : "PaymentAPI",  
				type : type,  
				data : $("#formPayment").serialize(),  
				dataType : "text",  
				complete : function(response, status)  
				{   
					onPaymentSaveComplete(response.responseText, status);
				} 
			
		}); 
}); 
		
function onPaymentSaveComplete(response, status) 
{  
	if (status == "success")  
	{   location.reload();
		//setTimeout(location.reload.bind(location), 5000)
		var resultSet = JSON.parse(response); 
		console.log(resultSet)

		if (resultSet.status.trim() == "success")   
		{    
			$("#alertSuccess").text("Successfully saved.");    
			$("#alertSuccess").show(); 
			//location.reload();
			$("#divPaymentsGrid").html(resultSet.data);
			//location.reload();
			
			
			console.log(resultSet);
		} else if (resultSet.status.trim() == "error")   
		{    
			$("#alertError").text(resultSet.data);    
			$("#alertError").show();   
		} 

		} else if (status == "error")  
		{   
			$("#alertError").text("Error while saving.");   
			$("#alertError").show();  
		} else  
		{   
			$("#alertError").text("Unknown error while saving..");   
			$("#alertError").show();  
		} 

		$("#hidpidSave").val("");  
		$("#formPayment")[0].reset(); 
		
}


$(document).on("click", ".btnUpdate", function(event) 
		{     
			$("#hidpidSave").val($(this).closest("tr").find('#hidpidUpdate').val());     
			$("#paydetails").val($(this).closest("tr").find('td:eq(0)').text());     
			$("#date").val($(this).closest("tr").find('td:eq(1)').text());     
			$("#amount").val($(this).closest("tr").find('td:eq(2)').text());      
		}); 



$(document).on("click", ".btnRemove", function(event) 
{  
	$.ajax(  
	{   
		url : "PaymentAPI",   
		type : "DELETE",   
		data : "pid=" + $(this).data("pid"),   
		dataType : "text",   
		complete : function(response, status)   
		{    
			onPaymentDeleteComplete(response.responseText, status);   
		}  
	}); 
}); 


function onPaymentDeleteComplete(response, status) 
{  
	if (status == "success")  
	{   
		//var resultSet = JSON.parse(response); 

		if (status.trim() == "success")   
		{    
			$("#alertSuccess").text("Successfully deleted.");    
			$("#alertSuccess").show(); 
			//setTimeout(location.reload.bind(location), 10000);
			location.reload();
		//	$("#divPaymentsGrid").html(resultSet.data);   
			} else if (status.trim() == "error")   
			{    
				$("#alertError").text(status);    
				$("#alertError").show();   
			} 

			} else if (status == "error")  
			{   
				$("#alertError").text("Error while deleting.");   
				$("#alertError").show();  
			} else  
			{   
				$("#alertError").text("Unknown error while deleting..");   
				$("#alertError").show();  
			} 
	} 



function validatePaymentForm() 
{  
	// NAME  
	if ($("#paydetails").val().trim() == "")  
	{   
		return "Insert the payment details.";   
	}

	 
	 // PASSWORD  
	if ($("#date").val().trim() == "")  
	{   
		return "Insert the today date.";  
	}
	
	// Payment'S REPORT
	if ($("#amount").val().trim() == "")  
	{   
		return "Insert  amount";  
	} 
	 
	
	 
	 return true;
	
}