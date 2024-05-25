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
import brian.model.GiaoVien;
import brian.model.TaiKhoan;

@WebServlet("/GiaoVienController")
public class GiaoVienController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	String[] headers = new String[] {"Mã Giáo Viên", "Tên Giáo Viên", "Giới Tính", "Ngày Sinh", "Khoa", "Địa Chỉ", "Ghi Chú"};
	 
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		
		System.out.println(action);
		System.out.println("In GiaoVienController");
		
		switch(action) {
		case "create":
			handleCreateGiaoVien(request, response);
			// Redirect to MainScreen after handling the action
		    response.sendRedirect("MainScreen?item=GiaoVien");
			break;
		case "update":
			handleUpdateGiaoVien(request, response);
			// Redirect to MainScreen after handling the action
		    response.sendRedirect("MainScreen?item=GiaoVien");
			break;
		case "delete":
			handleDeleteGiaoVien(request, response);
			break;
		default:
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().write("Invalid action");
		}
		 
	}

	private void handleCreateGiaoVien(HttpServletRequest request, HttpServletResponse response) throws IOException  {
		try {
			Map<String, String> formData = (Map<String, String>) request.getAttribute("formData");
				
			String maGiaoVien = formData.get(headers[0]);
			String tenGiaoVien = formData.get(headers[1]);
			String gioiTinh = formData.get(headers[2]); 
			Date ngaySinh = Main.simpleDateFormat.parse(formData.get(headers[3]));
			String khoa = formData.get(headers[4]);
			String diaChi = formData.get(headers[5]);
			String ghiChu = formData.get(headers[6]);
			
			System.out.println("CreateGiaoVien");
//			System.out.println(maGiaoVien);
//			System.out.println(tenGiaoVien);
//			System.out.println(gioiTinh);
//			System.out.println(ngaySinh);
//			System.out.println(khoa);
//			System.out.println(diaChi);
//			System.out.println(ghiChu);
			
			if (maGiaoVien == null || maGiaoVien.isEmpty()) {
	            throw new IllegalArgumentException("Mã giáo viên không được để trống.");
	        } 
				
			GiaoVien giaoVien = new GiaoVien(maGiaoVien, tenGiaoVien, gioiTinh, ngaySinh, khoa, diaChi, ghiChu);
			
			if (!Main.giaoVienDAO.addGiaoVien(giaoVien)) {
				request.setAttribute("errorMessage", "Error update database!");
                // Forward back to createUpdateForm.jsp
                request.getRequestDispatcher("createUpdateForm.jsp").forward(request, response);
                return;
			}
			
			
			
		} catch (Exception e) { // Catch more specific exceptions if needed
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().write("Error creating GiaoVien: " + e.getMessage());
        }
	}
	
	private void handleUpdateGiaoVien(HttpServletRequest request, HttpServletResponse response) throws IOException  {
		try {
			Map<String, String> formData = (Map<String, String>) request.getAttribute("formData");
	
			String maGiaoVien = formData.get(headers[0]);
			String tenGiaoVien = formData.get(headers[1]);
			String gioiTinh = formData.get(headers[2]); 
			Date ngaySinh = Main.simpleDateFormat.parse(formData.get(headers[3]));
			String khoa = formData.get(headers[4]);
			String diaChi = formData.get(headers[5]);
			String ghiChu = formData.get(headers[6]);
			
			System.out.println("UpdateGiaoVien");
//			System.out.println(maGiaoVien);
//			System.out.println(tenGiaoVien);
//			System.out.println(gioiTinh);
//			System.out.println(ngaySinh);
//			System.out.println(khoa);
//			System.out.println(diaChi);
//			System.out.println(ghiChu);

			if (maGiaoVien == null || maGiaoVien.isEmpty()) {
	            throw new IllegalArgumentException("Mã giáo viên không được để trống.");
	        } 
			
			GiaoVien giaoVien = new GiaoVien(maGiaoVien, tenGiaoVien, gioiTinh, ngaySinh, khoa, diaChi, ghiChu);
			
			if (Main.giaoVienDAO.updateGiaoVien(giaoVien)) {
				System.out.println("Success Update GiaoVien");
			}
			else {
				System.out.println("Failed Update GiaoVien");
			}
			
			response.getWriter().write("success"); 
		} catch (Exception e) { // Catch more specific exceptions if needed
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().write("Error updating GiaoVien: " + e.getMessage());
        }
	}
	
	private void handleDeleteGiaoVien(HttpServletRequest request, HttpServletResponse response) throws IOException  {
		try {
			String maGiaoVien = request.getParameter("maGiaoVien");
				
			if (maGiaoVien == null || maGiaoVien.isEmpty()) {
	            throw new IllegalArgumentException("Mã giáo viên không được để trống.");
	        } 
			
			Main.giaoVienDAO.deleteGiaoVien(maGiaoVien);
			 
		} catch (Exception e) { // Catch more specific exceptions if needed
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().write("Error deleting GiaoVien: " + e.getMessage());
        }
	}
	
}
