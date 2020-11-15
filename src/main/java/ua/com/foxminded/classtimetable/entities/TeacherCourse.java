package ua.com.foxminded.classtimetable.entities;

public class TeacherCourse {

	private int teacherId;
	private int courseId;

	public TeacherCourse() {
	}

	public int getTeacherId() {
		return teacherId;
	}

	public void setTeacherId(int teacherId) {
		this.teacherId = teacherId;
	}

	public int getCourseId() {
		return courseId;
	}

	public void setCourseId(int courseId) {
		this.courseId = courseId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + courseId;
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
		TeacherCourse other = (TeacherCourse) obj;
		if (courseId != other.courseId)
			return false;
		if (teacherId != other.teacherId)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "TeacherCourse [teacherId=" + teacherId + ", courseId=" + courseId + "]";
	}

}
