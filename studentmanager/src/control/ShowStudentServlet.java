package control;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.StudentModel;
import entity.Student;

public class ShowStudentServlet extends HttpServlet{
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");

		// �õ����������ѧ��ID
		String id = request.getParameter("id");
		if (null==id)
		{
			request.setAttribute("error", "Missing student id.");
			request.getRequestDispatcher("/jsp/error.jsp").forward(request, response);
			return ;
		}
		Integer studentId = Integer.valueOf(id);
		// ���ò�ѯ�������õ�ѧ������
 		StudentModel model = new StudentModel();

		Student student = model.load(studentId);
		if (null==student)
		{
			request.setAttribute("error", "Student record was not found.");
			request.getRequestDispatcher("/jsp/error.jsp").forward(request, response);
			return ;
		}
		// ��ѧ�����ݱ��浽request��
		request.setAttribute("student", student);
		// ת����student.jsp
		request.getRequestDispatcher("/jsp/studentshow.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.doGet(request, response);
	}

}
