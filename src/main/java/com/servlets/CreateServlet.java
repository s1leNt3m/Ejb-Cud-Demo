package com.servlets;

import java.io.IOException;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.entities.Student;
import com.services.StudentServiceRemote;

/**
 * Servlet implementation class CreateServlet
 */
@WebServlet("/CreateServlet")
public class CreateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreateServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    @EJB
    StudentServiceRemote studentService;
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().write("List after add student \n");
		Student student = new Student();
		student.setFullName(request.getParameter("fullName"));
		student.setAge(Integer.parseInt(request.getParameter("age")));
		studentService.create(student);
		response.getWriter().write("List after add student \n");
		List<Student> students = studentService.findAll();
		for (Student s : students) {
			response.getWriter().write("Id:" + s.getId() + " Fullname: " + s.getFullName() +" Age: " + s.getAge()+"\n");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
