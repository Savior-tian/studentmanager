package control;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.InsertStudent;

public class InsertStudentServlet extends HttpServlet {

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		int id = Integer.parseInt(request.getParameter("id"));
		String name = request.getParameter("name");
		String sex = request.getParameter("sex");
		int age = Integer.parseInt(request.getParameter("age"));
		String grade = request.getParameter("grade");
		float score = Float.parseFloat(request.getParameter("score"));

		InsertStudent model = new InsertStudent();
		model.insert(id, name, sex, age, grade, score);
		response.sendRedirect("ListStudentServlet.do");
	}
}
