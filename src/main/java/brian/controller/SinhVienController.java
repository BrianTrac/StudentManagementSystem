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
import brian.model.SinhVien;
import brian.model.TaiKhoan;

@WebServlet("/SinhVienController")
public class SinhVienController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	String[] headers = new String[] {"Mã Sinh Viên", "Tên Sinh Viên", "Giới Tính", "Ngày Sinh", "Khoa", "Địa Chỉ", "Ghi Chú"};
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
				
		System.out.println(action);
		System.out.println("In SinhVienController");
		
		switch(action) {
		case "create":
			handleCreateSinhVien(request, response);
			break;
		case "update":
			handleUpdateSinhVien(request, response);
			break;
		case "delete":
			handleDeleteSinhVien(request, response);
			break;
		default:
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().write("Invalid action");
		}
		// Redirect to MainScreen after handling the action
	    response.sendRedirect("MainScreen?item=SinhVien"); 
	}
	
	private void handleCreateSinhVien(HttpServletRequest request, HttpServletResponse response) throws IOException  {
		try {
			Map<String, String> formData = (Map<String, String>) request.getAttribute("formData");

				
			String maSinhVien = formData.get(headers[0]);
			String tenSinhVien = formData.get(headers[1]);
			String gioiTinh = formData.get(headers[2]); 
			Date ngaySinh = Main.simpleDateFormat.parse(formData.get(headers[3]));
			String khoa = formData.get(headers[4]);
			String diaChi = formData.get(headers[5]);
			String ghiChu = formData.get(headers[6]);
			
			System.out.println("CreateSinhVien");
			System.out.println(maSinhVien);
			System.out.println(tenSinhVien);
			System.out.println(gioiTinh);
			System.out.println(ngaySinh);
			System.out.println(khoa);
			System.out.println(diaChi);
			System.out.println(ghiChu);
			
			if (maSinhVien == null || maSinhVien.isEmpty()) {
	            throw new IllegalArgumentException("Mã sinh viên không được để trống.");
	        } 
			
			if (!Main.taiKhoanDAO.addTaiKhoan(new TaiKhoan(maSinhVien, "SV"))) {
			//	throw new IllegalArgumentException("Mã sinh viên đã tồn tại.");
				request.setAttribute("errorMessage", "Mã sinh viên đã tồn tại!");
                // Forward back to createUpdateForm.jsp
                request.getRequestDispatcher("createUpdateForm.jsp").forward(request, response);
                return;
			}
			
			SinhVien sinhVien = new SinhVien(Main.taiKhoanDAO.getTaiKhoan(maSinhVien), tenSinhVien, gioiTinh, ngaySinh, khoa, diaChi, ghiChu);
			
			System.out.println("After create SinhVien in SinhVienController");
			System.out.println(sinhVien.getMaSV());
			
			Main.sinhVienDAO.addSinhVien(sinhVien);
			
			
		} catch (Exception e) { // Catch more specific exceptions if needed
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().write("Error creating SinhVien: " + e.getMessage());
        }
	}
	
	private void handleUpdateSinhVien(HttpServletRequest request, HttpServletResponse response) throws IOException  {
		try {
			Map<String, String> formData = (Map<String, String>) request.getAttribute("formData");
	
			String maSinhVien = formData.get(headers[0]);
			String tenSinhVien = formData.get(headers[1]);
			String gioiTinh = formData.get(headers[2]); 
			Date ngaySinh = Main.simpleDateFormat.parse(formData.get(headers[3]));
			String khoa = formData.get(headers[4]);
			String diaChi = formData.get(headers[5]);
			String ghiChu = formData.get(headers[6]);
			
			System.out.println("UpdateSinhVien");
			System.out.println(maSinhVien);
			System.out.println(tenSinhVien);
			System.out.println(gioiTinh);
			System.out.println(ngaySinh);
			System.out.println(khoa);
			System.out.println(diaChi);
			System.out.println(ghiChu);

			if (maSinhVien == null || maSinhVien.isEmpty()) {
	            throw new IllegalArgumentException("Mã sinh viên không được để trống.");
	        } 
			
			SinhVien sinhVien = new SinhVien(Main.taiKhoanDAO.getTaiKhoan(maSinhVien), tenSinhVien, gioiTinh, ngaySinh, khoa, diaChi, ghiChu);
			if (Main.sinhVienDAO.updateSinhVien(sinhVien)) {
				System.out.println("Success Update SinhVien");
			}
			else {
				System.out.println("Failed Update SinhVien");
			}
			
			response.getWriter().write("success"); 
		} catch (Exception e) { // Catch more specific exceptions if needed
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().write("Error updating SinhVien: " + e.getMessage());
        }
	}
	
	private void handleDeleteSinhVien(HttpServletRequest request, HttpServletResponse response) throws IOException  {
		try {
			String maSinhVien = request.getParameter("maSinhVien");
			
			System.out.println("maSinhVien in SinhVienController: " + maSinhVien);
			
			if (maSinhVien == null || maSinhVien.isEmpty()) {
	            throw new IllegalArgumentException("Mã sinh viên không được để trống.");
	        } 
			
			Main.sinhVienDAO.deleteSinhVien(maSinhVien);
			 
		} catch (Exception e) { // Catch more specific exceptions if needed
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().write("Error deleting SinhVien: " + e.getMessage());
        }
	}
	
}
