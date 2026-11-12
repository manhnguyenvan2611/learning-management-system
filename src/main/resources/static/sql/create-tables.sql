CREATE DATABASE lms;
SHOW DATABASES;
USE lms;
/**
 * Course table
 */
CREATE TABLE Course (
	id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    description VARCHAR(255) NOT NULL,
    thumbnail VARCHAR(255) NOT NULL,
    level INT NOT NULL,
    max_score INT NOT NULL,
	min_score INT NOT NULL,
    status VARCHAR(10) NOT NULL,
    timestamp timestamp DEFAULT current_timestamp,
    rate VARCHAR(5) DEFAULT '4.6'
);

ALTER TABLE Course ADD column course_name varchar(50) NOT NULL AFTER id;

INSERT INTO Course (course_name, description, thumbnail, level, max_score, min_score, status) VALUES 
('HTML and CSS', 'Learn the fundamentals of web structure and styling, including semantic HTML, Flexbox, Grid, and responsive design.', 'course-1.jpg', 1, 10, 8, 'public'),
('JavaScript and Version Control System', 'Master JavaScript basics and advanced concepts along with version control using Git for efficient code management.', 'course-2.jpg', 2, 10, 8, 'public'),
('VCS Hosting and Package Managers', 'Learn how to use GitHub/GitLab for hosting repositories and manage dependencies using npm and Yarn.', 'course-3.jpg', 3, 10, 8, 'public'),
('ReactJS and Build Tools', 'Build modern web applications using React, including components, hooks, routing, and bundling with tools like Vite or Webpack.', 'course-4.jpg', 4, 10, 8, 'public'),
('Testing', 'Understand testing strategies including unit, integration, and end-to-end testing using modern tools.', 'course-5.jpg', 5, 10, 8, 'public')
;

ALTER TABLE Course ADD COLUMN threshold INT NOT NULL AFTER status;

UPDATE Course SET threshold = 8 WHERE id = 2;
UPDATE Course SET threshold = 16 WHERE id = 3;
UPDATE Course SET threshold = 24 WHERE id = 4;
UPDATE Course SET threshold = 32 WHERE id = 5;


/*
 * Lesson table
 */
CREATE TABLE Lesson (
	id INT auto_increment PRIMARY KEY,
    lesson_name VARCHAR(255) NOT NULL,
    file VARCHAR(255) NOT NULL,
    course_id INT NOT NULL,
    progress INT NOT NULL,
    FOREIGN KEY (course_id) 
    REFERENCES Course(id)
);

INSERT INTO Lesson (id, lesson_name, file, course_id, progress) VALUES
(1, 'Introduction to HTML', 'course-1-1.pdf', 1, 30),
(2, 'HTML Semantic Elements', 'course-1-2.pdf', 1, 30),
(3, 'CSS Flexbox and Grid', 'course-1-3.mp4', 1, 40),
(4, 'JavaScript Basics', 'course-2-1.pdf', 2, 30),
(5, 'DOM Manipulation', 'course-2-2.pdf', 2, 30),
(6, 'Introduction to Git', 'course-2-3.mp4', 2, 40),
(7, 'Using GitHub', 'course-3-1.pdf', 3, 30),
(8, 'Managing Packages with npm', 'course-3-2.pdf', 3, 30),
(9, 'Using Yarn', 'course-3-3.mp4', 3, 40),
(10, 'React Components', 'course-4-1.pdf', 4, 30),
(11, 'React Hooks', 'course-4-2.pdf', 4, 30),
(12, 'Build Tools with Vite', 'course-4-3.mp4', 4, 40),
(13, 'Introduction to Testing', 'course-5-1.pdf', 5, 30),
(14, 'Unit Testing with Jest', 'course-5-2.pdf', 5, 30),
(15, 'End-to-End Testing with Cypress', 'course-5-3.mp4', 5, 40);

SELECT * FROM Lesson;

/**
 * Test table
 */
CREATE TABLE Test (
	id INT auto_increment primary key,
    test_name VARCHAR(255) NOT NULL,
    time int NOT NULL,
    question_number INT NOT NULL,
    threshold INT NOT NULL,
    course_id INT NOT NULL
);

ALTER TABLE Test
ADD CONSTRAINT FOREIGN KEY (course_id) REFERENCES Course(id);

INSERT INTO Test (id, test_name, time, question_number, threshold, course_id) VALUES
(1, 'HTML and CSS', 15, 10, 8, 1),
(2, 'JavaScript and Version Control System', 20, 10, 8, 2),
(3, 'VCS Hosting and Package Managers', 18, 10, 8, 3),
(4, 'ReactJS and Build Tools', 25, 10, 8, 4),
(5, 'Testing', 22, 10, 8, 5);

/**
 * Question table
 */
CREATE TABLE Question (
	id INT AUTO_INCREMENT PRIMARY KEY,
    question_content VARCHAR(255) NOT NULL,
    answer_a VARCHAR(255) NOT NULL,
	answer_b VARCHAR(255) NOT NULL,
    answer_c VARCHAR(255) NOT NULL,
    answer_d VARCHAR(255) NOT NULL,
	test_id INT NOT NULL,
    FOREIGN KEY (test_id) 
    REFERENCES Test(id)
);

INSERT INTO Question 
(id, question_content, answer_a, answer_b, answer_c, answer_d, test_id) 
VALUES
(1, 'What does HTML stand for?', 'Hyper Text Markup Language', 'Home Tool Markup Language', 'Hyperlinks Text Mark Language', 'Hyper Tool Multi Language', 1),
(2, 'Which tag is used for the largest heading?', '< h6 >', '< heading >', '< h1 >', '< head >', 1),
(3, 'Which CSS property controls text size?', 'font-style', 'text-size', 'font-size', 'text-style', 1),
(4, 'What does CSS stand for?', 'Creative Style Sheets', 'Cascading Style Sheets', 'Computer Style Sheets', 'Colorful Style Sheets', 1),
(5, 'Which property is used for Flexbox?', 'display: grid', 'display: flex', 'position: flex', 'flex: display', 1),
(6, 'Which HTML tag is used for links?', '< a >', '< link >', '< href >', '< url >', 1),
(7, 'Which property changes background color?', 'color', 'bgcolor', 'background-color', 'background-style', 1),
(8, 'Which tag is used for images?', '< img >', '< image >', '< src >', '< pic >', 1),
(9, 'What is the default display of div?', 'inline', 'block', 'flex', 'grid', 1),
(10, 'Which CSS layout is 2D?', 'Flexbox', 'Grid', 'Inline', 'Block', 1),

(11, 'Which keyword declares a variable?', 'var', 'int', 'string', 'float', 2),
(12, 'Which symbol is used for comments?', '//', '< !-- -- >', '#', '/* */', 2),
(13, 'What is Git used for?', 'Styling', 'Version control', 'Database', 'Hosting', 2),
(14, 'Which method prints in console?', 'print()', 'echo()', 'console.log()', 'log()', 2),
(15, 'Which company created JavaScript?', 'Microsoft', 'Netscape', 'Google', 'Apple', 2),
(16, 'Which command initializes git?', 'git init', 'git start', 'git create', 'git new', 2),
(17, 'Which is a JS data type?', 'number', 'integer', 'decimal', 'float', 2),
(18, 'Which operator compares value and type?', '==', '=', '===', '!=', 2),
(19, 'Which command adds files to staging?', 'git push', 'git add', 'git commit', 'git pull', 2),
(20, 'Which loop repeats code?', 'if', 'for', 'switch', 'case', 2),

(21, 'What is GitHub?', 'IDE', 'Hosting platform', 'Language', 'Framework', 3),
(22, 'What does npm stand for?', 'Node Package Manager', 'New Package Manager', 'Node Project Manager', 'Next Package Manager', 3),
(23, 'Which command installs packages?', 'npm start', 'npm install', 'npm run', 'npm build', 3),
(24, 'What is Yarn?', 'Language', 'Package manager', 'Framework', 'IDE', 3),
(25, 'Which file stores dependencies?', 'index.js', 'package.json', 'app.js', 'config.json', 3),
(26, 'Which command pushes code?', 'git pull', 'git push', 'git fetch', 'git clone', 3),
(27, 'What is a repository?', 'Folder', 'Storage for code', 'File', 'Branch', 3),
(28, 'Which command clones repo?', 'git fork', 'git clone', 'git copy', 'git pull', 3),
(29, 'Which is alternative to npm?', 'pip', 'yarn', 'composer', 'gem', 3),
(30, 'What is GitLab?', 'Game', 'Hosting platform', 'Library', 'OS', 3),

(31, 'What is React?', 'Library', 'Language', 'Database', 'OS', 4),
(32, 'Who developed React?', 'Google', 'Facebook', 'Microsoft', 'Apple', 4),
(33, 'What is a component?', 'Function', 'Reusable UI', 'Database', 'API', 4),
(34, 'What hook manages state?', 'useEffect', 'useState', 'useRef', 'useMemo', 4),
(35, 'JSX stands for?', 'Java Syntax', 'JavaScript XML', 'JSON XML', 'Java Extend', 4),
(36, 'What is Vite?', 'Bundler', 'Database', 'Language', 'API', 4),
(37, 'Props are?', 'Functions', 'Inputs', 'Outputs', 'Hooks', 4),
(38, 'Which hook runs side effects?', 'useState', 'useEffect', 'useRef', 'useCall', 4),
(39, 'React uses?', 'DOM', 'Virtual DOM', 'XML', 'JSON', 4),
(40, 'Which tool bundles code?', 'Webpack', 'Node', 'Express', 'MongoDB', 4),

(41, 'What is testing?', 'Debugging', 'Checking code', 'Deploying', 'Building', 5),
(42, 'Unit testing tests?', 'Whole app', 'Single unit', 'Database', 'Server', 5),
(43, 'Jest is?', 'Framework', 'Testing tool', 'Library', 'IDE', 5),
(44, 'E2E stands for?', 'End to End', 'Easy to Execute', 'End Execute', 'Error to End', 5),
(45, 'Cypress is used for?', 'Unit test', 'E2E test', 'Build', 'Deploy', 5),
(46, 'Assertion means?', 'Check value', 'Assign value', 'Create value', 'Delete value', 5),
(47, 'Integration testing?', 'Single test', 'Combined units', 'UI test', 'Manual test', 5),
(48, 'Mocking is?', 'Real data', 'Fake data', 'No data', 'Live data', 5),
(49, 'Test case is?', 'Bug', 'Scenario', 'Error', 'Fix', 5),
(50, 'Automation testing?', 'Manual', 'Scripted', 'Random', 'Visual', 5);

/**
 * Answer table
 */
CREATE TABLE Answer (
	id INT AUTO_INCREMENT PRIMARY KEY NOT NULL,
    answer VARCHAR(255) NOT NULL,
    question_id INT NOT NULL,
    FOREIGN KEY (question_id)
    REFERENCES Question(id)
);

INSERT INTO Answer (id, answer, question_id) VALUES
(1, 'Hyper Text Markup Language', 1),
(2, '< h1 >', 2),
(3, 'font-size', 3),
(4, 'Cascading Style Sheets', 4),
(5, 'display: flex', 5),
(6, '< a >', 6),
(7, 'background-color', 7),
(8, '< img >', 8),
(9, 'block', 9),
(10, 'Grid', 10),

(11, 'var', 11),
(12, '//', 12),
(13, 'Version control', 13),
(14, 'console.log()', 14),
(15, 'Netscape', 15),
(16, 'git init', 16),
(17, 'number', 17),
(18, '===', 18),
(19, 'git add', 19),
(20, 'for', 20),

(21, 'Hosting platform', 21),
(22, 'Node Package Manager', 22),
(23, 'npm install', 23),
(24, 'Package manager', 24),
(25, 'package.json', 25),
(26, 'git push', 26),
(27, 'Storage for code', 27),
(28, 'git clone', 28),
(29, 'yarn', 29),
(30, 'Hosting platform', 30),

(31, 'Library', 31),
(32, 'Facebook', 32),
(33, 'Reusable UI', 33),
(34, 'useState', 34),
(35, 'JavaScript XML', 35),
(36, 'Bundler', 36),
(37, 'Inputs', 37),
(38, 'useEffect', 38),
(39, 'Virtual DOM', 39),
(40, 'Webpack', 40),

(41, 'Checking code', 41),
(42, 'Single unit', 42),
(43, 'Testing tool', 43),
(44, 'End to End', 44),
(45, 'E2E test', 45),
(46, 'Check value', 46),
(47, 'Combined units', 47),
(48, 'Fake data', 48),
(49, 'Scenario', 49),
(50, 'Scripted', 50);

/**
 * User table
 */
CREATE TABLE User (
	id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
	user_name VARCHAR(255) NOT NULL,
	email VARCHAR(255) NOT NULL,
	password VARCHAR(255) NOT NULL,
	avatar VARCHAR(255) NOT NULL,
	score INT NOT NULL DEFAULT 0,
	user_type VARCHAR(10)
);

/**
 * Role table
 */
CREATE TABLE Role(
	id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
	role_name VARCHAR(50) NOT NULL UNIQUE
);

INSERT INTO Role (role_name) VALUES
('admin'),
('lecturer'),
('user');

UPDATE Role SET role_name = "USER" WHERE id = 3;

/**
 * UserToRole table
 */
CREATE TABLE UserToRole (
	user_id INT NOT NULL,
	role_id INT NOT NULL,
	FOREIGN KEY (user_id) REFERENCES User(id),
	FOREIGN KEY (role_id) REFERENCES Role(id)
);

SELECT * FROM Lesson;
SELECT * FROM Course;
SELECT * FROM Test;
SELECT * FROM Question;
SELECT * FROM Answer;
SELECT * FROM User;
SELECT * FROM Role;
SELECT * FROM UserToRole;