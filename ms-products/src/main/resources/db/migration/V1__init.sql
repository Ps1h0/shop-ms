create table products (
    id bigserial primary key,
    name varchar(255),
    cost double precision,
    created_time timestamp default current_timestamp,
    updated_time timestamp default current_timestamp
);

insert into products (name, cost) values
('A', 10),
('B', 20),
('C', 30),
('D', 40),
('E', 50),
('F', 60),
('G', 70),
('H', 80),
('I', 90),
('J', 100),
('K', 110),
('L', 120),
('M', 130);