package ua.com.foxminded.classtimetable.repository.dao;

import org.springframework.stereotype.Repository;
import ua.com.foxminded.classtimetable.repository.entities.Lesson;

import java.time.LocalDate;
import java.util.List;

@Repository
public class LessonDao extends AbstractDao<Lesson> implements DaoInterface<Lesson> {

    private static final String GET_TEACHER_LESSONS_IN_DATE_RANGE =
            "SELECT lessons.id, date, start_time, end_time, classroom_id, course_id, teacher_id " +
                    "FROM teachers " +
                    "INNER JOIN lessons ON teachers.id = lessons.teacher_id " +
                    "WHERE teachers.first_name LIKE :firstName AND teachers.last_name LIKE :lastName " +
                    "AND date BETWEEN :beginDate AND :endDate";

    private static final String GET_STUDENT_LESSONS_IN_DATE_RANGE =
            "SELECT lessons.id, date, start_time, end_time, classroom_id, lessons.course_id, teacher_id " +
                    "FROM lessons " +
                    "INNER JOIN students_courses ON lessons.course_id = students_courses.course_id " +
                    "INNER JOIN students ON students_courses.student_id = students.id " +
                    "WHERE students.first_name LIKE :firstName AND students.last_name LIKE :lastName " +
                    "AND date BETWEEN :beginDate AND :endDate";

    public LessonDao() {
        setClazz(Lesson.class);
    }

    @SuppressWarnings("unchecked")
    public List<Lesson> getLessonsForTeacherOnDateRange(String teacherFirstName, String teacherLastName,
                                                        LocalDate beginDate, LocalDate endDate) {
        List<Lesson> lessons = entityManager
                .createNativeQuery(GET_TEACHER_LESSONS_IN_DATE_RANGE)
                .setParameter("firstName", teacherFirstName)
                .setParameter("lastName", teacherLastName)
                .setParameter("beginDate", beginDate)
                .setParameter("endDate", endDate)
                .getResultList();
        return lessons;
    }

    @SuppressWarnings("unchecked")
    public List<Lesson> getLessonsForStudentOnDateRange(String studentFirstName, String studentLastName,
                                                        LocalDate beginDate, LocalDate endDate) {
        List<Lesson> lessons = entityManager
                .createNativeQuery(GET_STUDENT_LESSONS_IN_DATE_RANGE)
                .setParameter("firstName", studentFirstName)
                .setParameter("lastName", studentLastName)
                .setParameter("beginDate", beginDate)
                .setParameter("endDate", endDate)
                .getResultList();
        return lessons;
    }

}
