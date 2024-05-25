package brian.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import brian.Main;
import brian.model.HocPhan;
import brian.model.SinhVien;

@WebServlet("/HocPhanController")
public class HocPhanController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	String[] headers = new String[] {"Mã HP", "Mã MH", "Mã Lớp", "Mã HK", "Mã GV", "Sĩ Số", "Đã ĐK", "Khóa", "Thứ", "Khung giờ", "Phòng", "Địa Điểm"};
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		
		System.out.println(action);
		System.out.println("In HocPhanController");
		
		switch(action) {
		case "create":
			handleCreateHocPhan(request, response);
			// Redirect to MainScreen after handling the action
		    response.sendRedirect("MainScreen?item=HocPhan"); 
			break;
		case "update":
			handleUpdateHocPhan(request, response);
			// Redirect to MainScreen after handling the action
		    response.sendRedirect("MainScreen?item=HocPhan"); 
			break;
		case "delete":
			handleDeleteHocPhan(request, response);
			break;
		case "viewSinhVien":
			handleViewDanhSachSinhVien(request, response);
			break;
		case "viewSinhVienNotDangKi":
			handleViewDanhSachSinhVienNotDangKi(request, response);
			break;
		case "deleteSinhVienInHocPhan":
			handleDeleteSinhVienInHocPhan(request, response);
			break;
		case "addSinhVienToHocPhan":
			handleAddSinhVienToHocPhan(request, response);
			break;
		default:
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().write("Invalid action");
		}
	
	}
	
	private void handleAddSinhVienToHocPhan(HttpServletRequest request, HttpServletResponse response) throws IOException  {
		try {
			String maHP = request.getParameter("maHP");
			String maSV = request.getParameter("maSV");
				
			if (maHP == null || maHP.isEmpty() || maSV == null || maSV.isEmpty()) {
	            throw new IllegalArgumentException("Mã học phần vs mã sinh viên không được để trống.");
	        } 
			
			if (Main.dkhpDAO.addDKHP(maHP, maSV)) {
				System.out.println("Add SV in HP success");
			} else {
				System.out.println("Add SV in HP failed");
			}
			 
		} catch (Exception e) { // Catch more specific exceptions if needed
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().write("Error adding SV in HP: " + e.getMessage());
        }
	}
	
	
	private void handleDeleteSinhVienInHocPhan(HttpServletRequest request, HttpServletResponse response) throws IOException  {
		try {
			String maHP = request.getParameter("maHP");
			String maSV = request.getParameter("maSV");
				
			if (maHP == null || maHP.isEmpty() || maSV == null || maSV.isEmpty()) {
	            throw new IllegalArgumentException("Mã học phần vs mã sinh viên không được để trống.");
	        } 
			
			if (Main.dkhpDAO.deleteDKHP(maHP, maSV)) {
				System.out.println("Delete SV in HP success");
			} else {
				System.out.println("Delete SV in HP failed");
			}
			 
		} catch (Exception e) { // Catch more specific exceptions if needed
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().write("Error deleting SV in HP: " + e.getMessage());
        }
	}
	
	private void handleViewDanhSachSinhVienNotDangKi(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    try {
	    	System.out.println("handleVieDanhSachSinhVienNotDangKi in HocPhanController");
	    	
	        String maHP = request.getParameter("maHP");
	        System.out.println(maHP + " handleVieDanhSachSinhVienNotDangKi in HocPhanController");
	    	
	        if (maHP == null || maHP.isEmpty()) {
	            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
	            response.getWriter().write("Invalid or missing maHP parameter");
	            return;
	        }
	        
	           	

	        List<SinhVien> sinhVienList = Main.hocPhanDAO.getSinhVienListNotInHocPhan(maHP);
	        String[] headersSV = new String[] {"Mã Sinh Viên", "Tên Sinh Viên", "Giới Tính", "Ngày Sinh", "Khoa", "Địa Chỉ", "Ghi Chú"};
	        Object[][] sinhVienData = Main.sinhVienDAO.getObjectMatrix(sinhVienList);
	        
	        System.out.println(sinhVienList);
	        System.out.println(headersSV);
	        System.out.println(sinhVienData);
	        
	        request.setAttribute("headers", headersSV);
	        request.setAttribute("tableData", sinhVienData);
	        request.setAttribute("objectType", "SinhVien");

	        request.getRequestDispatcher("Table.jsp").forward(request, response);
	    } catch (Exception e) {
	        // Log the exception and handle appropriately (e.g., send an error page)
	        e.printStackTrace(); 
	        response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
	        response.getWriter().write("Error fetching student list.");
	    }
	}
	
	private void handleViewDanhSachSinhVien(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    try {
	    	System.out.println("handleVieDanhSachSinhVien in HocPhanController");
	    	
	        String maHP = request.getParameter("maHP");
	        if (maHP == null || maHP.isEmpty()) {
	            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
	            response.getWriter().write("Invalid or missing maHP parameter");
	            return;
	        }
	   

	        List<SinhVien> sinhVienList = Main.hocPhanDAO.getSinhVienListInHocPhan(maHP);
	        String[] headersSV = new String[] {"Mã Sinh Viên", "Tên Sinh Viên", "Giới Tính", "Ngày Sinh", "Khoa", "Địa Chỉ", "Ghi Chú"};
	        Object[][] sinhVienData = Main.sinhVienDAO.getObjectMatrix(sinhVienList);
	        
	        System.out.println(sinhVienList);
	        System.out.println(headersSV);
	        System.out.println(sinhVienData);
	        
	        request.setAttribute("headers", headersSV);
	        request.setAttribute("tableData", sinhVienData);
	        request.setAttribute("objectType", "SinhVien");

	        request.getRequestDispatcher("Table.jsp").forward(request, response);
	    } catch (Exception e) {
	        // Log the exception and handle appropriately (e.g., send an error page)
	        e.printStackTrace(); 
	        response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
	        response.getWriter().write("Error fetching student list.");
	    }
	}
		
	private void handleCreateHocPhan(HttpServletRequest request, HttpServletResponse response) throws IOException  {
		try {
			Map<String, String> formData = (Map<String, String>) request.getAttribute("formData");
				
			String maHocPhan = formData.get(headers[0]);
			String maMH = formData.get(headers[1]);
			String maLop = formData.get(headers[2]);
			String maHK = formData.get(headers[3]);
			String maGV = formData.get(headers[4]);
			int siSo = Integer.parseInt(formData.get(headers[5]));
			int daDK = Integer.parseInt(formData.get(headers[6]));
			String khoa = formData.get(headers[7]);
			String thu = formData.get(headers[8]);
			String khungGio = formData.get(headers[9]);
			String phong = formData.get(headers[10]);
			String diaDiem = formData.get(headers[11]);
			
			System.out.println("CreateHocPhan");
			
			if (maHocPhan == null || maHocPhan.isEmpty()) {
	            throw new IllegalArgumentException("Mã học phần không được để trống.");
	        } 
				
			HocPhan hocPhan = new HocPhan(maHocPhan, Main.monHocDAO.getMonHoc(maMH), Main.lopDAO.getLop(maLop), Main.hocKiDAO.getHocKi(maHK), Main.giaoVienDAO.getGiaoVien(maGV), siSo, daDK, khoa, thu, khungGio, phong, diaDiem);
			
			if (!Main.hocPhanDAO.addHocPhan(hocPhan)) {
				request.setAttribute("errorMessage", "Error update database!");
                // Forward back to createUpdateForm.jsp
                request.getRequestDispatcher("createUpdateForm.jsp").forward(request, response);
                return;
			}
			
			
			
		} catch (Exception e) { // Catch more specific exceptions if needed
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().write("Error creating HocPhan: " + e.getMessage());
        }
	}
	
	private void handleUpdateHocPhan(HttpServletRequest request, HttpServletResponse response) throws IOException  {
		try {
			Map<String, String> formData = (Map<String, String>) request.getAttribute("formData");
	
			String maHocPhan = formData.get(headers[0]);
			String maMH = formData.get(headers[1]);
			String maLop = formData.get(headers[2]);
			String maHK = formData.get(headers[3]);
			String maGV = formData.get(headers[4]);
			int siSo = Integer.parseInt(formData.get(headers[5]));
			int daDK = Integer.parseInt(formData.get(headers[6]));
			String khoa = formData.get(headers[7]);
			String thu = formData.get(headers[8]);
			String khungGio = formData.get(headers[9]);
			String phong = formData.get(headers[10]);
			String diaDiem = formData.get(headers[11]);
			
			System.out.println("UpdateHocPhan");

			if (maHocPhan == null || maHocPhan.isEmpty()) {
	            throw new IllegalArgumentException("Mã học phần không được để trống.");
	        } 
			
			HocPhan hocPhan = new HocPhan(maHocPhan, Main.monHocDAO.getMonHoc(maMH), Main.lopDAO.getLop(maLop), Main.hocKiDAO.getHocKi(maHK), Main.giaoVienDAO.getGiaoVien(maGV), siSo, daDK, khoa, thu, khungGio, phong, diaDiem);
			
			if (Main.hocPhanDAO.updateHocPhan(hocPhan)) {
				System.out.println("Success Update HocPhan");
			}
			else {
				System.out.println("Failed Update HocPhan");
			}
			
			response.getWriter().write("success"); 
		} catch (Exception e) { // Catch more specific exceptions if needed
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().write("Error updating HocPhan: " + e.getMessage());
        }
	}
	
	private void handleDeleteHocPhan(HttpServletRequest request, HttpServletResponse response) throws IOException  {
		try {
			String maHocPhan = request.getParameter("maHocPhan");
				
			if (maHocPhan == null || maHocPhan.isEmpty()) {
	            throw new IllegalArgumentException("Mã học phần không được để trống.");
	        } 
			
			Main.hocPhanDAO.deleteHocPhan(maHocPhan);
			 
		} catch (Exception e) { // Catch more specific exceptions if needed
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().write("Error deleting HocPhan: " + e.getMessage());
        }
	}
}
