create table Pokemon (
    id int auto_increment primary key,
    evolution_id int,
    species_id int,
    pic_name varchar(100),
    ability_ids varchar(100),
    element_ids varchar(100),
    weaknesses_ids varchar(100),
    name varchar(50),
    gender varchar(1),
    height int,
    weight int
);

create table Element (
    id int auto_increment primary key,
    pic_name varchar(100),
    name varchar(50)
);

create table Location (
    id int auto_increment primary key,
    element_ids varchar(100),
    name varchar(50)
);

create table Ability (
    id int auto_increment primary key,
    element_id int,
    name varchar(50)
);

create table Species (
    id int auto_increment primary key,
    name varchar(50)
);