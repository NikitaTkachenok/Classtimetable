package ua.com.foxminded.classtimetable.entities;

class Faculty {

	private int facultyId;
	private String facultyName;

	public Faculty() {
	}

	public int getFacultyId() {
		return facultyId;
	}

	public void setFacultyId(int facultyId) {
		this.facultyId = facultyId;
	}

	public String getFacultyName() {
		return facultyName;
	}

	public void setFacultyName(String facultyName) {
		this.facultyName = facultyName;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + facultyId;
		result = prime * result + ((facultyName == null) ? 0 : facultyName.hashCode());
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
		Faculty other = (Faculty) obj;
		if (facultyId != other.facultyId)
			return false;
		if (facultyName == null) {
			if (other.facultyName != null)
				return false;
		} else if (!facultyName.equals(other.facultyName))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Faculty [facultyId=" + facultyId + ", facultyName=" + facultyName + "]";
	}

}
