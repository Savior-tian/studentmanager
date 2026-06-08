package model;

public class DeleteStudent {

	private final StudentModel studentModel = new StudentModel();

	public int delete(int id) {
		return studentModel.delete(id);
	}
}
