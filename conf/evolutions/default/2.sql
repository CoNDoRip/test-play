# --- Sample dataset

# --- !Ups

INSERT INTO employee (first_name, last_name, second_name, age, experience, description) 
       VALUES ('John', 'Smith', '', 25, 10, 'Java developer');
INSERT INTO employee (first_name, last_name, second_name, age, experience, description) 
       VALUES ('Mike', 'White', '', 31, 5, 'DBA');
INSERT INTO employee (first_name, last_name, second_name, age, experience, description) 
       VALUES ('Eva', 'Lee', '', 23, 3, 'Manager');
INSERT INTO employee (first_name, last_name, second_name, age, experience, description) 
       VALUES ('Carla', 'Sarkozy', '', 29, 5, 'PS Consultant');
INSERT INTO employee (first_name, last_name, second_name, age, experience, description) 
       VALUES ('Harry', 'Grey', '', 27, 7, 'Designer');


# --- !Downs

DELETE FROM employee;
