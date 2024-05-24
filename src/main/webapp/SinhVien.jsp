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
	<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
	<script>
        // Pass the context path from JSP to JavaScript
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

 	 <div id="sinhVien-table-container" class="table-container" data-object-type="SinhVien">
        <!-- Table will be loaded here -->
    </div>
    
 	<div id = "button-container">	
	    <div class="crud-buttons">
	        <button onclick="createSinhVien()">Create</button>
	        <button onclick="updateSinhVien()">Update</button>
	        <button onclick="deleteSinhVien()">Delete</button>
		</div>
	</div>
	
<script>
$(document).ready(function() {

    reloadTableData('SinhVien');

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
});
</script>	

</body>	
</html>