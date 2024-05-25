<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="vi">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Login Page</title>
<link rel="stylesheet" href="loginstyle.css?v=1.2">
</head>
<body> 
	<div class="image-container">
        <img src="logo.png" alt="University Logo" class="logo">
    </div>
    <div class="login-container">
        <h2>HCMUS Portal - Đăng nhập</h2>
        <div class=" form-container">
            <form id="loginForm" action="LoginController" method="post">
  				<%-- Display error message if available --%>
			    <%
			    String errorMessage = (String) request.getSession().getAttribute("errorMessage");
			    if (errorMessage != null && !errorMessage.isEmpty()) {
			    %>
			        <p><span style="color: red;"><%= errorMessage %></span></p>
			    <%
			    	request.getSession().removeAttribute("errorMessage");
			       }
			    %>  
	
				<label for="username"><b>Tên đăng nhập</b></label>
                <input type="text" id="username" name="username" required>
                <label for="password"><b>Mật khẩu</b></label>
                <input type="password" id="password" name="password" required>
                <input type="submit" value="Đăng nhập" class="submit-button">
            </form>
        </div>
    </div>
</body>

</html>