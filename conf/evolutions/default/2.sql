# --- Sample dataset

# --- !Ups

INSERT INTO employee (first_name, last_name, second_name, age, experience, description) 
       VALUES ('John', 'Smith', '', 25, '10 years', 'Java developer');
INSERT INTO employee (first_name, last_name, second_name, age, experience, description) 
       VALUES ('Mike', 'White', '', 31, '5 years', 'DBA');
INSERT INTO employee (first_name, last_name, second_name, age, experience, description) 
       VALUES ('Eva', 'Lee', '', 23, '3 years', 'Manager');
INSERT INTO employee (first_name, last_name, second_name, age, experience, description) 
       VALUES ('Carla', 'Sarkozy', '', 29, '5 years', 'PS Consultant');
INSERT INTO employee (first_name, last_name, second_name, age, experience, description) 
       VALUES ('Harry', 'Grey', '', 27, '7 years', 'Designer');


# --- !Downs

DELETE FROM employee;