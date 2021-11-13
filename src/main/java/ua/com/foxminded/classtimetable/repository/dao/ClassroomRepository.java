package ua.com.foxminded.classtimetable.repository.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.com.foxminded.classtimetable.repository.entities.Classroom;

import javax.persistence.criteria.CriteriaBuilder;

@Repository
public interface ClassroomRepository extends JpaRepository<Classroom, Integer> {

}
