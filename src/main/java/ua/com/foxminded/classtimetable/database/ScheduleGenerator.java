package ua.com.foxminded.classtimetable.database;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ThreadLocalRandom;
import java.util.Set;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import ua.com.foxminded.classtimetable.config.DBConfiguration;
import ua.com.foxminded.classtimetable.dao.ClassroomDao;
import ua.com.foxminded.classtimetable.dao.CourseDao;
import ua.com.foxminded.classtimetable.dao.FacultyDao;
import ua.com.foxminded.classtimetable.dao.LessonDao;
import ua.com.foxminded.classtimetable.dao.TeacherCourseDao;
import ua.com.foxminded.classtimetable.dao.TeacherDao;
import ua.com.foxminded.classtimetable.entities.Classroom;
import ua.com.foxminded.classtimetable.entities.Course;
import ua.com.foxminded.classtimetable.entities.Faculty;
import ua.com.foxminded.classtimetable.entities.Lesson;
import ua.com.foxminded.classtimetable.entities.Teacher;

public class ScheduleGenerator {

	private final AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(
			DBConfiguration.class);

	public void formSchedule() {
		LessonDao daoLesson = context.getBean(LessonDao.class);
		for (LocalDate date : setDateDiapason()) {
			for (Lesson lesson : formLessons()) {
				lesson.setDate(date);
				daoLesson.create(lesson);
			}
		}
	}

	private List<LocalDate> setDateDiapason() {
		LocalDate endDate = LocalDate.of(2021, 05, 31);
		List<LocalDate> diapason = new LinkedList<>();
		for (LocalDate startDate = LocalDate.now(); !diapason.contains(endDate); startDate = startDate.plusDays(1)) {
			diapason.add(startDate);
		}
		return diapason;
	}

	private List<Lesson> formLessons() {
		FacultyDao daoFaculty = context.getBean(FacultyDao.class);
		ClassroomDao daoClassroom = context.getBean(ClassroomDao.class);
		List<Lesson> lessonsPerDay = new LinkedList<>();
		List<Classroom> classrooms = daoClassroom.getAll();
		for (Faculty faculty : daoFaculty.getAll()) {
			List<Teacher> teachers = leaveTeachersFromOneFaculty(faculty.getId());
			List<Course> courses = leaveCoursesForOneFaculty(teachers);
			for (Entry<LocalTime, LocalTime> entry : setLessonsTime().entrySet()) {
				Lesson lesson = new Lesson();
				lesson.setStartTime(entry.getKey());
				lesson.setEndTime(entry.getValue());
				int randomTeacher = 0;
				if (teachers.size() >= 0) {
					randomTeacher = ThreadLocalRandom.current().nextInt(teachers.size());
				} else {
					randomTeacher = ThreadLocalRandom.current().nextInt(teachers.size() + 1);
				}
				lesson.setTeacherId(teachers.get(randomTeacher).getId());
				teachers.remove(randomTeacher);

				int randomCourse = 0;
				if (courses.size() >= 0) {
					randomCourse = ThreadLocalRandom.current().nextInt(courses.size());
				} else {
					randomCourse = ThreadLocalRandom.current().nextInt(courses.size() + 1);
				}
				lesson.setCourseId(courses.get(randomCourse).getId());
				courses.remove(randomCourse);

				int randomClassroom = 0;
				if (classrooms.size() >= 0) {
					randomClassroom = ThreadLocalRandom.current().nextInt(classrooms.size());
				} else {
					randomClassroom = ThreadLocalRandom.current().nextInt(classrooms.size() + 1);
				}
				lesson.setClassroomId(classrooms.get(randomClassroom).getId());
				classrooms.remove(randomClassroom);

				lessonsPerDay.add(lesson);
			}
		}
		return lessonsPerDay;
	}

	private List<Teacher> leaveTeachersFromOneFaculty(int facultyId) {
		TeacherDao daoTeacher = context.getBean(TeacherDao.class);
		return daoTeacher.getTeachersFromFaculty(facultyId);
	}

	private List<Course> leaveCoursesForOneFaculty(List<Teacher> facultyTeachers) {
		TeacherCourseDao daoTeacherCourse = context.getBean(TeacherCourseDao.class);
		CourseDao daoCourse = context.getBean(CourseDao.class);
		Set<Course> uniqueCourseNames = new HashSet<>();
		for (Teacher teacher : facultyTeachers) {
			uniqueCourseNames.add(daoCourse.getById(daoTeacherCourse.getById(teacher.getId()).getCourseId()));
		}
		List<Course> facultyCourses = new LinkedList<>();
		facultyCourses.addAll(uniqueCourseNames);
		return facultyCourses;
	}

	private Map<LocalTime, LocalTime> setLessonsTime() {
		Map<LocalTime, LocalTime> lessonsDuration = new LinkedHashMap<>();
		LocalTime startTime = LocalTime.of(9, 20);
		for (int counter = 0; counter <= 4; counter++) {
			lessonsDuration.put(startTime, startTime.plusHours(1).plusMinutes(40));
			startTime = startTime.plusHours(1).plusMinutes(50);
		}
		return lessonsDuration;
	}
}
