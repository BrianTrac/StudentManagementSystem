<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>Info Display</title>
<style>
    .result-container {
        background-color: #f9f9f9;
        border: 1px solid #ddd;
        padding: 15px;
        border-radius: 5px;
        font-family: 'Courier New', Courier, monospace;
        margin-top: 20px;
        line-height: 1.6; /* Adjust this value to increase/decrease line spacing */
        max-width: 100%; /* Ensure the container doesn't overflow the page width */
        box-sizing: border-box; /* Include padding and border in the element's total width and height */
    	overflow: hidden;
    	display: flex;         
    	flex-direction: row;    
    	flex-wrap: wrap;  
    }
    .result-container h4 {
        margin-top: 0;
    }
    .result-key {
        font-weight: bold;
        color: #333;
        display: inline-block; /* Allow keys to wrap */
        flex-grow: 0;        /* Don't let keys grow */
    	flex-shrink: 0;      /* Don't let keys shrink */
    }
    .result-value {
	    color: #555;
	    word-wrap: break-word; 
	    overflow-wrap: break-word;
	    word-break: break-all; 
	    display: inline-block; /* Allow values to wrap */
	    flex-grow: 1;        /* Allow values to grow to fill available space */
	}

</style>
</head>
<body>
<% 
    String result = "";
    Map<String, String> rowData = (HashMap<String, String>) request.getAttribute("rowData");
    if (rowData != null) {
        for (Map.Entry<String, String> entry : rowData.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            result += "<span class='result-key'>" + key + ":</span> <span class='result-value'>" + value + "</span>\n";  
        }
    }
%>
<div class="result-container">
    <h4>Thông tin chi tiết</h4>
    <pre><%= result %></pre>
</div>
</body>
</html>
