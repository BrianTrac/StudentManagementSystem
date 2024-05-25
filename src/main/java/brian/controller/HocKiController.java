package brian.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.Map;

import brian.Main;
import brian.model.HocKi;

@WebServlet("/HocKiController")
public class HocKiController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	String[] headers = new String[] {"Mã Học Kì", "Tên Học Kì", "Năm Học", "Ngày Bắt Đầu", "Ngày Kết Thúc"};
	  
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		
		System.out.println(action);
		System.out.println("In HocKiController");
		
		switch(action) {
		case "create":
			handleCreateHocKi(request, response);
			// Redirect to MainScreen after handling the action
		    response.sendRedirect("MainScreen?item=HocKi"); 

			break;
		case "update":
			handleUpdateHocKi(request, response);
			// Redirect to MainScreen after handling the action
		    response.sendRedirect("MainScreen?item=HocKi"); 

			break;
		case "delete":
			handleDeleteHocKi(request, response);
			break;
		default:
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().write("Invalid action");
		}
		
	}
	
	private void handleCreateHocKi(HttpServletRequest request, HttpServletResponse response) throws IOException  {
		try {
			Map<String, String> formData = (Map<String, String>) request.getAttribute("formData");
				
			String maHocKi = formData.get(headers[0]);
			String tenHocKi = formData.get(headers[1]);
			int namHoc = Integer.parseInt(formData.get(headers[2])); 
			Date ngayBatDau = Main.simpleDateFormat.parse(formData.get(headers[3]));
			Date ngayKetThuc = Main.simpleDateFormat.parse(formData.get(headers[4]));
						
			System.out.println("CreateHocKi");

			if (maHocKi == null || maHocKi.isEmpty()) {
	            throw new IllegalArgumentException("Mã học kì không được để trống.");
	        } 
				
			HocKi hocKi = new HocKi(maHocKi, tenHocKi, namHoc, ngayBatDau, ngayKetThuc);
			
			if (!Main.hocKiDAO.addHocKi(hocKi)) {
				request.setAttribute("errorMessage", "Error update database!");
                // Forward back to createUpdateForm.jsp
                request.getRequestDispatcher("createUpdateForm.jsp").forward(request, response);
                return;
			}
				
		} catch (Exception e) { // Catch more specific exceptions if needed
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().write("Error creating HocKi: " + e.getMessage());
        }
	}
	
	private void handleUpdateHocKi(HttpServletRequest request, HttpServletResponse response) throws IOException  {
		try {
			Map<String, String> formData = (Map<String, String>) request.getAttribute("formData");
	
			String maHocKi = formData.get(headers[0]);
			String tenHocKi = formData.get(headers[1]);
			int namHoc = Integer.parseInt(formData.get(headers[2])); 
			Date ngayBatDau = Main.simpleDateFormat.parse(formData.get(headers[3]));
			Date ngayKetThuc = Main.simpleDateFormat.parse(formData.get(headers[4]));
			
			
			System.out.println("UpdateHocKi");

			if (maHocKi == null || maHocKi.isEmpty()) {
	            throw new IllegalArgumentException("Mã học kì không được để trống.");
	        } 
			
			HocKi hocKi = new HocKi(maHocKi, tenHocKi, namHoc, ngayBatDau, ngayKetThuc);
			
			if (Main.hocKiDAO.updateHocKi(hocKi)) {
				System.out.println("Success Update HocKi");
			}
			else {
				System.out.println("Failed Update HocKi");
			}
			
			response.getWriter().write("success"); 
		} catch (Exception e) { // Catch more specific exceptions if needed
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().write("Error updating HocKi: " + e.getMessage());
        }
	}
	
	private void handleDeleteHocKi(HttpServletRequest request, HttpServletResponse response) throws IOException  {
		try {
			String maHocKi = request.getParameter("maHocKi");
				
			if (maHocKi == null || maHocKi.isEmpty()) {
	            throw new IllegalArgumentException("Mã học kì không được để trống.");
	        } 
			
			Main.hocKiDAO.deleteHocKi(maHocKi);
			 
		} catch (Exception e) { // Catch more specific exceptions if needed
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().write("Error deleting HocKi: " + e.getMessage());
        }
	}
	
}
