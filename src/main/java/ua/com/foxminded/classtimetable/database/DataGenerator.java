package ua.com.foxminded.classtimetable.database;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import ua.com.foxminded.classtimetable.config.DBConfiguration;
import ua.com.foxminded.classtimetable.dao.BuildingDao;
import ua.com.foxminded.classtimetable.dao.ClassroomDao;
import ua.com.foxminded.classtimetable.dao.CourseDao;
import ua.com.foxminded.classtimetable.dao.FacultyDao;
import ua.com.foxminded.classtimetable.dao.StudentCourseDao;
import ua.com.foxminded.classtimetable.dao.StudentDao;
import ua.com.foxminded.classtimetable.dao.TeacherCourseDao;
import ua.com.foxminded.classtimetable.dao.TeacherDao;
import ua.com.foxminded.classtimetable.entities.Building;
import ua.com.foxminded.classtimetable.entities.Classroom;
import ua.com.foxminded.classtimetable.entities.Course;
import ua.com.foxminded.classtimetable.entities.Faculty;
import ua.com.foxminded.classtimetable.entities.Student;
import ua.com.foxminded.classtimetable.entities.StudentCourse;
import ua.com.foxminded.classtimetable.entities.Teacher;
import ua.com.foxminded.classtimetable.entities.TeacherCourse;

public class DataGenerator {

	private final AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(
			DBConfiguration.class);

	private static final String HYPHEN = "-";
	private static final String SPACE = " ";
	private static final String UNDERSCORE = "_";

	public void fillTables() throws IOException, URISyntaxException, SQLException {
		formCorpusesTable();
		formClassroomsTable();
		formCoursesTable();
		formFacultiesTable();
		formStudentsTable();
		formTeachersTable();
		addCoursesToEachStudent();
		addCoursesToEachTeacher();
		checkCoursesWithoutTeachers();
	}

	private void formCorpusesTable() {
		Set<String> corpuses = generateCorpusesNames();
		BuildingDao daoBuilding = context.getBean(BuildingDao.class);
		for (String corpus : corpuses) {
			Building building = new Building();
			building.setBuildingName(corpus);
			daoBuilding.create(building);

		}
	}

	private Set<String> generateCorpusesNames() {
		Set<String> generatedCorpusNames = new HashSet<>();
		while (generatedCorpusNames.size() < 5) {
			generatedCorpusNames.add(RandomStringUtils.randomAlphabetic(1).toUpperCase());
		}
		return generatedCorpusNames;
	}

	private void formClassroomsTable() throws IOException, URISyntaxException {
		List<Classroom> classrooms = fillClassroomsData();
		ClassroomDao daoClassroom = context.getBean(ClassroomDao.class);
		for (Classroom classroom : classrooms) {
			daoClassroom.create(classroom);
		}
	}

	private List<Classroom> fillClassroomsData() throws IOException, URISyntaxException {
		List<Classroom> roomsWithFullData = distributeClassroomsToCorpuses();
		String classroomTypes = Files.lines(Paths.get(ClassLoader.getSystemResource("auditoriesTypes.txt").toURI()))
				.collect(Collectors.joining(SPACE));
		for (int index = 0; index < roomsWithFullData.size();) {
			roomsWithFullData.get(index).setRoomType(classroomTypes.substring(0, classroomTypes.indexOf(SPACE)));
			roomsWithFullData.get(index).setRoomCapacity(30);
			index++;
			if (index < roomsWithFullData.size()) {
				roomsWithFullData.get(index).setRoomType(classroomTypes.substring(0, classroomTypes.indexOf(SPACE)));
				roomsWithFullData.get(index).setRoomCapacity(30);
				index++;
			}
			if (index < roomsWithFullData.size()) {
				roomsWithFullData.get(index).setRoomType(classroomTypes.substring(classroomTypes.indexOf(SPACE) + 1,
						classroomTypes.indexOf(SPACE, classroomTypes.indexOf(SPACE) + 1)));
				roomsWithFullData.get(index).setRoomCapacity(10);
				index++;
			}
			if (index < roomsWithFullData.size()) {
				roomsWithFullData.get(index).setRoomType(classroomTypes.substring(
						classroomTypes.indexOf(SPACE, classroomTypes.indexOf(SPACE) + 1) + 1, classroomTypes.length()));
				roomsWithFullData.get(index).setRoomCapacity(100);
				index++;
			}
		}
		return roomsWithFullData;
	}

	private List<Classroom> distributeClassroomsToCorpuses() {
		List<Classroom> roomsWithNameAndCorpusId = new ArrayList<>();
		BuildingDao daoBuilding = context.getBean(BuildingDao.class);
		List<Building> corpuses = daoBuilding.getAll();
		for (Building building : corpuses) {
			for (int index = 0; index < 10; index++) {
				String roomName = building.getBuildingName() + HYPHEN + Integer.toString(index);
				Classroom classroom = new Classroom();
				classroom.setRoomName(roomName);
				classroom.setBuildingId(building.getId());
				roomsWithNameAndCorpusId.add(classroom);
			}
		}
		return roomsWithNameAndCorpusId;
	}

	private void formCoursesTable() throws IOException, URISyntaxException {
		List<String> coursesNames = Files.lines(Paths.get(ClassLoader.getSystemResource("disciplines.txt").toURI()))
				.collect(Collectors.toList());
		CourseDao daoCourse = context.getBean(CourseDao.class);
		for (String courseName : coursesNames) {
			Course course = new Course();
			course.setCourseName(courseName);
			daoCourse.create(course);
		}
	}

	private void formFacultiesTable() throws IOException, URISyntaxException {
		List<String> facultiesNames = Files
				.lines(Paths.get(ClassLoader.getSystemResource("facultiesNames.txt").toURI()))
				.collect(Collectors.toList());
		FacultyDao daoFaculty = context.getBean(FacultyDao.class);
		for (String facultyName : facultiesNames) {
			Faculty faculty = new Faculty();
			faculty.setFacultyName(facultyName);
			daoFaculty.create(faculty);
		}
	}

	private void formStudentsTable() throws IOException, URISyntaxException {
		StudentDao daoStudent = context.getBean(StudentDao.class);
		for (Student student : generateStudents()) {
			daoStudent.create(student);
		}
	}

	private void formTeachersTable() throws IOException, URISyntaxException {
		TeacherDao daoTeacher = context.getBean(TeacherDao.class);
		for (Teacher teacher : generateTeachers()) {
			daoTeacher.create(teacher);
		}
	}

	private Set<Student> generateStudents() throws IOException, URISyntaxException {
		List<String> firstNames = getFirstNames();
		List<String> lastNames = getLastNames();
		Set<Student> generatedStudents = new HashSet<>();
		while (generatedStudents.size() < 500) {
			int randomIndexOne = ThreadLocalRandom.current().nextInt(firstNames.size());
			int randomIndexTwo = ThreadLocalRandom.current().nextInt(lastNames.size());
			Student student = new Student();
			student.setFirstName(firstNames.get(randomIndexOne));
			student.setLastName(lastNames.get(randomIndexTwo));
			generatedStudents.add(student);
		}
		return distributeStudentsToFaculties(generatedStudents);
	}

	private Set<Teacher> generateTeachers() throws IOException, URISyntaxException {
		List<String> firstNames = getFirstNames();
		List<String> lastNames = getLastNames();
		Set<Teacher> generatedTeachers = new HashSet<>();
		while (generatedTeachers.size() < 50) {
			int randomIndexOne = ThreadLocalRandom.current().nextInt(firstNames.size());
			int randomIndexTwo = ThreadLocalRandom.current().nextInt(lastNames.size());
			Teacher teacher = new Teacher();
			teacher.setFirstName(firstNames.get(randomIndexOne));
			teacher.setLastName(lastNames.get(randomIndexTwo));
			generatedTeachers.add(teacher);
		}
		return distributeTeachersToFaculties(generatedTeachers);
	}

	private List<String> getFirstNames() throws IOException, URISyntaxException {
		List<String> names = Files.lines(Paths.get(ClassLoader.getSystemResource("names.txt").toURI()))
				.collect(Collectors.toList());
		List<String> firstNames = new ArrayList<>();
		for (String name : names) {
			firstNames.add(name.substring(0, name.indexOf(UNDERSCORE)));
		}
		return firstNames;
	}

	private List<String> getLastNames() throws IOException, URISyntaxException {
		List<String> names = Files.lines(Paths.get(ClassLoader.getSystemResource("names.txt").toURI()))
				.collect(Collectors.toList());
		List<String> lastNames = new ArrayList<>();
		for (String name : names) {
			lastNames.add(name.substring(name.indexOf(UNDERSCORE) + 1, name.length()));
		}
		return lastNames;
	}

	private Set<Student> distributeStudentsToFaculties(Set<Student> students) {
		FacultyDao daoFaculty = context.getBean(FacultyDao.class);
		for (Student student : students) {
			int facultyId = (int) (1 + Math.random() * (daoFaculty.getAll().size()));
			student.setFacultyId(facultyId);
		}
		return students;
	}

	private Set<Teacher> distributeTeachersToFaculties(Set<Teacher> teachers) {
		FacultyDao daoFaculty = context.getBean(FacultyDao.class);
		for (Teacher teacher : teachers) {
			int facultyId = (int) (1 + Math.random() * (daoFaculty.getAll().size()));
			teacher.setFacultyId(facultyId);
		}
		return teachers;
	}

	private void addCoursesToEachStudent() throws IOException, URISyntaxException, SQLException {
		StudentDao daoStudent = context.getBean(StudentDao.class);
		CourseDao daoCourse = context.getBean(CourseDao.class);
		StudentCourseDao daoStudentCourse = context.getBean(StudentCourseDao.class);
		for (Student student : daoStudent.getAll()) {
			int numberOfCoursesOfStudent = (int) (1 + Math.random() * (6 - 1));
			Set<Integer> randomCourses = new HashSet<>();
			while (randomCourses.size() < numberOfCoursesOfStudent) {
				randomCourses.add(daoCourse.getRandomId(1).getId());
			}
			List<Integer> randomCoursesList = new ArrayList<>(randomCourses);
			for (int counter = 0; counter < randomCourses.size(); counter++) {
				StudentCourse studentCourse = new StudentCourse();
				studentCourse.setStudentId(student.getId());
				studentCourse.setCourseId(randomCoursesList.get(counter));
				daoStudentCourse.create(studentCourse);
			}
		}
	}

	private void addCoursesToEachTeacher() throws IOException, URISyntaxException, SQLException {
		TeacherDao daoTeacher = context.getBean(TeacherDao.class);
		CourseDao daoCourse = context.getBean(CourseDao.class);
		TeacherCourseDao daoTeacherCourse = context.getBean(TeacherCourseDao.class);
		for (Teacher teacher : daoTeacher.getAll()) {
			TeacherCourse teacherCourse = new TeacherCourse();
			teacherCourse.setTeacherId(teacher.getId());
			teacherCourse.setCourseId(daoCourse.getRandomId(1).getId());
			daoTeacherCourse.create(teacherCourse);
		}
	}

	private void checkCoursesWithoutTeachers() throws IOException, URISyntaxException, SQLException {
		CourseDao daoCourse = context.getBean(CourseDao.class);
		TeacherCourseDao daoTeacherCourse = context.getBean(TeacherCourseDao.class);
		List<Integer> coursesWithTeacher = new ArrayList<>();
		for (TeacherCourse teacherCourse : daoTeacherCourse.getAll()) {
			coursesWithTeacher.add(teacherCourse.getCourseId());
		}
		for (Course course : daoCourse.getAll()) {
			if (!coursesWithTeacher.contains(course.getId())) {
				for (TeacherCourse teacherCourse : daoTeacherCourse.getAll()) {
					daoTeacherCourse.delete(teacherCourse);
				}
				addCoursesToEachTeacher();
			}
		}
	}
}
