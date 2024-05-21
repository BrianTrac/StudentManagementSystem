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
		Object[][] hocPhanData = Main.hocPhanDAO.getObjectMatrix();
		String[] headers  = new String[] {"Mã MH", "Tên Môn Học", "Tên Lớp", "SốTC", "Sĩ Số", "Đã ĐK", "Khóa", "Lịch học", "Địa Điểm", "Tên Giáo Viên"};
	%>
	<jsp:include page="Table.jsp">
		<jsp:param name="headers" value="<%= Arrays.toString(headers) %>" />
		<jsp:param name="tableData" value="<%= Arrays.deepToString(hocPhanData) %>" />
		<jsp:param name="objectType" value="HocPhan" />
	</jsp:include> 
	<div class="crud-buttons">
	        <button onclick="createHocPhan()">Create</button>
	        <button onclick="updateHocPhan()">Update</button>
	        <button onclick="deleteHocPhan()">Delete</button>
	</div>
</body>
</html>