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
		Object[][] hocKiData = Main.hocKiDAO.getObjectMatrix();
		String[] headers  = new String[] {"Mã Học Kì", "Tên Học Kì", "Năm Học", "Ngày Bắt Đầu", "Ngày Kết Thúc"};
	%>
	<jsp:include page="Table.jsp">
		<jsp:param name="headers" value="<%= Arrays.toString(headers) %>" />
		<jsp:param name="tableData" value="<%= Arrays.deepToString(hocKiData) %>" />
		<jsp:param name="objectType" value="HocKi" />
	</jsp:include>
	<div class="crud-buttons">
	        <button onclick="createHocKi()">Create</button>
	        <button onclick="updateHocKi()">Update</button>
	        <button onclick="deleteHocKi()">Delete</button>
	</div>
</body>
</html>