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
	System.out.println("LopHoc.jsp start");
	Object[][] lopHocData = Main.lopDAO.getObjectMatrix();
	String[] headers = new String[] {"Mã Lớp", "Tên Lớp", "SôSV"}; 
	%>

	<div id="lopHoc-table-container" class="table-container"
		data-object-type="LopHoc">
		<!-- Table will be loaded here -->
	</div>

	<div id="button-container">
		<div class="crud-buttons">
			<button onclick="createLopHoc()">Create</button>
			<button onclick="updateLopHoc()">Update</button>
			<button onclick="deleteLopHoc()">Delete</button>
		</div>
	</div>

	<script>
$(document).ready(function() {

    reloadTableData('LopHoc');

    function createLopHoc() {
        console.log("Create LopHoc");
        
     	// 2. Gather data from the selected row
     //   const rowData = Array.from(selectedRow.find('td'), td => td.textContent.trim());
        const headers = Array.from($('#dynamicTable th'), th => th.textContent.trim());
        
        // 3. Construct query string for passing data to the update form
        const queryParams = new URLSearchParams();
        queryParams.append('action', 'create');
        queryParams.append('objectType', 'LopHoc');
    	// Append headers data to query params
        queryParams.append('headers', headers.toString());
        
        window.location.href = contextPath + '/createUpdateForm.jsp?' + queryParams.toString();
    }

    function updateLopHoc() {
        console.log("Update LopHoc");
 		
    	if (selectedRow == null) {
            alert("Vui lòng chọn một lớp học để update.");
            return;
        }
    	
    	// 2. Gather data from the selected row
        const rowData = Array.from(selectedRow.find('td'), td => td.textContent.trim());
        const headers = Array.from($('#dynamicTable th'), th => th.textContent.trim());
        
        // 3. Construct query string for passing data to the update form
        const queryParams = new URLSearchParams();
        queryParams.append('action', 'update');
        queryParams.append('objectType', 'LopHoc');
    	// Append headers data to query params
        queryParams.append('headers', headers.toString());

        for (let i = 0; i < rowData.length; i++) {
            queryParams.append(headers[i], rowData[i]);
        }
    
 
        if (selectedRow) {       
 			window.location.href = contextPath + '/createUpdateForm.jsp?' + queryParams.toString();
 			selectedRow = null;
        } else {
            alert("Vui lòng chọn một lớp học để cập nhật.");
        }
    }

    function deleteLopHoc() {
        console.log("Delete LopHoc");
        
        if (selectedRow == null) {
            alert("Vui lòng chọn một lớp học để xóa.");
            return;
        }
        const maLopHoc = selectedRow.find('td:eq(0)').text();

        if (!confirm("Bạn có chắc chắn muốn xóa lớp học này không?")) {
            return; 
        }

        // 3. Send AJAX POST request to LopHocController
        fetch(contextPath + '/LopHocController', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/x-www-form-urlencoded' 
            },
            body: new URLSearchParams({
                action: 'delete',
                maLopHoc: encodeURIComponent(maLopHoc)
            })
         })
        .then(response => {
            if (response.ok) {
                alert("Lớp học đã bị xóa!");
                reloadTableData('LopHoc'); 
                
            } else {
                alert("Lỗi khi xóa lớp học: " + response.statusText);
            }
        })
        .catch(error => {
            console.error('Error:', error);
            alert("Lỗi khi gửi yêu cầu.");
        });
        selectedRow = null;
    }

    // Attach functions to window object to make them accessible
    window.createLopHoc = createLopHoc;
    window.updateLopHoc = updateLopHoc;
    window.deleteLopHoc = deleteLopHoc;
});
</script>

</body>
</html>