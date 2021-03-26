package ua.com.foxminded.classtimetable.entities;

import javax.persistence.*;

@Entity
@Table(name = "buildings")
public class Building {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column(name = "building_name")
    private String buildingName;

    public Building() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
        result = prime * result + id;
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
        Building other = (Building) obj;
        if (id != other.id)
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
        return "Buiding [id=" + id + ", buildingName=" + buildingName + "]";
    }
}
