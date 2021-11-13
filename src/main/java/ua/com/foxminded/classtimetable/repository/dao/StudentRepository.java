package ua.com.foxminded.classtimetable.repository.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.com.foxminded.classtimetable.repository.entities.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {

}
