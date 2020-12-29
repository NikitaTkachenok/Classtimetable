package ua.com.foxminded.classtimetable.entities;

import java.time.LocalTime;

public class Lesson {

	private int id;
	private LocalTime startTime;
	private LocalTime endTime;
	private int classroomId;
	private int courseId;
	private int teacherId;

	public int getId() {
		return id;
	}

	public void setid(int id) {
		this.id = id;
	}

	public LocalTime getStartTime() {
		return startTime;
	}

	public void setStartTime(LocalTime startTime) {
		this.startTime = startTime;
	}

	public LocalTime getEndTime() {
		return endTime;
	}

	public void setEndTime(LocalTime endTime) {
		this.endTime = endTime;
	}

	public int getClassroomId() {
		return classroomId;
	}

	public void setClassroomId(int classroomId) {
		this.classroomId = classroomId;
	}

	public int getCourseId() {
		return courseId;
	}

	public void setCourseId(int courseId) {
		this.courseId = courseId;
	}

	public int getTeacherId() {
		return teacherId;
	}

	public void setTeacherId(int teacherId) {
		this.teacherId = teacherId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + classroomId;
		result = prime * result + courseId;
		result = prime * result + ((endTime == null) ? 0 : endTime.hashCode());
		result = prime * result + id;
		result = prime * result + ((startTime == null) ? 0 : startTime.hashCode());
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
		Lesson other = (Lesson) obj;
		if (classroomId != other.classroomId)
			return false;
		if (courseId != other.courseId)
			return false;
		if (endTime == null) {
			if (other.endTime != null)
				return false;
		} else if (!endTime.equals(other.endTime))
			return false;
		if (id != other.id)
			return false;
		if (startTime == null) {
			if (other.startTime != null)
				return false;
		} else if (!startTime.equals(other.startTime))
			return false;
		if (teacherId != other.teacherId)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Lesson [id=" + id + ", startTime=" + startTime + ", endTime=" + endTime + ", classroomId=" + classroomId
				+ ", courseId=" + courseId + ", teacherId=" + teacherId + "]";
	}

}
