package ua.com.foxminded.classtimetable.entities;

public class Faculty {

	private int id;
	private String facultyName;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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
		result = prime * result + id;
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
		if (id != other.id)
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
		return "Faculty [id=" + id + ", facultyName=" + facultyName + "]";
	}

}
