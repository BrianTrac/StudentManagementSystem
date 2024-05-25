<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="brian.Main"%>
<%@ page import="java.util.Arrays"%>


<!DOCTYPE html>
<html lang="vi">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.1">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script>
        // Pass the context path from JSP to JavaScript
        const contextPath = '<%=request.getContextPath()%>';
</script>

<script src="reloadTableData.js"></script>
</head>
<body>
	<%
	System.out.println("HocPhan.jsp start");
	Object[][] hocPhanData = Main.hocPhanDAO.getObjectMatrix();
	String[] headers = new String[] {"Mã HP", "Mã MH", "Mã Lớp", "Mã HK", "Mã GV", "Sĩ Số", "Đã ĐK", "Khóa", "Thứ", "Khung giờ", "Phòng", "Địa Điểm"};

	%>

	<div id="hocPhan-table-container" class="table-container"
		data-object-type="HocPhan">
		<!-- Table will be loaded here -->
	</div>

	<div class="button-container">
		<div class="crud-buttons">
			<button onclick="viewDanhSachSinhVien()">Xem danh sách sinh viên đăng kí</button>
			<button onclick="createHocPhan()">Create</button>
			<button onclick="updateHocPhan()">Update</button>
			<button onclick="deleteHocPhan()">Delete</button>
		</div>
	</div>

<script>

let selectedRow = null;
$(document).ready(function() {

    reloadTableData('HocPhan');
    
    function viewDanhSachSinhVien() {
    	console.log("ViewDanhSachSinhVien");
    	
    	if (selectedRow == null) {
            alert("Vui lòng chọn một học phần để xem Danh Sach Sinh Vien.");
            return;
        }
    	
    	// 2. Get maHP from the selected row
        const maHP = selectedRow.find('td:eq(0)').text();
    	<%System.out.println("In HocPhan.jsp");%>
    	console.log(maHP + "in HocPhan.jsp");

        const queryParams = new URLSearchParams();
        queryParams.append('maHP', maHP.toString());
        queryParams.append('objectType', 'HocPhan');
        
        window.location.href = contextPath + '/danhSachSinhVienDangKy.jsp?' + queryParams;
    	selectedRow = null;
    }

    function createHocPhan() {
        console.log("Create HocPhan");
        
     	// 2. Gather data from the selected row
     //   const rowData = Array.from(selectedRow.find('td'), td => td.textContent.trim());
        const headers = Array.from($('#dynamicTable th'), th => th.textContent.trim());
        
        // 3. Construct query string for passing data to the update form
        const queryParams = new URLSearchParams();
        queryParams.append('action', 'create');
        queryParams.append('objectType', 'HocPhan');
    	// Append headers data to query params
        queryParams.append('headers', headers.toString());
        
        window.location.href = contextPath + '/createUpdateForm.jsp?' + queryParams.toString();
    }

    function updateHocPhan() {
        console.log("Update HocPhan");
 		
    	if (selectedRow == null) {
            alert("Vui lòng chọn một học phần để update.");
            return;
        }
    	
    	// 2. Gather data from the selected row
        const rowData = Array.from(selectedRow.find('td'), td => td.textContent.trim());
        const headers = Array.from($('#dynamicTable th'), th => th.textContent.trim());
        
        // 3. Construct query string for passing data to the update form
        const queryParams = new URLSearchParams();
        queryParams.append('action', 'update');
        queryParams.append('objectType', 'HocPhan');
    	// Append headers data to query params
        queryParams.append('headers', headers.toString());

        for (let i = 0; i < rowData.length; i++) {
            queryParams.append(headers[i], rowData[i]);
        }
    
 
        if (selectedRow) {       
 			window.location.href = contextPath + '/createUpdateForm.jsp?' + queryParams.toString();
 			selectedRow = null;
        } else {
            alert("Vui lòng chọn một học phần để cập nhật.");
        }
    }

    function deleteHocPhan() {
        console.log("Delete HocPhan");
        
        if (selectedRow == null) {
            alert("Vui lòng chọn một học phần để xóa.");
            return;
        }
        const maHocPhan = selectedRow.find('td:eq(0)').text();

        if (!confirm("Bạn có chắc chắn muốn xóa học phần này không?")) {
            return; 
        }

        // 3. Send AJAX POST request to HocPhanController
        fetch(contextPath + '/HocPhanController', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/x-www-form-urlencoded' 
            },
            body: new URLSearchParams({
                action: 'delete',
                maHocPhan: encodeURIComponent(maHocPhan)
            })
         })
        .then(response => {
            if (response.ok) {
                alert("Học phần đã bị xóa!");
                reloadTableData('HocPhan'); 
                
            } else {
                alert("Lỗi khi xóa học phần: " + response.statusText);
            }
        })
        .catch(error => {
            console.error('Error:', error);
            alert("Lỗi khi gửi yêu cầu.");
        });
        selectedRow = null;
    }

    // Attach functions to window object to make them accessible
    window.createHocPhan = createHocPhan;
    window.updateHocPhan = updateHocPhan;
    window.deleteHocPhan = deleteHocPhan;
    window.viewDanhSachSinhVien = viewDanhSachSinhVien;
});
</script>

</body>
</html>