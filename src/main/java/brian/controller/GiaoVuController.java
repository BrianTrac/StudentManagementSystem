package brian.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.util.Date;
import java.util.Map;

import brian.Main;
import brian.model.GiaoVu;
import brian.model.TaiKhoan;

@WebServlet("/GiaoVuController")
public class GiaoVuController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	String[] headers = new String[] {"Mã Giáo Vụ", "Tên Giáo Vụ", "Giới Tính", "Ngày Sinh", "Địa Chỉ"};
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		
		System.out.println(action);
		System.out.println("In GiaoVuController");
		
		switch(action) {
		case "create":
			handleCreateGiaoVu(request, response);
			// Redirect to MainScreen after handling the action
		    response.sendRedirect("MainScreen?item=GiaoVu"); 
		    
			break;
		case "update":
			handleUpdateGiaoVu(request, response);
			// Redirect to MainScreen after handling the action
		    response.sendRedirect("MainScreen?item=GiaoVu"); 
		    
			break;
		case "delete":
			handleDeleteGiaoVu(request, response);
			break;
		default:
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().write("Invalid action");
		}
		
	}
	
	private void handleCreateGiaoVu(HttpServletRequest request, HttpServletResponse response) throws IOException  {
		try {
			Map<String, String> formData = (Map<String, String>) request.getAttribute("formData");

				
			String maGiaoVu = formData.get(headers[0]);
			String tenGiaoVu = formData.get(headers[1]);
			String gioiTinh = formData.get(headers[2]); 
			Date ngaySinh = Main.simpleDateFormat.parse(formData.get(headers[3]));
			String diaChi = formData.get(headers[4]);
			
			System.out.println("CreateGiaoVu");
			System.out.println(maGiaoVu);
			System.out.println(tenGiaoVu);
			System.out.println(gioiTinh);
			System.out.println(ngaySinh);
			System.out.println(diaChi);
			
			if (maGiaoVu == null || maGiaoVu.isEmpty()) {
	            throw new IllegalArgumentException("Mã giáo vụ không được để trống.");
	        } 
			
			if (!Main.taiKhoanDAO.addTaiKhoan(new TaiKhoan(maGiaoVu, "GiaoVu"))) {
			//	throw new IllegalArgumentException("Mã giáo vụ đã tồn tại.");
				request.setAttribute("errorMessage", "Error update database!");
                // Forward back to createUpdateForm.jsp
                request.getRequestDispatcher("createUpdateForm.jsp").forward(request, response);
                return;
			}
			
			GiaoVu giaoVu = new GiaoVu(Main.taiKhoanDAO.getTaiKhoan(maGiaoVu), tenGiaoVu, gioiTinh, ngaySinh, diaChi);
			
			System.out.println("After create GiaoVu in GiaoVuController");
			System.out.println(giaoVu.getMaGVu());
			
			Main.giaoVuDAO.addGiaoVu(giaoVu);
			
			
		} catch (Exception e) { // Catch more specific exceptions if needed
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().write("Error creating GiaoVu: " + e.getMessage());
        }
	}
	
	private void handleUpdateGiaoVu(HttpServletRequest request, HttpServletResponse response) throws IOException  {
		try {
			Map<String, String> formData = (Map<String, String>) request.getAttribute("formData");
	
			String maGiaoVu = formData.get(headers[0]);
			String tenGiaoVu = formData.get(headers[1]);
			String gioiTinh = formData.get(headers[2]); 
			Date ngaySinh = Main.simpleDateFormat.parse(formData.get(headers[3]));
			String diaChi = formData.get(headers[4]);
			
			System.out.println("UpdateGiaoVu");
			System.out.println(maGiaoVu);
			System.out.println(tenGiaoVu);
			System.out.println(gioiTinh);
			System.out.println(ngaySinh);
			System.out.println(diaChi);

			if (maGiaoVu == null || maGiaoVu.isEmpty()) {
	            throw new IllegalArgumentException("Mã giáo vụ không được để trống.");
	        } 
			
			GiaoVu giaoVu = new GiaoVu(Main.taiKhoanDAO.getTaiKhoan(maGiaoVu), tenGiaoVu, gioiTinh, ngaySinh, diaChi);
			if (Main.giaoVuDAO.updateGiaoVu(giaoVu)) {
				System.out.println("Success Update GiaoVu");
			}
			else {
				System.out.println("Failed Update GiaoVu");
			}
			
			response.getWriter().write("success"); 
		} catch (Exception e) { // Catch more specific exceptions if needed
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().write("Error updating GiaoVu: " + e.getMessage());
        }
	}
	
	private void handleDeleteGiaoVu(HttpServletRequest request, HttpServletResponse response) throws IOException  {
		try {
			String maGiaoVu = request.getParameter("maGiaoVu");
			
			System.out.println("maGiaoVu in GiaoVuController: " + maGiaoVu);
			
			if (maGiaoVu == null || maGiaoVu.isEmpty()) {
	            throw new IllegalArgumentException("Mã giáo vụ không được để trống.");
	        } 
			
			Main.giaoVuDAO.deleteGiaoVu(maGiaoVu);
			 
		} catch (Exception e) { // Catch more specific exceptions if needed
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().write("Error deleting GiaoVu: " + e.getMessage());
        }
	}
}
