package control;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import entity.Student;
import model.StudentModel;

public class ImportStudentServlet extends HttpServlet {

	private static final DataFormatter DATA_FORMATTER = new DataFormatter();

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getRequestDispatcher("/jsp/studentimport.jsp").forward(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");

		List<Student> students = new ArrayList<Student>();
		try {
			ImportFormData formData = parseFormData(request);
			if (formData.pastedContent != null && formData.pastedContent.trim().length() > 0) {
				students = parseRows(formData.pastedContent);
			} else if (formData.uploadedFile != null && formData.uploadedFile.getSize() > 0) {
				students = parseUploadedFile(formData.uploadedFile);
			} else if (formData.filePath != null && formData.filePath.trim().length() > 0) {
				students = parseFileByPath(formData.filePath.trim());
			} else {
				request.setAttribute("error", "请粘贴 Excel 内容，或上传 csv/xls/xlsx 文件，或填写本地文件路径。");
				request.getRequestDispatcher("/jsp/studentimport.jsp").forward(request, response);
				return;
			}

			if (students.isEmpty()) {
				request.setAttribute("error", "没有解析到可导入的学生数据，请检查表头和内容格式。");
				request.getRequestDispatcher("/jsp/studentimport.jsp").forward(request, response);
				return;
			}

			StudentModel model = new StudentModel();
			int importedCount = model.batchInsert(students);
			request.setAttribute("message", "成功导入 " + importedCount + " 条学生记录。");
			request.setAttribute("studentlist", model.search());
			request.getRequestDispatcher("/jsp/studentlist.jsp").forward(request, response);
		} catch (Exception e) {
			request.setAttribute("error", e.getMessage());
			request.getRequestDispatcher("/jsp/studentimport.jsp").forward(request, response);
		}
	}

	private ImportFormData parseFormData(HttpServletRequest request) throws FileUploadException, Exception {
		ImportFormData formData = new ImportFormData();
		if (!ServletFileUpload.isMultipartContent(request)) {
			formData.pastedContent = request.getParameter("pastedContent");
			formData.filePath = request.getParameter("filePath");
			return formData;
		}

		ServletFileUpload upload = new ServletFileUpload(new DiskFileItemFactory());
		upload.setHeaderEncoding("UTF-8");
		List items = upload.parseRequest(request);
		for (int index = 0; index < items.size(); index++) {
			FileItem item = (FileItem) items.get(index);
			if (item.isFormField()) {
				String fieldName = item.getFieldName();
				if ("pastedContent".equals(fieldName)) {
					formData.pastedContent = item.getString("UTF-8");
				} else if ("filePath".equals(fieldName)) {
					formData.filePath = item.getString("UTF-8");
				}
			} else if ("uploadFile".equals(item.getFieldName()) && item.getSize() > 0) {
				formData.uploadedFile = item;
			}
		}
		return formData;
	}

	private List<Student> parseRows(String content) {
		List<Student> students = new ArrayList<Student>();
		String[] rows = content.split("\\r?\\n");
		for (int index = 0; index < rows.length; index++) {
			String row = rows[index].trim();
			if (row.length() == 0) {
				continue;
			}
			if (isHeaderRow(splitColumns(row))) {
				continue;
			}
			students.add(parseStudent(splitColumns(row), index + 1));
		}
		return students;
	}

	private List<Student> parseUploadedFile(FileItem uploadedFile) throws Exception {
		String fileName = uploadedFile.getName();
		String lowerFileName = fileName == null ? "" : fileName.toLowerCase();
		InputStream inputStream = uploadedFile.getInputStream();
		try {
			if (lowerFileName.endsWith(".xls") || lowerFileName.endsWith(".xlsx")) {
				return parseExcel(inputStream);
			}
			if (lowerFileName.endsWith(".csv") || lowerFileName.endsWith(".txt")) {
				return parseDelimitedStream(inputStream);
			}
			throw new IllegalArgumentException("上传文件类型不支持，请选择 csv、xls 或 xlsx 文件。");
		} finally {
			inputStream.close();
		}
	}

	private List<Student> parseFileByPath(String filePath) throws Exception {
		String lowerFilePath = filePath.toLowerCase();
		if (lowerFilePath.endsWith(".xls") || lowerFilePath.endsWith(".xlsx")) {
			InputStream inputStream = new FileInputStream(filePath);
			try {
				return parseExcel(inputStream);
			} finally {
				inputStream.close();
			}
		}
		return parseCsvFile(filePath);
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
		InputStream inputStream = new FileInputStream(filePath);
		try {
			return parseDelimitedStream(inputStream, charset);
		} finally {
			inputStream.close();
		}
	}

	private List<Student> parseDelimitedStream(InputStream inputStream) throws Exception {
		byte[] bytes = inputStream.readAllBytes();
		Exception utf8Failure = null;
		try {
			return parseDelimitedStream(new ByteArrayInputStream(bytes), StandardCharsets.UTF_8);
		} catch (Exception e) {
			utf8Failure = e;
		}
		try {
			return parseDelimitedStream(new ByteArrayInputStream(bytes), Charset.forName("GBK"));
		} catch (Exception e) {
			if (utf8Failure != null) {
				e.addSuppressed(utf8Failure);
			}
			throw e;
		}
	}

	private List<Student> parseDelimitedStream(InputStream inputStream, Charset charset) throws Exception {
		List<Student> students = new ArrayList<Student>();
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new InputStreamReader(inputStream, charset));
			String line = null;
			int lineNumber = 0;
			while ((line = reader.readLine()) != null) {
				lineNumber++;
				line = line.trim();
				if (line.length() == 0) {
					continue;
				}
				String[] columns = splitColumns(line);
				if (isHeaderRow(columns)) {
					continue;
				}
				students.add(parseStudent(columns, lineNumber));
			}
		} finally {
			if (reader != null) {
				reader.close();
			}
		}
		return students;
	}

	private List<Student> parseExcel(InputStream inputStream) throws Exception {
		List<Student> students = new ArrayList<Student>();
		Workbook workbook = WorkbookFactory.create(inputStream);
		try {
			Sheet sheet = workbook.getSheetAt(0);
			for (int rowIndex = 0; rowIndex <= sheet.getLastRowNum(); rowIndex++) {
				Row row = sheet.getRow(rowIndex);
				if (row == null) {
					continue;
				}
				String[] columns = new String[6];
				boolean emptyRow = true;
				for (int columnIndex = 0; columnIndex < 6; columnIndex++) {
					Cell cell = row.getCell(columnIndex);
					columns[columnIndex] = cell == null ? "" : DATA_FORMATTER.formatCellValue(cell).trim();
					if (columns[columnIndex].length() > 0) {
						emptyRow = false;
					}
				}
				if (emptyRow || isHeaderRow(columns)) {
					continue;
				}
				students.add(parseStudent(columns, rowIndex + 1));
			}
		} finally {
			workbook.close();
		}
		return students;
	}

	private String[] splitColumns(String row) {
		String[] columns = row.split("\\t");
		if (columns.length < 6) {
			columns = row.split(",");
		}
		return columns;
	}

	private boolean isHeaderRow(String[] columns) {
		if (columns == null || columns.length == 0) {
			return false;
		}
		String firstColumn = columns[0] == null ? "" : columns[0].trim();
		return "学号".equals(firstColumn) || "id".equalsIgnoreCase(firstColumn);
	}

	private Student parseStudent(String[] columns, int lineNumber) {
		if (columns.length < 6) {
			throw new IllegalArgumentException("第 " + lineNumber + " 行格式不正确，应为：学号,姓名,性别,年龄,班级,成绩");
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

	private static class ImportFormData {
		private String pastedContent;
		private String filePath;
		private FileItem uploadedFile;
	}
}