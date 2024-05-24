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
import brian.model.Lop;

@WebServlet("/LopHocController")
public class LopHocController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	String[] headers = new String[] {"Mã Lớp", "Tên Lớp", "SôSV"}; 
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		
		System.out.println(action);
		System.out.println("In LopHocController");
		
		switch(action) {
		case "create":
			handleCreateLopHoc(request, response);
			break;
		case "update":
			handleUpdateLopHoc(request, response);
			break;
		case "delete":
			handleDeleteLopHoc(request, response);
			break;
		default:
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().write("Invalid action");
		}
		// Redirect to MainScreen after handling the action
	    response.sendRedirect("MainScreen?item=LopHoc");
	}

	private void handleCreateLopHoc(HttpServletRequest request, HttpServletResponse response) throws IOException  {
		try {
			Map<String, String> formData = (Map<String, String>) request.getAttribute("formData");
				
			String maLopHoc = formData.get(headers[0]);
			String tenLopHoc = formData.get(headers[1]);
			int soSV = Integer.parseInt(formData.get(headers[2]));
			
			System.out.println("CreateLopHoc");

			if (maLopHoc == null || maLopHoc.isEmpty()) {
	            throw new IllegalArgumentException("Mã lớp học không được để trống.");
	        } 
				
			Lop lopHoc = new Lop(maLopHoc, tenLopHoc, soSV);
			
			if (!Main.lopDAO.addLop(lopHoc)) {
				request.setAttribute("errorMessage", "Mã lớp học đã tồn tại!");
                // Forward back to createUpdateForm.jsp
                request.getRequestDispatcher("createUpdateForm.jsp").forward(request, response);
                return;
			}
			
			
			
		} catch (Exception e) { // Catch more specific exceptions if needed
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().write("Error creating LopHoc: " + e.getMessage());
        }
	}
	
	private void handleUpdateLopHoc(HttpServletRequest request, HttpServletResponse response) throws IOException  {
		try {
			Map<String, String> formData = (Map<String, String>) request.getAttribute("formData");
	
			String maLopHoc = formData.get(headers[0]);
			String tenLopHoc = formData.get(headers[1]);
			int soSV = Integer.parseInt(formData.get(headers[2]));
			
			System.out.println("UpdateLopHoc");

			if (maLopHoc == null || maLopHoc.isEmpty()) {
	            throw new IllegalArgumentException("Mã lớp học không được để trống.");
	        } 
			
			Lop lopHoc = new Lop(maLopHoc, tenLopHoc, soSV);
			
			if (Main.lopDAO.updateLop(lopHoc)) {
				System.out.println("Success Update LopHoc");
			}
			else {
				System.out.println("Failed Update LopHoc");
			}
			
			response.getWriter().write("success"); 
		} catch (Exception e) { // Catch more specific exceptions if needed
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().write("Error updating LopHoc: " + e.getMessage());
        }
	}
	
	private void handleDeleteLopHoc(HttpServletRequest request, HttpServletResponse response) throws IOException  {
		try {
			String maLopHoc = request.getParameter("maLopHoc");
				
			if (maLopHoc == null || maLopHoc.isEmpty()) {
	            throw new IllegalArgumentException("Mã lớp học không được để trống.");
	        } 
			
			Main.lopDAO.deleteLop(maLopHoc);
			 
		} catch (Exception e) { // Catch more specific exceptions if needed
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().write("Error deleting LopHoc: " + e.getMessage());
        }
	}
	
}
