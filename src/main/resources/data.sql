USE globalpmdb;
DELETE FROM TASK;
DELETE FROM GOAL;
DELETE FROM USER_PROJECT;
DELETE FROM PROJECT;
DELETE FROM user;

INSERT INTO PROJECT(ID, NAME) VALUES
(001, 'PROJECT1'),
(002, 'PROJECT2');

INSERT INTO GOAL(id, goal_name, project_id) VALUES
(101,'GOAL1',001),
(102,'GOAL2',002);

INSERT INTO TASK(id,description,name,goal_id) VALUES
(110, 'TASK1', 'TD1', 101);

insert into user(id, name) values (1001,'ayushraj');

INSERT INTO user_project(user_id,project_id) VALUES (1001,001);


