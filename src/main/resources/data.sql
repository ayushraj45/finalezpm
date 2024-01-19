USE globalpmdb;
DELETE FROM USER_TASK;
DELETE FROM USER_GOAL;
DELETE FROM TASK;
DELETE FROM GOAL;
DELETE FROM USER_PROJECT;
DELETE FROM PROJECT;
DELETE FROM USER;

ALTER TABLE project MODIFY `completion_status` varchar(20) DEFAULT false;

INSERT INTO PROJECT(ID, NAME) VALUES
(001, 'PROJECT1'),
(002, 'PROJECT2');

INSERT INTO GOAL(id, goal_name, project_id,completion_status) VALUES
(101,'GOAL1',001,false),
(102,'GOAL2',001,true);

INSERT INTO TASK(id,description,name,goal_id,completion_status) VALUES
(110, 'TASK1', 'TD1', 101,true),
(120, 'Task2', 'TD2', 101,false);

insert into user(id, name) values (1001,'ayushraj');

INSERT INTO user_project(user_id,project_id) VALUES (1001,001);


