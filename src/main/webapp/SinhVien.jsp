<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="brian.Main"%>
<%@ page import="java.util.Arrays" %>

<!DOCTYPE html>
<html lang="vi">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.1">
<title>Insert title here</title>
<style>
	.filter-container {
    display: flex;               /* Use flexbox for layout */
    align-items: center;        /* Vertically align items (label, input, button) */
    margin-bottom: 20px;       /* Add bottom margin for spacing */
    padding: 10px;             /* Add padding for visual separation */
    border: 1px solid #ccc;     /* Add a subtle border */
    border-radius: 5px;  
    width: 95%; /* Slightly round the corners */
}

.filter-container label {
    margin-right: 10px;       /* Space between label and input */
}

.filter-container input[type="text"] {
    flex-grow: 1;              /* Allow input to expand and fill available space */
    padding: 8px;               /* Add padding to input field */
    border: 1px solid #ccc;
    border-radius: 3px;
    margin-right: 10px;       /* Space between input and button */
}

.filter-container button {
    background-color: #007bff; /* Primary button color */
    color: white;
    border: none;
    padding: 8px 12px;
    border-radius: 3px;
    cursor: pointer;
}

.filter-container button:hover {
    background-color: #0056b3; /* Darker shade on hover */
}
</style>

<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script>
   //Pass the context path from JSP to JavaScript
   const contextPath = '<%= request.getContextPath() %>';
</script>

<script src="reloadTableData.js"></script>
</head>
<body>
	<%
		System.out.println("SinhVien.jsp start");
		Object[][] sinhVienData = Main.sinhVienDAO.getObjectMatrix();
		String[] headers = new String[] {"Mã Sinh Viên", "Tên Sinh Viên", "Giới Tính", "Ngày Sinh", "Khoa", "Địa Chỉ", "Ghi Chú"};
		
	%>
	<div class="filter-container">
        <label for="yearFilter">Nhập năm:</label>
        <input type="text" id="yearFilter" placeholder="Nhập năm học..."> 
        <button onclick="getStudentByYear()">Lọc</button>
    </div>
	

 	 <div id="sinhVien-table-container" class="table-container" data-object-type="SinhVien">
        <!-- Table will be loaded here -->
    </div>
    
 	<div id = "button-container">	
	    <div class="crud-buttons">
	    	<button onclick="viewDanhSachHocPhan()">Xem danh sách học phần</button>
	        <button onclick="createSinhVien()">Create</button>
	        <button onclick="updateSinhVien()">Update</button>
	        <button onclick="deleteSinhVien()">Delete</button>
		</div>
	</div>
	
<script>
let selectedRow = null;
$(document).ready(function() {

    reloadTableData('SinhVien');

    function getStudentByYear() {
    	console.log("GetStudentByYear");
    	
    	const year = document.getElementById("yearFilter").value;
        
        if (year === "") {
            reloadTableData('SinhVien');
        }
        else {
        	if (!year || isNaN(year)) { // check if the value of year is a valid number
            	alert("Vui lòng nhập năm hợp lệ.");
            	return;
            }
        	reloadTableDataOfDKHP('SinhVien', { year: year, action: 'getStudentByYear' });
        }
        
    }
    
    function viewDanhSachHocPhan() {
		console.log("ViewDanhSachHocPhan");
    	
    	if (selectedRow == null) {
            alert("Vui lòng chọn một sinh viên để xem Danh Sach Hoc Phan.");
            return;
        }
    	
    	// 2. Get maHP from the selected row
        const maSV = selectedRow.find('td:eq(0)').text();
    	<%System.out.println("In SinhVien.jsp");%>
    	console.log(maSV + " in SinhVien.jsp");

        const queryParams = new URLSearchParams();
        queryParams.append('maSV', maSV.toString());
        queryParams.append('objectType', 'SinhVien');
        
        window.location.href = contextPath + '/danhSachHocPhanOfSinhVien.jsp?' + queryParams;
    	selectedRow = null;
    }
    
    function createSinhVien() {
        console.log("Create SinhVien");
        
     	// 2. Gather data from the selected row
     //   const rowData = Array.from(selectedRow.find('td'), td => td.textContent.trim());
        const headers = Array.from($('#dynamicTable th'), th => th.textContent.trim());
        
        // 3. Construct query string for passing data to the update form
        const queryParams = new URLSearchParams();
        queryParams.append('action', 'create');
        queryParams.append('objectType', 'SinhVien');
    	// Append headers data to query params
        queryParams.append('headers', headers.toString());

	//        for (let i = 0; i < rowData.length; i++) {
    //        queryParams.append(headers[i], rowData[i]);
    //    }
        
        window.location.href = contextPath + '/createUpdateForm.jsp?' + queryParams.toString();
    }

    function updateSinhVien() {
        console.log("Update SinhVien");
 		
    	if (selectedRow == null) {
            alert("Vui lòng chọn một sinh viên để update.");
            return;
        }
    	
    	// 2. Gather data from the selected row
        const rowData = Array.from(selectedRow.find('td'), td => td.textContent.trim());
        const headers = Array.from($('#dynamicTable th'), th => th.textContent.trim());
        
        // 3. Construct query string for passing data to the update form
        const queryParams = new URLSearchParams();
        queryParams.append('action', 'update');
        queryParams.append('objectType', 'SinhVien');
    	// Append headers data to query params
        queryParams.append('headers', headers.toString());

        for (let i = 0; i < rowData.length; i++) {
            queryParams.append(headers[i], rowData[i]);
        }
    
  //      const maSinhVien = selectedRow.find('td:eq(0)').text();
	
        if (selectedRow) {
 //         window.location.href = contextPath + '/createUpdateForm.jsp?action=update&objectType=SinhVien&id=' + encodeURIComponent(maSinhVien);
            
 			window.location.href = contextPath + '/createUpdateForm.jsp?' + queryParams.toString();
 			selectedRow = null;
        } else {
            alert("Vui lòng chọn một sinh viên để cập nhật.");
        }
    }

    function deleteSinhVien() {
        console.log("Delete SinhVien");
        
        if (selectedRow == null) {
            alert("Vui lòng chọn một sinh viên để xóa.");
            return;
        }
        const maSinhVien = selectedRow.find('td:eq(0)').text();

        if (!confirm("Bạn có chắc chắn muốn xóa sinh viên này không?")) {
            return; 
        }

        // 3. Send AJAX POST request to SinhVienController
        fetch(contextPath + '/SinhVienController', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/x-www-form-urlencoded' 
            },
            body: new URLSearchParams({
                action: 'delete',
                maSinhVien: encodeURIComponent(maSinhVien)
            })
         })
        .then(response => {
            if (response.ok) {
                alert("Sinh viên đã bị xóa!");
                reloadTableData('SinhVien'); 
                
            } else {
                alert("Lỗi khi xóa sinh viên: " + response.statusText);
            }
        })
        .catch(error => {
            console.error('Error:', error);
            alert("Lỗi khi gửi yêu cầu.");
        });
        selectedRow = null;
    }

    // Attach functions to window object to make them accessible
    window.createSinhVien = createSinhVien;
    window.updateSinhVien = updateSinhVien;
    window.deleteSinhVien = deleteSinhVien;
    window.viewDanhSachHocPhan = viewDanhSachHocPhan;
    window.getStudentByYear = getStudentByYear;
});
</script>	

</body>	
</html>