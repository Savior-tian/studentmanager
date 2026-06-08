package model;

public class UpdateStudent {

	private final StudentModel studentModel = new StudentModel();

	public int update(int id, String name, String sex, int age, String grade, float score) {
		return studentModel.update(id, name, sex, age, grade, score);
	}
}
