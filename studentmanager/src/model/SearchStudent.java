package model;

import java.util.List;

import entity.Student;

public class SearchStudent {

	private final StudentModel studentModel = new StudentModel();

	public List<Student> search() {
		return studentModel.search();
	}
}
