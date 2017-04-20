package servletPackage;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.SingleThreadModel;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Servlet
 */
@WebServlet(description = "This is a sample servlet", urlPatterns = { "/Servlet" })
public class Servlet extends HttpServlet implements Java {
	private static final long serialVersionUID = 1L;

	private int count = 0;
	
	protected synchronized void increment()
	{
		count++;
	}
	
	protected synchronized void decrement()
	{
		count--;
	}
	
	protected synchronized int getCount()
	{
		return count;
	}
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("Test");
		String searchTerm = request.getParameter("searchTerm");
		PrintWriter writer = response.getWriter();
		writer.printf("You searched for %s", searchTerm);
	}

}
