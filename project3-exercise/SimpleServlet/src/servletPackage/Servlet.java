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
public class Servlet extends HttpServlet implements SingleThreadModel {
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
	@SuppressWarnings("unchecked")
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String message = "Hello from server.";
		
		List<Student> students = new ArrayList<Student>();
		students.add(new Student(1, "ABC"));
		students.add(new Student(2, "DEF"));
		
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

}
