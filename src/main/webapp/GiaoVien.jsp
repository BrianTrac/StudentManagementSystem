<%@page import="java.util.Arrays"%>
<%@page import="java.lang.reflect.Array"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="brian.Main" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%
		Object[][] giaoVienData = Main.giaoVienDAO.getObjectMatrix();
		String[] headers  = new String[] {"Mã Giáo Viên", "Tên Giáo Viên", "Giới Tính", "Ngày Sinh", "Khoa", "Địa Chỉ", "Ghi Chú"};
	%>
	<jsp:include page="Table.jsp">
		<jsp:param name="headers" value="<%= Arrays.toString(headers) %>" />
		<jsp:param name="tableData" value="<%= Arrays.deepToString(giaoVienData) %>" />
		<jsp:param name="objectType" value="GiaoVien" />
	</jsp:include>
	<div class="crud-buttons">
	        <button onclick="createGiaoVien()">Create</button>
	        <button onclick="updateGiaoVien()">Update</button>
	        <button onclick="deleteGiaoVien()">Delete</button>
	</div>
</body>
</html>