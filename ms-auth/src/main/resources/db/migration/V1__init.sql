create table role_table
(
    id   serial      not null
        constraint role_table_pk
            primary key,
    name varchar(20) not null
);

create table user_table
(
    id       bigserial not null
        constraint user_table_pk
            primary key,
    email    varchar(50),
    password varchar(500)
);

create table users_roles
(
    user_id bigint references user_table(id),
    role_id int references role_table(id)
);

insert into role_table(name) values ('ROLE_ADMIN');
insert into role_table(name) values ('ROLE_USER');

