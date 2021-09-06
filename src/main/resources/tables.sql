CREATE TABLE IF NOT EXISTS time_periods
(
    id SERIAL PRIMARY KEY,
    start_date DATE,
    end_date DATE
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
CREATE TABLE IF NOT EXISTS teachers
(
    id SERIAL PRIMARY KEY,
    firstname VARCHAR,
    lastname VARCHAR,
    date_of_birth DATE,
    gender VARCHAR,
    vacation INT,
    working_hours INT,
    FOREIGN KEY (vacation) REFERENCES time_periods (id) ON DELETE CASCADE,
    FOREIGN KEY (working_hours) REFERENCES time_periods (id) ON DELETE CASCADE
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
CREATE TABLE IF NOT EXISTS vacation
(
    id SERIAL PRIMARY KEY,
    description VARCHAR,
    time_period INTEGER,
    FOREIGN KEY (time_period) REFERENCES time_periods (id) ON DELETE CASCADE
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
    supervisor INTEGER,
    FOREIGN KEY (supervisor) REFERENCES teachers (id) ON DELETE CASCADE
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
    subject INT,
    teacher INT,
    time_period INT,
    classroom INT,
    FOREIGN KEY (subject) REFERENCES subjects (id) ON DELETE CASCADE,
    FOREIGN KEY (teacher) REFERENCES teachers (id) ON DELETE CASCADE,
    FOREIGN KEY (time_period) REFERENCES time_periods (id) ON DELETE CASCADE,
    FOREIGN KEY (classroom) REFERENCES classrooms (id) ON DELETE CASCADE
);
CREATE TABLE IF NOT EXISTS lectures_groups
(
    lecture_id INTEGER,
    group_id INTEGER,
    FOREIGN KEY (group_id) REFERENCES groups (id) ON DELETE CASCADE,
    FOREIGN KEY (lecture_id) REFERENCES lectures (id) ON DELETE CASCADE
);
