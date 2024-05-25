<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="brian.Main" %>
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
		System.out.println("GiaoVu.jsp start");
		Object[][] giaoVuData = Main.giaoVuDAO.getObjectMatrix();
		String[] headers  = new String[] {"Mã Giáo Vụ", "Tên Giáo Vụ", "Giới Tính", "Ngày Sinh", "Địa Chỉ"};
	%>

 	 <div id="giaoVu-table-container" class="table-container" data-object-type="GiaoVu">
        <!-- Table will be loaded here -->
    </div>
    
 	<div id = "button-container">	
	    <div class="crud-buttons">
	        <button onclick="createGiaoVu()">Create</button>
	        <button onclick="updateGiaoVu()">Update</button>
	        <button onclick="deleteGiaoVu()">Delete</button>
		</div>
	</div>
	
<script>
let selectedRow = null;
$(document).ready(function() {

    reloadTableData('GiaoVu');

    function createGiaoVu() {
        console.log("Create GiaoVu");
        
     	// 2. Gather data from the selected row
     //   const rowData = Array.from(selectedRow.find('td'), td => td.textContent.trim());
        const headers = Array.from($('#dynamicTable th'), th => th.textContent.trim());
        
        // 3. Construct query string for passing data to the update form
        const queryParams = new URLSearchParams();
        queryParams.append('action', 'create');
        queryParams.append('objectType', 'GiaoVu');
    	// Append headers data to query params
        queryParams.append('headers', headers.toString());
        
        window.location.href = contextPath + '/createUpdateForm.jsp?' + queryParams.toString();
    }

    function updateGiaoVu() {
        console.log("Update GiaoVu");
 		
    	if (selectedRow == null) {
            alert("Vui lòng chọn một giáo vụ để update.");
            return;
        }
    	
    	// 2. Gather data from the selected row
        const rowData = Array.from(selectedRow.find('td'), td => td.textContent.trim());
        const headers = Array.from($('#dynamicTable th'), th => th.textContent.trim());
        
        // 3. Construct query string for passing data to the update form
        const queryParams = new URLSearchParams();
        queryParams.append('action', 'update');
        queryParams.append('objectType', 'GiaoVu');
    	// Append headers data to query params
        queryParams.append('headers', headers.toString());

        for (let i = 0; i < rowData.length; i++) {
            queryParams.append(headers[i], rowData[i]);
        }
    
        if (selectedRow) {        
 			window.location.href = contextPath + '/createUpdateForm.jsp?' + queryParams.toString();
 			selectedRow = null;
        } else {
            alert("Vui lòng chọn một giáo vụ để cập nhật.");
        }
    }

    function deleteGiaoVu() {
        console.log("Delete GiaoVu");
        
        if (selectedRow == null) {
            alert("Vui lòng chọn một giáo vụ để xóa.");
            return;
        }
        const maGiaoVu = selectedRow.find('td:eq(0)').text();

        if (!confirm("Bạn có chắc chắn muốn xóa giáo vụ này không?")) {
            return; 
        }

        // 3. Send AJAX POST request to GiaoVuController
        fetch(contextPath + '/GiaoVuController', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/x-www-form-urlencoded' 
            },
            body: new URLSearchParams({
                action: 'delete',
                maGiaoVu: encodeURIComponent(maGiaoVu)
            })
         })
        .then(response => {
            if (response.ok) {
                alert("Giáo vụ đã bị xóa!");
                reloadTableData('GiaoVu'); 
                
            } else {
                alert("Lỗi khi xóa giáo vụ: " + response.statusText);
            }
        })
        .catch(error => {
            console.error('Error:', error);
            alert("Lỗi khi gửi yêu cầu.");
        });
        selectedRow = null;
    }

    // Attach functions to window object to make them accessible
    window.createGiaoVu = createGiaoVu;
    window.updateGiaoVu = updateGiaoVu;
    window.deleteGiaoVu = deleteGiaoVu;
});
</script>	

</body>	
</html>