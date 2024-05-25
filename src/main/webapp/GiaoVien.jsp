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
	System.out.println("GiaoVien.jsp start");
	Object[][] giaoVienData = Main.giaoVienDAO.getObjectMatrix();
	String[] headers = new String[]{"Mã Giáo Viên", "Tên Giáo Viên", "Giới Tính", "Ngày Sinh", "Khoa", "Địa Chỉ",
			"Ghi Chú"};
	%>

	<div id="giaoVien-table-container" class="table-container"
		data-object-type="GiaoVien">
		<!-- Table will be loaded here -->
	</div>

	<div id="button-container">
		<div class="crud-buttons">
			<button onclick="createGiaoVien()">Create</button>
			<button onclick="updateGiaoVien()">Update</button>
			<button onclick="deleteGiaoVien()">Delete</button>
		</div>
	</div>

	<script>
	let selectedRow = null;
$(document).ready(function() {

    reloadTableData('GiaoVien');

    function createGiaoVien() {
        console.log("Create GiaoVien");
        
     	// 2. Gather data from the selected row
     //   const rowData = Array.from(selectedRow.find('td'), td => td.textContent.trim());
        const headers = Array.from($('#dynamicTable th'), th => th.textContent.trim());
        
        // 3. Construct query string for passing data to the update form
        const queryParams = new URLSearchParams();
        queryParams.append('action', 'create');
        queryParams.append('objectType', 'GiaoVien');
    	// Append headers data to query params
        queryParams.append('headers', headers.toString());
        
        window.location.href = contextPath + '/createUpdateForm.jsp?' + queryParams.toString();
    }

    function updateGiaoVien() {
        console.log("Update GiaoVien");
 		
    	if (selectedRow == null) {
            alert("Vui lòng chọn một giáo viên để update.");
            return;
        }
    	
    	// 2. Gather data from the selected row
        const rowData = Array.from(selectedRow.find('td'), td => td.textContent.trim());
        const headers = Array.from($('#dynamicTable th'), th => th.textContent.trim());
        
        // 3. Construct query string for passing data to the update form
        const queryParams = new URLSearchParams();
        queryParams.append('action', 'update');
        queryParams.append('objectType', 'GiaoVien');
    	// Append headers data to query params
        queryParams.append('headers', headers.toString());

        for (let i = 0; i < rowData.length; i++) {
            queryParams.append(headers[i], rowData[i]);
        }
    
 
        if (selectedRow) {       
 			window.location.href = contextPath + '/createUpdateForm.jsp?' + queryParams.toString();
 			selectedRow = null;
        } else {
            alert("Vui lòng chọn một giáo viên để cập nhật.");
        }
    }

    function deleteGiaoVien() {
        console.log("Delete GiaoVien");
        
        if (selectedRow == null) {
            alert("Vui lòng chọn một giáo viên để xóa.");
            return;
        }
        const maGiaoVien = selectedRow.find('td:eq(0)').text();

        if (!confirm("Bạn có chắc chắn muốn xóa giáo viên này không?")) {
            return; 
        }

        // 3. Send AJAX POST request to GiaoVienController
        fetch(contextPath + '/GiaoVienController', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/x-www-form-urlencoded' 
            },
            body: new URLSearchParams({
                action: 'delete',
                maGiaoVien: encodeURIComponent(maGiaoVien)
            })
         })
        .then(response => {
            if (response.ok) {
                alert("Giáo viên đã bị xóa!");
                reloadTableData('GiaoVien'); 
                
            } else {
                alert("Lỗi khi xóa giáo viên: " + response.statusText);
            }
        })
        .catch(error => {
            console.error('Error:', error);
            alert("Lỗi khi gửi yêu cầu.");
        });
        selectedRow = null;
    }

    // Attach functions to window object to make them accessible
    window.createGiaoVien = createGiaoVien;
    window.updateGiaoVien = updateGiaoVien;
    window.deleteGiaoVien = deleteGiaoVien;
});
</script>

</body>
</html>