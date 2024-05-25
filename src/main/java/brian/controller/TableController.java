package brian.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;

import brian.Main;

@WebServlet("/TableController")
public class TableController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String objectType = request.getParameter("objectType");
		if (objectType == null) {
			objectType = "GiaoVu";
		}
		
		Object[][] tableData;
		String[] headers;
		switch (objectType) {
		case "GiaoVu":
			tableData = Main.giaoVuDAO.getObjectMatrix();
			headers = new String[] {"Mã Giáo Vụ", "Tên Giáo Vụ", "Giới Tính", "Ngày Sinh", "Địa Chỉ"};
			break;
		case "SinhVien":
			tableData = Main.sinhVienDAO.getObjectMatrix();
			headers = new String[] {"Mã Sinh Viên", "Tên Sinh Viên", "Giới Tính", "Ngày Sinh", "Khoa", "Địa Chỉ", "Ghi Chú"};
			break;
		case "GiaoVien":
			tableData = Main.giaoVienDAO.getObjectMatrix();
			headers = new String[] {"Mã Giáo Viên", "Tên Giáo Viên", "Giới Tính", "Ngày Sinh", "Khoa", "Địa Chỉ", "Ghi Chú"};
			break;
		case "MonHoc":
			tableData = Main.monHocDAO.getObjectMatrix();
			headers = new String[] {"Mã Môn Học", "Tên Môn Học", "SốTC"};
			break;
		case "LopHoc":
			tableData = Main.lopDAO.getObjectMatrix();
			headers = new String[] {"Mã Lớp", "Tên Lớp", "SôSV"}; 
			break;
		case "HocKi":
			tableData = Main.hocKiDAO.getObjectMatrix();
			headers = new String[] {"Mã Học Kì", "Tên Học Kì", "Năm Học", "Ngày Bắt Đầu", "Ngày Kết Thúc"};
			break;
		case "HocPhan":
			tableData = Main.hocPhanDAO.getObjectMatrix();
		//	headers = new String[] {"Mã MH", "Tên Môn Học", "Tên Lớp", "SốTC", "Sĩ Số", "Đã ĐK", "Khóa", "Lịch học", "Địa Điểm", "Tên Giáo Viên"};
			headers = new String[] {"Mã HP", "Mã MH", "Mã Lớp", "Mã HK", "Mã GV", "Sĩ Số", "Đã ĐK", "Khóa", "Thứ", "Khung giờ", "Phòng", "Địa Điểm"};
			break;
		default:
			tableData = new Object[0][0];
			headers = new String[0];
			break;
		}
		
		
		request.setAttribute("headers", headers);
		request.setAttribute("tableData", tableData);
		request.setAttribute("objectType", objectType);
		
		System.out.println("TableController in TableController");
		System.out.println("Headers: " + Arrays.toString(headers));
        System.out.println("Table Data: " + Arrays.deepToString(tableData));
		System.out.println(objectType);
		
		request.getRequestDispatcher("Table.jsp").forward(request, response);
	}
}
