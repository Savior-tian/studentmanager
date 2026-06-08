package model;

import entity.Student;

public class InsertStudent {

	private final StudentModel studentModel = new StudentModel();

	public int insert(int id, String name, String sex, int age, String grade, float score) {
		return studentModel.insert(id, name, sex, age, grade, score);
	}

	public int insert(Student student) {
		return studentModel.insert(student);
	}
}
