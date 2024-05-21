<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="brian.Main" %>
    <%@ page import="java.util.Arrays" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%
		Object[][] lopData = Main.lopDAO.getObjectMatrix();
		String[] headers  = new String[] {"Mã Lớp", "Tên Lớp", "Số SV"}; 
	%>
	<jsp:include page="Table.jsp">
		<jsp:param name="headers" value="<%= Arrays.toString(headers) %>" />
		<jsp:param name="tableData" value="<%= Arrays.deepToString(lopData) %>" />
		<jsp:param name="objectType" value="Lop" />
	</jsp:include>
	<div class="crud-buttons">
	        <button onclick="createLopHoc()">Create</button>
	        <button onclick="updateLopHoc()">Update</button>
	        <button onclick="deleteLopHoc()">Delete</button>
	</div>
</body>
</html>