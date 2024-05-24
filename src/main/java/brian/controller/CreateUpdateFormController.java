package brian.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/CreateUpdateFormController")
public class CreateUpdateFormController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("In CreateUpdateFormController");
		
		String objectType = request.getParameter("objectType");
        String action = request.getParameter("action");
            
        // Fetch all form data
        Map<String, String> formData = new HashMap<>();
        for (String paramName : request.getParameterMap().keySet()) {
        	formData.put(paramName, request.getParameter(paramName));
        }
       

        // Set form data as an attribute
        request.setAttribute("formData", formData);

        
        if ("GiaoVu".equals(objectType)) {
        	String[] headers = new String[] {"Mã Giáo Vụ", "Tên Giáo Vụ", "Giới Tính", "Ngày Sinh", "Địa Chỉ"};
        	
        	request.getRequestDispatcher("/GiaoVuController?action=" + action).forward(request, response);
        } 
        else if ("SinhVien".equals(objectType)) {
        	String[] headers = new String[] {"Mã Sinh Viên", "Tên Sinh Viên", "Giới Tính", "Ngày Sinh", "Khoa", "Địa Chỉ", "Ghi Chú"};
			
            request.getRequestDispatcher("/SinhVienController?action=" + action).forward(request, response);
        } 
        else if ("GiaoVien".equals(objectType)) {
        	String[] headers = new String[] {"Mã Giáo Viên", "Tên Giáo Viên", "Giới Tính", "Ngày Sinh", "Khoa", "Địa Chỉ", "Ghi Chú"};
			
            request.getRequestDispatcher("/GiaoVienController?action=" + action).forward(request, response);
        } 
        else if ("MonHoc".equals(objectType)) {
        	String[] headers = new String[] {"Mã Môn Học", "Tên Môn Học", "SốTC"};
			
            request.getRequestDispatcher("/MonHocController?action=" + action).forward(request, response);
        } 
        else if ("LopHoc".equals(objectType)) {
        	String[] headers = new String[] {"Mã Lớp", "Tên Lớp", "SôSV"}; 
			
            request.getRequestDispatcher("/LopHocController?action=" + action).forward(request, response);
        } 
        else if ("HocKi".equals(objectType)) {
        	String[] headers = new String[] {"Mã Học Kì", "Tên Học Kì", "Năm Học", "Ngày Bắt Đầu", "Ngày Kết Thúc"};
			
            request.getRequestDispatcher("/HocKiController?action=" + action).forward(request, response);
        }
        else if ("HocPhan".equals(objectType)) {
        	String[] headers = new String[] {"Mã MH", "Tên Môn Học", "Tên Lớp", "SốTC", "Sĩ Số", "Đã ĐK", "Khóa", "Lịch học", "Địa Điểm", "Tên Giáo Viên"};
			
            request.getRequestDispatcher("/HocPhanController?action=" + action).forward(request, response);
        }
        else {
        	//......
        }
	}

}
