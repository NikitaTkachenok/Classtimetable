package ua.com.foxminded.classtimetable.entities;

public class Lesson {

	private int lessonSerialNumber;
	private String startTime;
	private String endTime;

	public Lesson() {
	}

	public int getLessonSerialNumber() {
		return lessonSerialNumber;
	}

	public void setLessonSerialNumber(int lessonSerialNumber) {
		this.lessonSerialNumber = lessonSerialNumber;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((endTime == null) ? 0 : endTime.hashCode());
		result = prime * result + lessonSerialNumber;
		result = prime * result + ((startTime == null) ? 0 : startTime.hashCode());
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
		if (endTime == null) {
			if (other.endTime != null)
				return false;
		} else if (!endTime.equals(other.endTime))
			return false;
		if (lessonSerialNumber != other.lessonSerialNumber)
			return false;
		if (startTime == null) {
			if (other.startTime != null)
				return false;
		} else if (!startTime.equals(other.startTime))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Lesson [lessonSerialNumber=" + lessonSerialNumber + ", startTime=" + startTime + ", endTime=" + endTime
				+ "]";
	}

}
