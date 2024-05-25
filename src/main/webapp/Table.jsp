<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>

<!DOCTYPE html>
<html lang="vi">
<head>
<meta charset="UTF-8">
<title>Table</title>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css">
<link rel="stylesheet" href="tablestyle.css">
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script src="reloadTableData.js"></script> 
</head>
<body>

<%	
    String objectType = (String) request.getAttribute("objectType");
	String headersStr = Arrays.toString((String[]) request.getAttribute("headers"));
	String dataStr = Arrays.deepToString((Object[][]) request.getAttribute("tableData"));
	
	
	// Ensure data is processed if it is not null
	if (headersStr != null && dataStr != null) {
	    // Parse headers
	    String[] headers = headersStr.substring(1, headersStr.length() - 1).split(", ");
	    
	    // Parse data rows
	    dataStr = dataStr.replaceAll("\\[\\[", "").replaceAll("\\]\\]", "");
	    String[] dataRows = dataStr.split("\\], \\[");
	    Object[][] data = new Object[dataRows.length][];
	    
	    for (int i = 0; i < dataRows.length; i++) {
	        String[] rowData = dataRows[i].split(", ");
	        data[i] = rowData;
	    }
		
	    
	    System.out.println("In Table.jsp");
	    for (String header : headers) {
	    	System.out.println(header);
	    }
	    for (Object[] d : data) {
	    	for (Object s: d) {
	    		System.out.println(s.toString());
	    	}
	    }
%>
    <table id="dynamicTable">
        <thead>
            <tr>
                <% for (String header : headers) { %>
                    <th class="sortable"><%= header %></th>
                <% } %>
            </tr>
        </thead>
        <tbody>
            <% 
            if (data != null && data.length > 0) {
	            for (Object[] rowData : data) { %>
	                <tr>
	                     <% for (int i = 0; i < rowData.length; i++) { 
	                        String cellData = (rowData[i]!= "[]" && rowData[i] != null && !"null".equals(rowData[i].toString())) ? rowData[i].toString() : "";
	                    %>
	                        <td><%= cellData %></td>
	                    <% } %>
	                </tr>
	            <% }
	        }%>
        </tbody>
    </table>
<%
    } else {
        // Print error message if headers or data are not available
        out.println("No data available to display the table.");
    }
%>
</body>
</html>