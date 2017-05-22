CREATE TABLE users (
  id VARCHAR(255) PRIMARY KEY,
  name VARCHAR(255) NOT NULL,
  email VARCHAR(255) NOT NULL,
  role VARCHAR(255),
  password varchar(255)
);

insert into users (id, name, email, role, password) values ("001", "admin", "admin@example.com", "BACKGROUND_JOB", "$2a$04$DbgJbGA4dkQSzAvXvJcGBOv5kHuMBzrWfne3x3Cx4JQv4IJcxtBIW");


CREATE TABLE employees (
  id VARCHAR(255) PRIMARY KEY,
  name VARCHAR(255),
  department_id VARCHAR(255),
  role_id VARCHAR(255),
  gender varchar(255),
  created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE attendances (
  id VARCHAR(255) PRIMARY KEY,
  employee_id VARCHAR(255),
  from_date VARCHAR(255),
  to_date VARCHAR(255),
  description VARCHAR(255),
  present bool,
  created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);