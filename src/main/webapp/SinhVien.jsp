<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="brian.Main"%>
<%@ page import="java.util.Arrays" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%
		Object[][] sinhVienData = Main.sinhVienDAO.getObjectMatrix();
		String[] headers  = new String[] {"Mã Sinh Viên", "Tên Sinh Viên", "Giới Tính", "Ngày Sinh", "Khoa", "Địa Chỉ", "Ghi Chú"};
	%>
	<jsp:include page="Table.jsp">
		<jsp:param name="headers" value="<%= Arrays.toString(headers) %>" />
		<jsp:param name="tableData" value="<%= Arrays.deepToString(sinhVienData) %>" />
		<jsp:param name="objectType" value="SinhVien" />
	</jsp:include>
	<div class="crud-buttons">
	        <button onclick="createSinhVien()">Create</button>
	        <button onclick="updateSinhVien()">Update</button>
	        <button onclick="deleteSinhVien()">Delete</button>
	</div>
</body>
</html>