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
	System.out.println("HocKi.jsp start");
	Object[][] hocKiData = Main.hocKiDAO.getObjectMatrix();
	String[] headers = new String[] {"Mã Học Kì", "Tên Học Kì", "Năm Học", "Ngày Bắt Đầu", "Ngày Kết Thúc"};
	
	%>

	<div id="hocKi-table-container" class="table-container"
		data-object-type="HocKi">
		<!-- Table will be loaded here -->
	</div>

	<div id="button-container">
		<div class="crud-buttons">
			<button onclick="createHocKi()">Create</button>
			<button onclick="updateHocKi()">Update</button>
			<button onclick="deleteHocKi()">Delete</button>
		</div>
	</div>

	<script>
	let selectedRow = null;
$(document).ready(function() {

    reloadTableData('HocKi');

    function createHocKi() {
        console.log("Create HocKi");
        
     	// 2. Gather data from the selected row
     //   const rowData = Array.from(selectedRow.find('td'), td => td.textContent.trim());
        const headers = Array.from($('#dynamicTable th'), th => th.textContent.trim());
        
        // 3. Construct query string for passing data to the update form
        const queryParams = new URLSearchParams();
        queryParams.append('action', 'create');
        queryParams.append('objectType', 'HocKi');
    	// Append headers data to query params
        queryParams.append('headers', headers.toString());
        
        window.location.href = contextPath + '/createUpdateForm.jsp?' + queryParams.toString();
    }

    function updateHocKi() {
        console.log("Update HocKi");
 		
    	if (selectedRow == null) {
            alert("Vui lòng chọn một học kì để update.");
            return;
        }
    	
    	// 2. Gather data from the selected row
        const rowData = Array.from(selectedRow.find('td'), td => td.textContent.trim());
        const headers = Array.from($('#dynamicTable th'), th => th.textContent.trim());
        
        // 3. Construct query string for passing data to the update form
        const queryParams = new URLSearchParams();
        queryParams.append('action', 'update');
        queryParams.append('objectType', 'HocKi');
    	// Append headers data to query params
        queryParams.append('headers', headers.toString());

        for (let i = 0; i < rowData.length; i++) {
            queryParams.append(headers[i], rowData[i]);
        }
    
 
        if (selectedRow) {       
 			window.location.href = contextPath + '/createUpdateForm.jsp?' + queryParams.toString();
 			selectedRow = null;
        } else {
            alert("Vui lòng chọn một học kì để cập nhật.");
        }
    }

    function deleteHocKi() {
        console.log("Delete HocKi");
        
        if (selectedRow == null) {
            alert("Vui lòng chọn một học kì để xóa.");
            return;
        }
        const maHocKi = selectedRow.find('td:eq(0)').text();

        if (!confirm("Bạn có chắc chắn muốn xóa học kì này không?")) {
            return; 
        }

        // 3. Send AJAX POST request to HocKiController
        fetch(contextPath + '/HocKiController', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/x-www-form-urlencoded' 
            },
            body: new URLSearchParams({
                action: 'delete',
                maHocKi: encodeURIComponent(maHocKi)
            })
         })
        .then(response => {
            if (response.ok) {
                alert("Học kì đã bị xóa!");
                reloadTableData('HocKi'); 
                
            } else {
                alert("Lỗi khi xóa học kì: " + response.statusText);
            }
        })
        .catch(error => {
            console.error('Error:', error);
            alert("Lỗi khi gửi yêu cầu.");
        });
        selectedRow = null;
    }

    // Attach functions to window object to make them accessible
    window.createHocKi = createHocKi;
    window.updateHocKi = updateHocKi;
    window.deleteHocKi = deleteHocKi;
});
</script>

</body>
</html>