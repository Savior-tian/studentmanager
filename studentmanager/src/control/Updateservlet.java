package control;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.StudentModel;
import entity.Student;

public class Updateservlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public Updateservlet() {
		super();
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
       this.doPost(request, response);
    }

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		    request.setCharacterEncoding("gbk");
		 		
	 			// 得到传入参数：学生ID
			    String id = request.getParameter("id");
			if (null==id)
			{
				request.setAttribute("error", "没有该学生！");
				request.getRequestDispatcher("/jsp/error.jsp").forward(request, response);
				
				return ;
			}
			Integer studentId = Integer.valueOf(id);
			// 调用查询方法，得到学生数据
     		StudentModel model = new StudentModel();

			Student student = model.load(studentId);
			if (null==student)
			{
				request.setAttribute("error", "没有指定编号的学生记录");
				request.getRequestDispatcher("/jsp/error.jsp").forward(request, response);
				return ;
			}
			// 将管理员数据保存到request中
			request.setAttribute("student", student);
			// 转发到student.jsp
			request.getRequestDispatcher("/jsp/studentupdate.jsp").forward(request, response);
	
	}

}
