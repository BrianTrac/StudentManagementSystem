<%@page import="java.io.Console"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %> <!DOCTYPE html>
<html>
<head>
    <title>Create/Update Form</title>
<style>
    /* Styling for the form */
    .create-update-form-container {
        max-width: 400px;
        margin: 20px auto;
        padding: 20px;
        border: 1px solid #ccc;
        border-radius: 5px;
    }

    .create-update-form-container div {
        margin-bottom: 15px;
    }

    .create-update-form-container label {
        display: block;
        margin-bottom: 5px;
        font-weight: bold;
    }

    .create-update-form-container input[type="text"] {
        width: 100%;
        padding: 10px;
        border:1px solid #ccc;
        border-radius: 3px;
        box-sizing: border-box;
    }

    .create-update-form-container button {
        background-color: #007bff;
        color: white;
        padding: 10px 15px; none;
        border-radius: 3px;
        cursor: pointer;
    }

    .create-update-form-container button:hover {
        background-color: #0056b3;
    }
</style>

<script>
    function cancelOperation(objectType) {
        window.location.href = 'MainScreen?item=' + encodeURIComponent(objectType);
    }
</script>
</head>
<body>
	<div class="create-update-form-container">
		<% 
        String errorMessage = (String) request.getAttribute("errorMessage");
        if (errorMessage != null) { %>
            <div id="errorMessage" style="color: red;"><%= errorMessage %></div>
        <% } %>
        
	    <% 
	        String action = request.getParameter("action") != null ? request.getParameter("action") : "create"; 
	        String objectType = request.getParameter("objectType"); 
	        request.setAttribute("action", action);
	        request.setAttribute("objecType", objectType);
	    %>
	
	    <h2><%= action.equals("create") ? "Tạo" : "Cập nhật" %> <%= objectType %></h2>
	    <form id="dataForm" action="CreateUpdateFormController" method="POST">
	        <input type="hidden" name="action" value="<%= action %>">
	        <input type="hidden" name="objectType" value="<%= objectType %>">
	
	        <% 
	           
	         // Get the headers from the query parameters and split into an array
	            String headersStr = request.getParameter("headers");
	            String[] headers = headersStr != null ? headersStr.split(",") : new String[0]; 
	
	            // Create a map to store the row data
	            Map<String, String> rowData = new HashMap<>();
	
	            // Iterate over the headers to get corresponding values
	           	for (int i = 0; i < headers.length; i++) {
	           		System.out.print("Test headers.length in createUpdateForm.jsp");
                    String header = headers[i];
                    String fieldName = header.replaceAll("\\s+", " ").trim(); 
                    String fieldValue = ""; 
                    if (action.equals("update")) {
                    	fieldValue = request.getParameter(fieldName); 
                    	rowData.put(fieldName, fieldValue);
                    }
                    else {
                    	fieldValue = "";
                    }
	                
	                System.out.println(fieldName);
	                System.out.println(fieldValue);
	            
	        %>
	            <div>
	                <label for="<%= fieldName %>"><%= fieldName %>:</label>
	                <input type="text" id="<%= fieldName %>" name="<%= fieldName %>" value="<%= fieldValue %>" <%= action.equals("update") && i == 0 ? "readonly" : ""%>> 
	            </div>
	        <% 
	            }
	            System.out.println("In createUpdateForm.jsp");
	            System.out.println(rowData);
	            
	         	// Set attributes for the rest of the JSP to use
	            request.setAttribute("headers", headers);
	         	if (action.equals("update")) {
	         		request.setAttribute("existingData", rowData);
	         	}
	        %>
	
	        <button type="submit">Submit</button>
	    <%--    
	     <button type="button" onclick="cancelOperation('<%= objectType %>')">Cancel</button>
	   --%>
	   		<button type="button" onclick="history.back()">Cancel</button>
	  
	   
	   </form>
	</div>
    <script>
        // JavaScript for form validation or other interactions can go here
    </script>
</body>
</html>

