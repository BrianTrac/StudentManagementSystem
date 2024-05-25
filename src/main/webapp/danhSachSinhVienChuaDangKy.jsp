<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <title>Danh sách sinh viên đăng ký học phần</title>
    <link rel="stylesheet" href="tablestyle.css"> 
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script src="reloadTableData.js"></script> </head>
    <script>
        // Pass the context path from JSP to JavaScript
        const contextPath = '<%=request.getContextPath()%>';
    </script>
<body>
    <h2 id="courseNameHeading">Danh sách sinh viên chưa đăng ký học phần</h2> 
    <div id="studentListTable">
        <jsp:include page="Table.jsp"/> 
    </div>


    <div class="crud-buttons">
        <button onclick="addSinhVienToHocPhan()">Thêm sinh viên</button>
        <button onclick="goBackAndReload()">Quay lại</button>
    </div>

    <script>
        $(document).ready(function() {
            // Get the maHP from the query parameters
            const urlParams = new URLSearchParams(window.location.search);
            const maHP = urlParams.get('maHP');
			console.log("In DS SV DK");
			console.log("MaHP: ", maHP);
			
			const courseNameHeading = document.getElementById("courseNameHeading");
            courseNameHeading.textContent = "Danh sách sinh viên đăng ký chưa học phần " + maHP;
       
            // Load the table data
            reloadTableDataOfDangKi('SinhVien', { maHP: maHP, action: "viewSinhVienNotDangKi" }); 
             
        });
        
        function goBackAndReload() {
        	console.log("Back and Reload");
        	window.location.href = contextPath + '/MainScreen?item=HocPhan';
        }
		
        function addSinhVienToHocPhan() {
            console.log("Click addSinhVienToHocPhan");
            
            if (selectedRow == null) {
                alert("Vui lòng chọn một sinh viên để thêm.");
                return;
            }
        	
        	const maSV = selectedRow.find('td:eq(0)').text();
        	const urlParams = new URLSearchParams(window.location.search);
            const maHP = urlParams.get('maHP');
        	
        	fetch(contextPath + '/HocPhanController', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/x-www-form-urlencoded' 
                },
                body: new URLSearchParams({
                    action: 'addSinhVienToHocPhan',
                    maHP: encodeURIComponent(maHP),
                	maSV: encodeURIComponent(maSV)
                })
             })
            .then(response => {
                if (response.ok) {
                    alert("Sinh viên đã được thêm vào Học Phần!");
                    
                 	// Load the table data
                    reloadTableDataOfDangKi('SinhVien', { maHP: maHP, action: "viewSinhVienNotDangKi" }); 

                } else {
                    alert("Lỗi khi thêm sinh viên vào học phần: " + response.statusText);
                }
            })
            .catch(error => {
                console.error('Error:', error);
                alert("Lỗi khi gửi yêu cầu.");
            });
            selectedRow = null;

            
        }
    </script>
</body>
</html>