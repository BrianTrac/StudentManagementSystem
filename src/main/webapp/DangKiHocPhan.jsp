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
		Object[][] dkhpData = Main.dkhpDAO.getObjectMatrix();
		String[] headers  = new String[] {"MãHK", "Số Lần Mở", "Ngày Bắt Đầu", "Ngày Kết Thúc"};
	%>
	<jsp:include page="Table.jsp">
		<jsp:param name="headers" value="<%= headers %>" />
		<jsp:param name="tableData" value="<%= dkhpData %>" />
		<jsp:param name="objectType" value="DKHP" />
	</jsp:include>

</body>
</html>