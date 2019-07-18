create table emp_task (task_id varchar(255) not null, employee_id varchar(255) not null) 
create table employee (employee_id varchar(255) not null, employee_name varchar(255) not null, project_id varchar(255) not null, primary key (employee_id)) 
create table project (project_id varchar(255) not null, project_name varchar(255) not null, primary key (project_id)) 
create table task (task_id varchar(255) not null, created_timestamp timestamp not null, description varchar(255) not null, due_date_of_task date not null, start_date_of_task date not null, project_id varchar(255), primary key (task_id))  

alter table project add constraint uk_project_name unique (project_name) 
alter table emp_task add constraint fk_emp_task foreign key (employee_id) references employee 
alter table emp_task add constraint fk_task_emp foreign key (task_id) references task 
alter table employee add constraint fk_emp_proj foreign key (project_id) references project 
alter table task add constraint fk_task_proj foreign key (project_id) references project 

insert into project (project_name, project_id) values ('iPhone UI', 'project1')
insert into task (created_timestamp, description, due_date_of_task, project_id, start_date_of_task, task_id) values (TO_DATE('07/15/2019 13:05:27.495', 'mm/dd/yyyy hh:mm:ss.XXX'), 'Push Notifications', TO_DATE('01/03/2019', 'mm/dd/yyyy'), 'project1', TO_DATE('01/01/2019', 'mm/dd/yyyy'), 'task1')
insert into employee (employee_name, project_id, employee_id) values ('Rahul Dev', 'project1', 'M1001075') 
insert into employee (employee_name, project_id, employee_id) values ('Anjan G', 'project1', 'M1001099') 
insert into employee (employee_name, project_id, employee_id) values ('Bhushan S', 'project1', 'M1001119') 
insert into task (created_timestamp, description, due_date_of_task, project_id, start_date_of_task, task_id) values (TO_DATE('07/16/2019 13:05:27.495', 'mm/dd/yyyy hh:mm:ss.XXX'), 'Help Screen', TO_DATE('01/03/2019', 'mm/dd/yyyy'), 'project1', TO_DATE('01/01/2019', 'mm/dd/yyyy'), 'task2')
insert into employee (employee_name, project_id, employee_id) values ('Kavitha B', 'project1', 'M1002000') 
insert into employee (employee_name, project_id, employee_id) values ('Rohit Reddy', 'project1', 'M1001072') 
insert into employee (employee_name, project_id, employee_id) values ('Sanjay J', 'project1', 'M1001001') 
insert into emp_task (employee_id, task_id) values ('M1001075', 'task1') 
insert into emp_task (employee_id, task_id) values ('M1001099', 'task1') 
insert into emp_task (employee_id, task_id) values ('M1001119', 'task1') 
insert into emp_task (employee_id, task_id) values ('M1002000', 'task2') 
insert into emp_task (employee_id, task_id) values ('M1001072', 'task2')

insert into project (project_name, project_id) values ('iPad Bugs', 'project2') 
insert into task (created_timestamp, description, due_date_of_task, project_id, start_date_of_task, task_id) values (TO_DATE('07/16/2019 14:05:27.495', 'mm/dd/yyyy hh:mm:ss.XXX'), 'Graphics Slicing', TO_DATE('01/03/2019', 'mm/dd/yyyy'), 'project2', TO_DATE('01/01/2019', 'mm/dd/yyyy'), 'task3')
insert into employee (employee_name, project_id, employee_id) values ('Balaji B', 'project2', 'M1002001') 
insert into employee (employee_name, project_id, employee_id) values ('Ravi K', 'project2', 'M1001073') 
insert into employee (employee_name, project_id, employee_id) values ('Dinesh K', 'project2', 'M1001002') 
insert into emp_task (employee_id, task_id) values ('M1002001', 'task3') 
insert into emp_task (employee_id, task_id) values ('M1001073', 'task3') 
