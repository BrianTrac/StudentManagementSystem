package brian.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/UpdateInfoServlet")
public class UpdateInfoController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Retrieve the parameters and store them in a map
		Map<String, String> rowData = new HashMap<>();
        for (Map.Entry<String, String[]> entry : request.getParameterMap().entrySet()) {
            if (entry.getValue().length > 0) {
                rowData.put(entry.getKey(), entry.getValue()[0]);
            }
        }

        // Set the data as a request attribute
        request.setAttribute("rowData", rowData);

        // Forward to info.jsp
        request.getRequestDispatcher("info.jsp").forward(request, response);
    }
}
