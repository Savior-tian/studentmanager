package control;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entity.Student;
import model.SelectStudent;

public class UpStudentServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String id = request.getParameter("id");
		if (id == null) {
			request.setAttribute("error", "Missing student id.");
			request.getRequestDispatcher("/jsp/error.jsp").forward(request, response);
			return;
		}
		Integer studentId = Integer.valueOf(id);

		SelectStudent model = new SelectStudent();
		Student student = model.load(studentId);
		if (student == null) {
			request.setAttribute("error", "Student record was not found.");
			request.getRequestDispatcher("/jsp/error.jsp").forward(request, response);
			return;
		}
		request.setAttribute("student", student);
		request.getRequestDispatcher("/jsp/studentupdate.jsp").forward(request, response);
	}
}
