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
import brian.model.MonHoc;

@WebServlet("/MonHocController")
public class MonHocController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	String[] headers = new String[] {"Mã Môn Học", "Tên Môn Học", "SốTC"};
	
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		
		System.out.println(action);
		System.out.println("In MonHocController");
		
		switch(action) {
		case "create":
			handleCreateMonHoc(request, response);
			break;
		case "update":
			handleUpdateMonHoc(request, response);
			break;
		case "delete":
			handleDeleteMonHoc(request, response);
			break;
		default:
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().write("Invalid action");
		}
		// Redirect to MainScreen after handling the action
	    response.sendRedirect("MainScreen?item=MonHoc"); 
	
	}
	
	private void handleCreateMonHoc(HttpServletRequest request, HttpServletResponse response) throws IOException  {
		try {
			Map<String, String> formData = (Map<String, String>) request.getAttribute("formData");
				
			String maMonHoc = formData.get(headers[0]);
			String tenMonHoc = formData.get(headers[1]);
			int soTC = Integer.parseInt(formData.get(headers[2]));
			
			
			System.out.println("CreateMonHoc");
			
			
			if (maMonHoc == null || maMonHoc.isEmpty()) {
	            throw new IllegalArgumentException("Mã môn học không được để trống.");
	        } 
				
			MonHoc monHoc = new MonHoc(maMonHoc, tenMonHoc, soTC);
			
			if (!Main.monHocDAO.addMonHoc(monHoc)) {
				request.setAttribute("errorMessage", "Mã môn học đã tồn tại!");
                // Forward back to createUpdateForm.jsp
                request.getRequestDispatcher("createUpdateForm.jsp").forward(request, response);
                return;
			}
			
			
			
		} catch (Exception e) { // Catch more specific exceptions if needed
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().write("Error creating MonHoc: " + e.getMessage());
        }
	}
	
	private void handleUpdateMonHoc(HttpServletRequest request, HttpServletResponse response) throws IOException  {
		try {
			Map<String, String> formData = (Map<String, String>) request.getAttribute("formData");
	
			
			String maMonHoc = formData.get(headers[0]);
			String tenMonHoc = formData.get(headers[1]);
			int soTC = Integer.parseInt(formData.get(headers[2]));
			
			System.out.println("UpdateMonHoc");

			if (maMonHoc == null || maMonHoc.isEmpty()) {
	            throw new IllegalArgumentException("Mã môn học không được để trống.");
	        } 
			
			MonHoc monHoc = new MonHoc(maMonHoc, tenMonHoc, soTC);
			
			if (Main.monHocDAO.updateMonHoc(monHoc)) {
				System.out.println("Success Update MonHoc");
			}
			else {
				System.out.println("Failed Update MonHoc");
			}
			
			response.getWriter().write("success"); 
		} catch (Exception e) { // Catch more specific exceptions if needed
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().write("Error updating MonHoc: " + e.getMessage());
        }
	}
	
	private void handleDeleteMonHoc(HttpServletRequest request, HttpServletResponse response) throws IOException  {
		try {
			String maMonHoc = request.getParameter("maMonHoc");
				
			if (maMonHoc == null || maMonHoc.isEmpty()) {
	            throw new IllegalArgumentException("Mã môn học không được để trống.");
	        } 
			
			Main.monHocDAO.deleteMonHoc(maMonHoc);
			 
		} catch (Exception e) { // Catch more specific exceptions if needed
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().write("Error deleting MonHoc: " + e.getMessage());
        }
	}
	
}
