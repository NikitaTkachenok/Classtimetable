package ua.com.foxminded.classtimetable.entities;

public class TeacherFaculty {

	private int teacherId;
	private int facultyId;

	public TeacherFaculty() {
	}

	public int getTeacherId() {
		return teacherId;
	}

	public void setTeacherId(int teacherId) {
		this.teacherId = teacherId;
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
		result = prime * result + teacherId;
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
		TeacherFaculty other = (TeacherFaculty) obj;
		if (facultyId != other.facultyId)
			return false;
		if (teacherId != other.teacherId)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "TeacherFaculty [teacherId=" + teacherId + ", facultyId=" + facultyId + "]";
	}
}
