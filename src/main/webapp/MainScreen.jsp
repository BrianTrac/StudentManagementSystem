<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 
<!DOCTYPE html>
<html lang="vi">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.1">
<title>Dynamic Menu</title>
<link rel="stylesheet" href="mainstyle.css?v=2.0">
</head>
<body>
	<div class="main-container">
        <nav class="navigation">
            <ul>
                <li><a href="MainScreen?item=GiaoVu">Giáo Vụ</a></li>
                <li><a href="MainScreen?item=SinhVien">Sinh Viên</a></li>
                <li><a href="MainScreen?item=GiaoVien">Giáo Viên</a></li>
                <li><a href="MainScreen?item=MonHoc">Môn Học</a></li>
                <li><a href="MainScreen?item=LopHoc">Lớp Học</a></li>
                <li><a href="MainScreen?item=HocKi">Học Kì</a></li>
                <li><a href="MainScreen?item=HocPhan">Học Phần</a></li>
                <li><a href="MainScreen?item=DangKi">Đăng kí học phần</a></li>
            </ul>
        </nav>

        <div class="mid-container">
            <div class="content">
            	<%
            		String selectedContent = (String) request.getAttribute("selectedContent");  
               		selectedContent = (selectedContent == null) ? " " : selectedContent;
             	  %>
            	<h2><%= selectedContent %></h2>        
            </div>
            <div class="search-bar">
                <input type="text" id="searchInput" placeholder="Search...">
                <button id="searchButton">Search</button>
            </div>
            <div class="datasheet">
				<jsp:include page="${selectedTable}"/>
            </div>
        </div>
        <div class="info-container">
				<jsp:include page="info.jsp"/>     
        </div>
    </div>
</body>
</html>