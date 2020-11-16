package ua.com.foxminded.classtimetable.entities;

public class StudentFaculty {

	private int studentId;
	private int facultyId;

	public StudentFaculty() {
	}

	public int getStudentId() {
		return studentId;
	}

	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}

	public int getFacultyId() {
		return facultyId;
	}

	public void setFacultyId(int facultyId) {
		this.facultyId = facultyId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + facultyId;
		result = prime * result + studentId;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		StudentFaculty other = (StudentFaculty) obj;
		if (facultyId != other.facultyId)
			return false;
		if (studentId != other.studentId)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "StudentFaculty [studentId=" + studentId + ", facultyId=" + facultyId + "]";
	}

}
