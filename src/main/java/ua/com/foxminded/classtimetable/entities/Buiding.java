package ua.com.foxminded.classtimetable.entities;

public class Buiding {

	private int buildingId;
	private String buildingName;

	public Buiding() {
	}

	public int getBuildingId() {
		return buildingId;
	}

	public void setBuildingId(int buildingId) {
		this.buildingId = buildingId;
	}

	public String getBuildingName() {
		return buildingName;
	}

	public void setBuildingName(String buildingName) {
		this.buildingName = buildingName;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + buildingId;
		result = prime * result + ((buildingName == null) ? 0 : buildingName.hashCode());
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
		Buiding other = (Buiding) obj;
		if (buildingId != other.buildingId)
			return false;
		if (buildingName == null) {
			if (other.buildingName != null)
				return false;
		} else if (!buildingName.equals(other.buildingName))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Buiding [buildingId=" + buildingId + ", buildingName=" + buildingName + "]";
	}
}
