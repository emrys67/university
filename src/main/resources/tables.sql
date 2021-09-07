-- DROP TABLE time_periods, subjects, students, teachers, groups, vacations, lectures,
--     students_groups, subjects_teachers, lectures_groups, classrooms;

CREATE TABLE IF NOT EXISTS time_periods
(
    id SERIAL PRIMARY KEY,
    start_date DATE,
    end_date DATE,
    start_time TIME,
    end_time TIME
);
CREATE TABLE IF NOT EXISTS vacations
(
    id SERIAL PRIMARY KEY,
    description VARCHAR,
    time_period_id INTEGER,
    FOREIGN KEY (time_period_id) REFERENCES time_periods (id) ON DELETE CASCADE
);
CREATE TABLE IF NOT EXISTS teachers
(
    id SERIAL PRIMARY KEY,
    firstname VARCHAR,
    lastname VARCHAR,
    date_of_birth DATE,
    gender VARCHAR,
    vacation_id INT,
    working_hours_id INT,
    FOREIGN KEY (vacation_id) REFERENCES vacations (id) ON DELETE CASCADE,
    FOREIGN KEY (working_hours_id) REFERENCES time_periods (id) ON DELETE CASCADE
);
CREATE TABLE IF NOT EXISTS students
(
    id SERIAL PRIMARY KEY,
    firstname VARCHAR,
    lastname VARCHAR,
    date_of_birth DATE,
    gender VARCHAR,
    study_year INT
);
CREATE TABLE IF NOT EXISTS groups
(
    id SERIAL PRIMARY KEY,
    name VARCHAR
);
CREATE TABLE IF NOT EXISTS students_groups
(
    student_id INT,
    group_id INT,
    FOREIGN KEY (student_id) REFERENCES students (id) ON DELETE CASCADE,
    FOREIGN KEY (group_id) REFERENCES groups (id) ON DELETE CASCADE
);
CREATE TABLE IF NOT EXISTS classrooms
(
    id SERIAL PRIMARY KEY,
    capacity INT
);
CREATE TABLE IF NOT EXISTS subjects
(
    id SERIAL PRIMARY KEY,
    name VARCHAR,
    description VARCHAR,
    supervisor_id INTEGER,
    FOREIGN KEY (supervisor_id) REFERENCES teachers (id) ON DELETE CASCADE
);
CREATE TABLE IF NOT EXISTS subjects_teachers
(
    teacher_id INTEGER,
    subject_id INTEGER,
    FOREIGN KEY (teacher_id) REFERENCES teachers (id) ON DELETE CASCADE,
    FOREIGN KEY (subject_id) REFERENCES subjects (id) ON DELETE CASCADE
);
CREATE TABLE IF NOT EXISTS lectures
(
    id SERIAL PRIMARY KEY,
    subject_id INT,
    teacher_id INT,
    time_period_id INT,
    classroom_id INT,
    FOREIGN KEY (subject_id) REFERENCES subjects (id) ON DELETE CASCADE,
    FOREIGN KEY (teacher_id) REFERENCES teachers (id) ON DELETE CASCADE,
    FOREIGN KEY (time_period_id) REFERENCES time_periods (id) ON DELETE CASCADE,
    FOREIGN KEY (classroom_id) REFERENCES classrooms (id) ON DELETE CASCADE
);
CREATE TABLE IF NOT EXISTS lectures_groups
(
    lecture_id INTEGER,
    group_id INTEGER,
    FOREIGN KEY (group_id) REFERENCES groups (id) ON DELETE CASCADE,
    FOREIGN KEY (lecture_id) REFERENCES lectures (id) ON DELETE CASCADE
);
