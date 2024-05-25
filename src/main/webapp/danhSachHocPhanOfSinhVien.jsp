<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <title>Danh sách học phần của sinh viên</title>
    <link rel="stylesheet" href="tablestyle.css"> 
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script src="reloadTableData.js"></script> </head>
    <script>
        // Pass the context path from JSP to JavaScript
        const contextPath = '<%=request.getContextPath()%>';
    </script>
<body>
    <h2 id="courseNameHeadingDSHPofSV">Danh sách học phần của sinh viên</h2> 
    <div id="DKHPListTable">
        <jsp:include page="Table.jsp"/> 
    </div>


    <div class="crud-buttons">
    	  <button onclick="goBackAndReload()">Quay lại</button>
    </div>

    <script>
        $(document).ready(function() {
            // Get the maHP from the query parameters
            const urlParams = new URLSearchParams(window.location.search);
            const maSV = urlParams.get('maSV');
			console.log("In DS HP of SV");
			
			const courseNameHeadingDSHPofSV = document.getElementById("courseNameHeadingDSHPofSV");
            courseNameHeadingDSHPofSV.textContent = "Danh sách học phần của sinh viên " + maSV;
       
            // Load the table data
            reloadTableDataOfDKHP('DKHP', { maSV: maSV, action: "viewDKHPofSV" });           
        
        });
        
        function goBackAndReload() {
        	console.log("Back and Reload");
        	window.location.href = contextPath + '/MainScreen?item=SinhVien';
        //	window.history.back({forceGet: true});
        }
    </script>
</body>
</html>