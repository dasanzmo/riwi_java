/*Especialidad

• id_especialidad (Clave primaria, entero, autoincremental)
• nombre (Cadena)
• descripcion (Cadena)

Médico

• id_medico (Clave primaria, entero, autoincremental)
• nombre (Cadena)
• apellidos (Cadena)
• id_especialidad (Clave foránea, entero, referencia a Especialidad)

Paciente

• id_paciente (Clave primaria, entero, autoincremental)
• nombre (Cadena)
• apellidos (Cadena)
• fecha_nacimiento (Fecha)
• documento_identidad (Cadena)
Cita

• id_cita (Clave primaria, entero, autoincremental)
• id_paciente (Clave foránea, entero, referencia a Paciente)
• id_medico (Clave foránea, entero, referencia a Médico)
• fecha_cita (Fecha)
• hora_cita (Hora)
• motivo (Cadena)*/

CREATE DATABASE hospital;

USE hospital;

CREATE TABLE specialty(
	id_specialty int auto_increment primary key,
    name varchar(50) not null,
    description varchar(100) not null
);

CREATE TABLE paciente (
	id_patient INT auto_increment primary key,
    name varchar(50) not null,
    apellidos varchar(50) not null,
    fecha_nacimiento date not null,
    documento_identidad int not null
);

ALTER TABLE paciente RENAME to patient;

ALTER TABLE patient
CHANGE COLUMN apellidos last_name varchar(50) not null;

ALTER TABLE patient
CHANGE COLUMN fecha_nacimiento birthdate varchar(50) not null;

ALTER TABLE patient
CHANGE COLUMN documento_identidad identity_document varchar(50) not null;

CREATE TABLE doctor (
	id_doctor int auto_increment primary key,
    name varchar(50) not null,
    last_name varchar(50) not null,
    id_specialty int,
    constraint fk_id_specialty foreign key(id_specialty) references specialty(id_specialty)
);

CREATE TABLE appointment(
	id_appointment int auto_increment primary key,
    id_patient int,
	constraint fk_id_patient foreign key(id_patient) references patient(id_patient),
    id_doctor int,
	constraint fk_id_doctor foreign key(id_doctor) references doctor(id_doctor),
    date_appointment date not null,
    appointment_time time not null,
    reason varchar(100) not null
);

INSERT INTO specialty (name,description) VALUES ("Pediatrician","Children's specialist");
INSERT INTO doctor(name,last_name,id_specialty)VALUES("Daniel", "Sanchez",1);
INSERT INTO patient (name,last_name,birthdate,identity_document)VALUES("Susana","Duque","1999-02-01",1037666687);
INSERT INTO appointment(id_patient, date_appointment,appointment_time,reason)VALUES(1,"2024-05-20","12:00:00","General check");
update appointment set id_doctor = 1 where id_appointment = 1;

SELECT *
FROM appointment
INNER JOIN doctor ON appointment.id_doctor = doctor.id_doctor
INNER JOIN patient ON appointment.id_patient = patient.id_patient;

SELECT * FROM doctor
INNER JOIN specialty ON doctor.id_specialty = specialty.id_specialty;


