package brian.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import brian.Main;
import brian.model.TaiKhoan;
import brian.util.PasswordUtil;

@WebServlet(" /LoginController")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		System.out.println("Username: " + username); // Debugging print statement
	    System.out.println("Password: " + password); // Debugging print statement
		
		TaiKhoan tKhoan = Main.taiKhoanDAO.getTaiKhoan(username);

		if (tKhoan != null && PasswordUtil.verifyPassword(password, tKhoan.getMatKhau())) {
			request.getSession().setAttribute("taiKhoan", tKhoan);
			response.sendRedirect("MainScreen.jsp");
		}
		else {
			request.getSession().setAttribute("errorMessage", "Invalid username or password");
			response.sendRedirect("Login.jsp");
		}
	}

}
