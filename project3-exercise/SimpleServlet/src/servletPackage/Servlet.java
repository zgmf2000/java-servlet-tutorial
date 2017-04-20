package servletPackage;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.SingleThreadModel;
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
@MultipartConfig
public class Servlet extends HttpServlet implements SingleThreadModel {
	private static final long serialVersionUID = 1L;
	private List<Student> students = new ArrayList<>();
	private int count = 0;
	
	public Servlet()
	{
		students.add(new Student(1, "Ariana"));
		students.add(new Student(2, "Ariadne"));
		students.add(new Student(3, "Aria"));
		students.add(new Student(4, "Tony"));
		students.add(new Student(5, "Terry"));
	}
	
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
	@SuppressWarnings("unchecked")
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String message = "Hello from server.";
		
		JSONObject obj = new JSONObject();
		JSONArray studentData = new JSONArray();
		
		for (Student s:students)
		{
			JSONObject student = new JSONObject();
			student.put("id", s.getId());
			student.put("name", s.getName());
			studentData.add(student);
		}
		
		obj.put("data", studentData);
		obj.put("message", message);
	
		response.getWriter().write(obj.toJSONString());
	}
	
	@SuppressWarnings("unchecked")
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		String filter = request.getParameter("name");
		JSONArray studentData = new JSONArray();
		
		for (Student s:students)
		{
			if (s.getName().toLowerCase().contains(filter.toLowerCase()))
			{
				JSONObject student = new JSONObject();
				student.put("id", s.getId());
				student.put("name", s.getName());
				studentData.add(student);
			}
		}
		
		if (studentData.isEmpty())
			response.sendError(404);
		else
			response.getWriter().write(studentData.toJSONString());
	}

}
