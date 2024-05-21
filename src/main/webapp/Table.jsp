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
</head>
<body>
<%
    // Get headers and data from the request parameters
    String headersStr = request.getParameter("headers");
    String dataStr = request.getParameter("tableData");
    
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
            <% for (Object[] rowData : data) { System.out.println(rowData.length);%>
                <tr>
                     <% for (int i = 0; i < rowData.length; i++) { 
                        String cellData = (rowData[i] != null && !"null".equals(rowData[i].toString())) ? rowData[i].toString() : "";
                    %>
                        <td><%= cellData %></td>
                    <% } %>
                </tr>
            <% } %>
        </tbody>
    </table>
<%
    } else {
        // Print error message if headers or data are not available
        out.println("No data available to display the table.");
    }
%>

 <script>
	 	function handleRowClick(row) {
		    const rowData = Array.from($(row).find('td'), td => td.textContent);
		    const headers = Array.from($('#dynamicTable th'), th => th.textContent);
	
		    // Build Query String Manually with Enhanced Encoding
		    let queryString = '';
		    for (let i = 0; i < rowData.length; i++) {
		        const key = encodeURIComponent(headers[i]);
		        const value = encodeURIComponent(rowData[i]);
		        queryString += (i > 0 ? '&' : '') + key + '=' + value;
		    }
	
		    fetch('UpdateInfoServlet?' + queryString, {
		        method: 'GET'
		    })
		    .then(response => {
		        if (!response.ok) {
		            throw new Error('Failed to retrieve data');
		        }
		        return response.text();
		    })
		    .then(responseText => {
		        $('.info-container').html(responseText);
		    })
		    .catch(error => {
		        alert(error.message);
		    });
		}


        $(document).ready(function(){
        	<%System.out.println("Hello2");%>
            // Sorting functionality
            $('th.sortable').click(function(){
                let table = $(this).parents('table').eq(0)
                let rows = table.find('tr:gt(0)').toArray().sort(comparer($(this).index()))
                this.asc = !this.asc
                if (!this.asc){rows = rows.reverse()}
                for (let i = 0; i < rows.length; i++){table.append(rows[i])}
                $('th').removeClass('asc desc');
                $(this).addClass(this.asc ? 'asc' : 'desc');
            })
            
            function comparer(index) {
                return function(a, b) {
                    let valA = getCellValue(a, index), valB = getCellValue(b, index)
                    return $.isNumeric(valA) && $.isNumeric(valB) ? valA - valB : valA.localeCompare(valB)
                }
            }
            
            function getCellValue(row, index){ 
            	return $(row).children('td').eq(index).text() 
            }

            // Search functionality
            $("#searchInput").on("keyup", function() {
                let value = $(this).val().toLowerCase();
                $("#dynamicTable tbody tr").filter(function() {
                    $(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
                });
            });
            
            // Row clicked
            $('#dynamicTable tbody tr').click(function() {
                handleRowClick(this);
            });
        });
    </script>
</body>
</html>