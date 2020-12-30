DROP TABLE IF EXISTS buildings, classrooms, courses, faculties, teachers, students, lessons, students_courses, teachers_courses;

CREATE TABLE buildings
(
	id SERIAL NOT NULL,
	building_name text NOT NULL,
	primary key (id)
);

CREATE TABLE classrooms
(
	id SERIAL NOT NULL,
	room_name text NOT NULL,
	room_type text NOT NULL,
	room_capacity int NOT NULL,
	building_id int,
	PRIMARY KEY (id),
	FOREIGN KEY (building_id) REFERENCES buildings (id) ON DELETE SET NULL
);

CREATE TABLE courses 
(
	id SERIAL NOT NULL,
	course_name text NOT NULL,
	PRIMARY KEY (id)
);

CREATE TABLE faculties
(
	id SERIAL NOT NULL,
	faculty_name text NOT NULL,
	PRIMARY KEY (id)
);

CREATE TABLE teachers
(
	id SERIAL NOT NULL,
	first_name text NOT NULL,
	last_name text NOT NULL,
	faculty_id int,
	PRIMARY KEY (id),
	FOREIGN KEY (faculty_id) REFERENCES faculties (id) ON DELETE SET NULL
);

CREATE TABLE students
(
	id SERIAL NOT NULL,
	first_name text NOT NULL,
	last_name text NOT NULL,
	faculty_id int,
	PRIMARY KEY (id),
	FOREIGN KEY (faculty_id) REFERENCES faculties (id) ON DELETE SET NULL
);

CREATE TABLE lessons
(
	id SERIAL NOT NULL,
	start_time TIME NOT NULL,
	end_time TIME NOT NULL,
	classroom_id int,
	course_id int,
	teacher_id int,
	PRIMARY KEY (id),
	FOREIGN KEY (classroom_id) REFERENCES classrooms (id) ON DELETE SET NULL,
	FOREIGN KEY (course_id) REFERENCES courses (id) ON DELETE SET NULL,
	FOREIGN KEY (teacher_id) REFERENCES teachers (id) ON DELETE SET NULL
);

CREATE TABLE students_courses 
(
	student_id int,
	course_id int,
	PRIMARY KEY (student_id, course_id),
	FOREIGN KEY (student_id) REFERENCES students (id) ON DELETE CASCADE,
	FOREIGN KEY (course_id) REFERENCES courses (id) ON DELETE CASCADE
);

CREATE TABLE teachers_courses 
(
	teacher_id int,
	course_id int,
	PRIMARY KEY (teacher_id, course_id),
	FOREIGN KEY (teacher_id) REFERENCES teachers (id) ON DELETE CASCADE,
	FOREIGN KEY (course_id) REFERENCES courses (id) ON DELETE CASCADE
);
