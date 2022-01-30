DROP database quizmanagerdb;
DROP user quizmanager;

CREATE USER quizmanager WITH password 'password';
CREATE database quizmanagerdb with template=template0 owner=quizmanager;
\connect quizmanagerdb;
alter default privileges grant all on tables to quizmanager;
alter default privileges grant all on sequences to quizmanager;

CREATE table qm_roles(
    role_id integer primary key not null,
    role_name varchar(20) not null
);

CREATE table qm_users(
    user_id integer primary key not null,
    role_id integer not null,
    first_name varchar(20) not null,
    last_name varchar(20) not null,
    email varchar(50) not null,
    password text not null
);
alter table qm_users add constraint role_users_fk
foreign key (role_id) references qm_roles(role_id);

CREATE TABLE qm_quizzes(
    quiz_id integer primary key not null,
    title varchar(20) not null,
    description varchar(50) not null
);

CREATE table qm_questions(
    question_id integer primary key not null,
    quiz_id integer not null,
    question varchar(250) not null
);
alter table qm_questions add constraint ques_quiz_fk
foreign key (quiz_id) references qm_quizzes(quiz_id);

create table qm_responses(
    response_id integer primary key not null,
    question_id integer not null,
    response varchar(150) not null,
    answer boolean
);
alter table qm_responses add constraint resp_ques_fk
foreign key (question_id) references qm_questions(question_id);

CREATE SEQUENCE qm_roles_seq increment 1 start 1;
create sequence qm_users_seq increment 1 start 1;
create sequence qm_quizzes_seq increment 1 start 1;
create sequence qm_question_seq increment 1 start 100;
create sequence qm_responses_seq increment 1 start 1000;

insert into qm_roles (role_id, role_name)
values (NEXTVAL('qm_roles_seq'), 'Taker'),
       (NEXTVAL('qm_roles_seq'), 'Marker'),
       (NEXTVAL('qm_roles_seq'), 'Setter');
