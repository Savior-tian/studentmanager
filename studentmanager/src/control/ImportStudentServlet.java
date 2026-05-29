package control;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entity.Student;
import model.StudentModel;

public class ImportStudentServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getRequestDispatcher("/jsp/studentimport.jsp").forward(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String pastedContent = request.getParameter("pastedContent");
		String filePath = request.getParameter("filePath");

		List<Student> students = new ArrayList<Student>();
		try {
			if (pastedContent != null && pastedContent.trim().length() > 0) {
				students = parseRows(pastedContent);
			} else if (filePath != null && filePath.trim().length() > 0) {
				students = parseCsvFile(filePath.trim());
			} else {
				request.setAttribute("error", "Please paste Excel content or enter a CSV file path.");
				request.getRequestDispatcher("/jsp/studentimport.jsp").forward(request, response);
				return;
			}

			if (students.isEmpty()) {
				request.setAttribute("error", "No student data was parsed for import.");
				request.getRequestDispatcher("/jsp/studentimport.jsp").forward(request, response);
				return;
			}

			StudentModel model = new StudentModel();
			int importedCount = model.batchInsert(students);
			request.setAttribute("message", "Imported " + importedCount + " student records successfully.");
			request.setAttribute("studentlist", model.search());
			request.getRequestDispatcher("/jsp/studentlist.jsp").forward(request, response);
		} catch (Exception e) {
			request.setAttribute("error", e.getMessage());
			request.getRequestDispatcher("/jsp/studentimport.jsp").forward(request, response);
		}
	}

	private List<Student> parseRows(String content) {
		List<Student> students = new ArrayList<Student>();
		String[] rows = content.split("\\r?\\n");
		for (int index = 0; index < rows.length; index++) {
			String row = rows[index].trim();
			if (row.length() == 0) {
				continue;
			}
			students.add(parseStudent(row, index + 1));
		}
		return students;
	}

	private List<Student> parseCsvFile(String filePath) throws Exception {
		Exception utf8Failure = null;
		try {
			return parseCsvFile(filePath, StandardCharsets.UTF_8);
		} catch (Exception e) {
			utf8Failure = e;
		}
		try {
			return parseCsvFile(filePath, Charset.forName("GBK"));
		} catch (Exception e) {
			if (utf8Failure != null) {
				e.addSuppressed(utf8Failure);
			}
			throw e;
		}
	}

	private List<Student> parseCsvFile(String filePath, Charset charset) throws Exception {
		List<Student> students = new ArrayList<Student>();
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new InputStreamReader(new FileInputStream(filePath), charset));
			String line = null;
			int lineNumber = 0;
			while ((line = reader.readLine()) != null) {
				lineNumber++;
				line = line.trim();
				if (line.length() == 0) {
					continue;
				}
				students.add(parseStudent(line, lineNumber));
			}
		} finally {
			if (reader != null) {
				reader.close();
			}
		}
		return students;
	}

	private Student parseStudent(String row, int lineNumber) {
		String[] columns = row.split("\\t");
		if (columns.length < 6) {
			columns = row.split(",");
		}
		if (columns.length < 6) {
			throw new IllegalArgumentException("Line " + lineNumber + " format error. Expected: id,name,sex,age,grade,score");
		}

		Student student = new Student();
		student.setId(Integer.parseInt(columns[0].trim()));
		student.setName(columns[1].trim());
		student.setSex(columns[2].trim());
		student.setAge(Integer.parseInt(columns[3].trim()));
		student.setGrade(columns[4].trim());
		student.setScore(Float.parseFloat(columns[5].trim()));
		return student;
	}
}