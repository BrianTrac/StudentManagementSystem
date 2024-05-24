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
	System.out.println("MonHoc.jsp start");
	Object[][] monHocData = Main.monHocDAO.getObjectMatrix();
	String[] headers = new String[] {"Mã Môn Học", "Tên Môn Học", "SốTC"};
	
	%>

	<div id="monHoc-table-container" class="table-container"
		data-object-type="MonHoc">
		<!-- Table will be loaded here -->
	</div>

	<div id="button-container">
		<div class="crud-buttons">
			<button onclick="createMonHoc()">Create</button>
			<button onclick="updateMonHoc()">Update</button>
			<button onclick="deleteMonHoc()">Delete</button>
		</div>
	</div>

	<script>
$(document).ready(function() {

    reloadTableData('MonHoc');

    function createMonHoc() {
        console.log("Create MonHoc");
        
     	// 2. Gather data from the selected row
     //   const rowData = Array.from(selectedRow.find('td'), td => td.textContent.trim());
        const headers = Array.from($('#dynamicTable th'), th => th.textContent.trim());
        
        // 3. Construct query string for passing data to the update form
        const queryParams = new URLSearchParams();
        queryParams.append('action', 'create');
        queryParams.append('objectType', 'MonHoc');
    	// Append headers data to query params
        queryParams.append('headers', headers.toString());
        
        window.location.href = contextPath + '/createUpdateForm.jsp?' + queryParams.toString();
    }

    function updateMonHoc() {
        console.log("Update MonHoc");
 		
    	if (selectedRow == null) {
            alert("Vui lòng chọn một môn học để update.");
            return;
        }
    	
    	// 2. Gather data from the selected row
        const rowData = Array.from(selectedRow.find('td'), td => td.textContent.trim());
        const headers = Array.from($('#dynamicTable th'), th => th.textContent.trim());
        
        // 3. Construct query string for passing data to the update form
        const queryParams = new URLSearchParams();
        queryParams.append('action', 'update');
        queryParams.append('objectType', 'MonHoc');
    	// Append headers data to query params
        queryParams.append('headers', headers.toString());

        for (let i = 0; i < rowData.length; i++) {
            queryParams.append(headers[i], rowData[i]);
        }
    
 
        if (selectedRow) {       
 			window.location.href = contextPath + '/createUpdateForm.jsp?' + queryParams.toString();
 			selectedRow = null;
        } else {
            alert("Vui lòng chọn một môn học để cập nhật.");
        }
    }

    function deleteMonHoc() {
        console.log("Delete MonHoc");
        
        if (selectedRow == null) {
            alert("Vui lòng chọn một môn học để xóa.");
            return;
        }
        const maMonHoc = selectedRow.find('td:eq(0)').text();

        if (!confirm("Bạn có chắc chắn muốn xóa môn học này không?")) {
            return; 
        }

        // 3. Send AJAX POST request to MonHocController
        fetch(contextPath + '/MonHocController', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/x-www-form-urlencoded' 
            },
            body: new URLSearchParams({
                action: 'delete',
                maMonHoc: encodeURIComponent(maMonHoc)
            })
         })
        .then(response => {
            if (response.ok) {
                alert("Môn học đã bị xóa!");
                reloadTableData('MonHoc'); 
                
            } else {
                alert("Lỗi khi xóa môn học: " + response.statusText);
            }
        })
        .catch(error => {
            console.error('Error:', error);
            alert("Lỗi khi gửi yêu cầu.");
        });
        selectedRow = null;
    }

    // Attach functions to window object to make them accessible
    window.createMonHoc = createMonHoc;
    window.updateMonHoc = updateMonHoc;
    window.deleteMonHoc = deleteMonHoc;
});
</script>

</body>
</html>