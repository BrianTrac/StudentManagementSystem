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
		Object[][] monHocData = Main.monHocDAO.getObjectMatrix();
		String[] headers  = new String[] {"Mã Môn Học", "Tên Môn Học", "Số TC"}; 
	%>
	<jsp:include page="Table.jsp">
		<jsp:param name="headers" value="<%= Arrays.toString(headers) %>" />
		<jsp:param name="tableData" value="<%= Arrays.deepToString(monHocData) %>" />
		<jsp:param name="objectType" value="MonHoc" />
	</jsp:include>
	<div class="crud-buttons">
	        <button onclick="createMonHoc()">Create</button>
	        <button onclick="updateMonHoc()">Update</button>
	        <button onclick="deleteMonHoc()">Delete</button>
		</div>
</body>
</html>