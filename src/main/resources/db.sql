create database practica_mvn_mvc_bd;
use practica_mvn_mvc_bd;

create table estudiante (id_estudiante  int auto_increment primary key,
 nombre varchar (50), 
 edad int);
desc estudiante;

create table profesor (id_profesor int auto_increment primary key
, id_estudiante int,
 nombre varchar (50),
 materia varchar (30),
 correo_electronico varchar (50),
 foreign key (id_estudiante) references estudiante (id_estudiante));
 desc profesor;
 
 create table curso (id_curso int primary key auto_increment,
 curso int,
 id_estudiante int,
 id_profesor int,
 foreign key (id_estudiante) references estudiante (id_estudiante),
 foreign key ( id_profesor) references profesor (id_profesor));
 desc curso;
select * from estudiante;
select * from profesor;
select * from curso;

