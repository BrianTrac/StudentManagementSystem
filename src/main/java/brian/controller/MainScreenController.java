package brian.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import brian.Main;

@WebServlet("/MainScreen")
public class MainScreenController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	private String[] getObjectType(String selectedItem) {
		String contentType = " ";
		String tableType = " ";
		Object[][] object = null;
		String[] headers = null; 
		if ("GiaoVu".equals(selectedItem)) {
			contentType = "Thông Tin Giáo Vụ";
			tableType = "GiaoVu.jsp";
			object = Main.giaoVuDAO.getObjectMatrix();
			headers = new String[] {"Mã Giáo Vụ", "Tên Giáo Vụ", "Giới Tính", "Ngày Sinh", "Địa Chỉ"};
		}
		else if ("SinhVien".equals(selectedItem)) {
			contentType = "Thông Tin Sinh Viên";
			tableType = "SinhVien.jsp";
			object = Main.sinhVienDAO.getObjectMatrix();
			headers = new String[] {"Mã Sinh Viên", "Tên Sinh Viên", "Giới Tính", "Ngày Sinh", "Khoa", "Địa Chỉ", "Ghi Chú"};
		}
		else if ("GiaoVien".equals(selectedItem)) {
			contentType = "Thông Tin Giáo Viên";
			tableType = "GiaoVien.jsp";
			object = Main.giaoVienDAO.getObjectMatrix();
			headers = new String[] {"Mã Giáo Viên", "Tên Giáo Viên", "Giới Tính", "Ngày Sinh", "Khoa", "Địa Chỉ", "Ghi Chú"};
		}
		else if ("MonHoc".equals(selectedItem)) {
			contentType = "Thông Tin Môn Học";
			tableType = "MonHoc.jsp";
			object = Main.monHocDAO.getObjectMatrix();
			headers = new String[] {"Mã Môn Học", "Tên Môn Học", "SốTC"};
		}
		else if ("LopHoc".equals(selectedItem)) {
			contentType = "Thông Tin Lớp Học";
			tableType = "LopHoc.jsp";
			object = Main.lopDAO.getObjectMatrix();
			headers = new String[] {"Mã Lớp", "Tên Lớp", "SôSV"}; 
		}
		else if ("HocKi".equals(selectedItem)) {
			contentType = "Thông Tin Học Kì";
			tableType = "HocKi.jsp";
			object = Main.hocKiDAO.getObjectMatrix();
			headers = new String[] {"Mã Học Kì", "Tên Học Kì", "Năm Học", "Ngày Bắt Đầu", "Ngày Kết Thúc"};
		}
		else if ("HocPhan".equals(selectedItem)) {
			contentType = "Thông Tin Học Phần";
			tableType = "HocPhan.jsp";
			object = Main.hocPhanDAO.getObjectMatrix();
			headers = new String[] {"Mã HP", "Mã MH", "Mã Lớp", "Mã HK", "Mã GV", "Sĩ Số", "Đã ĐK", "Khóa", "Thứ", "Khung giờ", "Phòng", "Địa Điểm"};

		}
		else if ("DangKi".equals(selectedItem)) {
			contentType = "Thông Tin Đăng Kí Học Phần";
			tableType = "DangKiHocPhan.jsp";
			object = Main.dkhpDAO.getObjectMatrix();
			headers = new String[] {"MãHK", "Số Lần Mở", "Ngày Bắt Đầu", "Ngày Kết Thúc"};
		}
		else {
			//
		}
		
		return new String[] {contentType, tableType};
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String selectedItem = request.getParameter("item");
		String[] objectType = getObjectType(selectedItem);
		String contentType = objectType[0];
		String tableType = objectType[1];
	
		request.setAttribute("selectedContent", contentType);
		request.setAttribute("selectedTable", tableType);
		
		System.out.println(tableType + " in MainScreenController");
		
		request.getRequestDispatcher("MainScreen.jsp").forward(request, response);
	}

}
