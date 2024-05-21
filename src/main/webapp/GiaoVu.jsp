<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="brian.Main" %>
    <%@ page import="java.util.Arrays" %>
    
<!DOCTYPE html>
<html lang="vi">
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

</head>
<body>
	<%
		Object[][] giaoVuData = Main.giaoVuDAO.getObjectMatrix();
		String[] headers  = new String[] {"Mã Giáo Vụ", "Tên Giáo Vụ", "Giới Tính", "Ngày Sinh", "Địa Chỉ"};
	%>
	
	 <jsp:include page="Table.jsp">
	     <jsp:param name="headers" value="<%= Arrays.toString(headers) %>" />
	     <jsp:param name="tableData" value="<%= Arrays.deepToString(giaoVuData) %>" />
	     <jsp:param name="objectType" value="GiaoVu" />
 	</jsp:include>
	    <div class="crud-buttons">
	        <button onclick="createGiaoVu()">Create</button>
	        <button onclick="updateGiaoVu()">Update</button>
	        <button onclick="deleteGiaoVu()">Delete</button>
		</div>
</body>
</html>