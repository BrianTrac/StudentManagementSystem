function reloadTableData(objectType) {

	const url = contextPath + '/TableController?objectType=' + encodeURIComponent(objectType);
	
	console.log(contextPath);
	console.log(url);
	console.log(objectType)
	
	fetch(url, {method: 'GET'})
		.then(response => {
			if (!response.ok) {
				throw new Error('Failed to reload ${objectType} data. Server responded with status: ${response.status}');
			}
			return response.text();
		})
		.then(newTableHtml => {
			console.log("Test newTableHtml in reloadTableData");
			console.log(newTableHtml);
		//	$('#dynamicTable').html($(newTableHtml).find('#dynamicTable').html());
			// Target the specific table container based on the objectType
            const tableContainer = document.querySelector(`[data-object-type="${objectType}"]`);
            if (tableContainer) {
                tableContainer.innerHTML = newTableHtml;
                
                // Reattach event handlers for the new table
                attachTableEventHandlers(objectType);
            }
            
             if (!$('#result-container').length) {
		        console.error('Element with ID result-container not found');
		        return;
		    }
		    $('#result-container').html(""); 
		    // Log to the console to confirm if the element was emptied
		    console.log("result-container emptied successfully!"); 
		})
		.catch(error => {
            console.error('Error reloading table:', error);
            alert(`Có lỗi xảy ra khi tải lại bảng dữ liệu ${objectType}. Vui lòng thử lại.`); 
        });
}

let selectedRow = null;

function handleRowClick(row) {
	
	// Remove highlight from all rows
    $('#dynamicTable tbody tr').removeClass('highlighted-row');
    // Add highlight to the clicked row
    $(row).addClass('highlighted-row');
	
	selectedRow = $(row);
	
    const rowData = Array.from($(row).find('td'), td => td.textContent);
    const headers = Array.from($('#dynamicTable th'), th => th.textContent);
	
    // Build Query String Manually with Enhanced Encoding
    let queryString = '';
    for (let i = 0; i < rowData.length; i++) {
        const key = encodeURIComponent(headers[i]);
        const value = encodeURIComponent(rowData[i]);
        queryString += (i > 0 ? '&' : '') + key + '=' + value;
    }

    fetch('UpdateInfoServlet?' + queryString, {
        method: 'GET'
    })
    .then(response => {
        if (!response.ok) {
            throw new Error('Failed to retrieve data');
        }
        return response.text();
    })
    .then(responseText => {
        $('.info-container').html(responseText);
    })
    .catch(error => {
        alert(error.message);
    });
}

function attachTableEventHandlers(objectType) {
    // Sorting functionality
    $('th.sortable').click(function(){
        let table = $(this).parents('table').eq(0)
        let rows = table.find('tr:gt(0)').toArray().sort(comparer($(this).index()))
        this.asc = !this.asc
        if (!this.asc){rows = rows.reverse()}
        for (let i = 0; i < rows.length; i++){table.append(rows[i])}
        $('th').removeClass('asc desc');
        $(this).addClass(this.asc ? 'asc' : 'desc');
    })
    
    function comparer(index) {
        return function(a, b) {
            let valA = getCellValue(a, index), valB = getCellValue(b, index)
            return $.isNumeric(valA) && $.isNumeric(valB) ? valA - valB : valA.localeCompare(valB)
        }
    }
    
    function getCellValue(row, index){ 
    	return $(row).children('td').eq(index).text() 
    }

    // Search functionality
    $("#searchInput").on("keyup", function() {
        let value = $(this).val().toLowerCase();
        $("#dynamicTable tbody tr").filter(function() {
            $(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
        });
    });
    
    // Row clicked
    $('#dynamicTable tbody tr').click(function() {
        handleRowClick(this);
    });
}

		