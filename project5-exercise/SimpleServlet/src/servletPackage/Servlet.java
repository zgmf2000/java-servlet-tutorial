package servletPackage;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 * Servlet implementation class Servlet
 */
@WebServlet(description = "This is a sample servlet", urlPatterns = { "/StudentServlet" })
public class Servlet extends HttpServlet implements javax.servlet.Servlet {
	private static final long serialVersionUID = 1L;
	private List<Book> students = new ArrayList<>();
	private int id = 1;
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		parseResponse(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		
		if (request.getParameterMap().containsKey("name"))
		{
			String name = request.getParameter("name");
			students.add(new Book(id, name));
			id++;
		}
		else if (request.getParameterMap().containsKey("deleteTarget"))
		{
			String target = request.getParameter("deleteTarget");
			students.removeIf(e->e.getId()==Integer.parseInt(target));
		}

		parseResponse(request, response);
	}
	
	private void parseResponse(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		request.getSession().setAttribute("students", students);
		request.getRequestDispatcher("/hello.jsp").forward(request, response);
	}
}
