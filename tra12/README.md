#TEAM READINESS ACTIVITY
PREPARE

Create a simple Java application that lists authors and the books they have written using JPA. For simplicity, we will assume that each book was only written by one author (but authors can write multiple books). Also, for simplicity, you may write this as a standalone Java application (not a web app).

##TEACH ONE ANOTHER
INSTRUCTIONS
Using JPA, list all the authors in the DB with the books they have written. Then, using JPA, in your Java code, create a new author, and a new book written by that author, add them to the database, and show the list again.

To assist you in getting started, please follow these steps:

1. Create a new MySQL database for your authors:

CREATE DATABASE library;

2. Create a new User with permissions in that library:

GRANT ALL PRIVILEGES on library.* TO 'libraryAdmin'@'localhost' IDENTIFIED BY 'libraryPass';
FLUSH PRIVILEGES;

3. Create tables for Authors and Books:

USE library;

CREATE TABLE author (id INT PRIMARY KEY AUTO_INCREMENT, name VARCHAR(100) NOT NULL);

CREATE TABLE book (id INT PRIMARY KEY AUTO_INCREMENT, title VARCHAR(100) NOT NULL, author_id INT NOT NULL, FOREIGN KEY (author_id) REFERENCES author(id));

4. Insert a few rows into each table:

(Note that the second query here assumes that the first succeeded and gave the id of 1 to Mark Twain, and 2 to C.S. Lewis)

INSERT INTO author (name) VALUES ("Mark Twain"), ("C.S. Lewis");

INSERT INTO book (title, author_id) VALUES ("Tom Sawyer", 1), ("Huckleberry Finn", 1), ("The Great Divorce", 2), ("Mere Cristianity", 2);

5. Verify the data in your database:

SELECT * FROM author AS a INNER JOIN book AS b ON a.id = b.author_id;

6. Reference the JPA example:

Make sure to reference the JPA Example using Students/Majors from this week's preparation material, as it is very similar to your task here.

PROVE
Please zip up and submit a copy of your JSPs and java files (servlets and any other classes) that you used for this assignment to I-Learn. Do not include any JARs with your submission.
