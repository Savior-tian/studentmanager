package model;

import entity.Student;

public class SelectStudent {

	private final StudentModel studentModel = new StudentModel();

	public Student load(Integer id) {
		return studentModel.load(id);
	}
}
