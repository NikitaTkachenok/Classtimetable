package ua.com.foxminded.classtimetable.entities;

public class BuildingClassroom {

	private int buildingId;
	private int roomId;

	public BuildingClassroom() {
	}

	public int getBuildingId() {
		return buildingId;
	}

	public void setBuildingId(int buildingId) {
		this.buildingId = buildingId;
	}

	public int getRoomId() {
		return roomId;
	}

	public void setRoomId(int roomId) {
		this.roomId = roomId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + buildingId;
		result = prime * result + roomId;
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
		BuildingClassroom other = (BuildingClassroom) obj;
		if (buildingId != other.buildingId)
			return false;
		if (roomId != other.roomId)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "BuildingClassroom [buildingId=" + buildingId + ", roomId=" + roomId + "]";
	}
}
