/*
Avión

• id_avion (Clave primaria, entero, autoincremental)
• modelo (Cadena)
• capacidad (Entero)

Vuelo

• id_vuelo (Clave primaria, entero, autoincremental)
• destino (Cadena)
• fecha_salida (Fecha)
• hora_salida (Hora)
• id_avion (Clave foránea, entero, referencia a Avión)

Pasajero

• id_pasajero (Clave primaria, entero, autoincremental)
• nombre (Cadena)
• apellido (Cadena)
• documento_identidad (Cadena)

Reservación

• id_reservacion (Clave primaria, entero, autoincremental)
• id_pasajero (Clave foránea, entero, referencia a Pasajero)
• id_vuelo (Clave foránea, entero, referencia a Vuelo)
• fecha_reservacion (Fecha)
• asiento (Cadena)
*/

CREATE DATABASE aerolinea;

use aerolinea;

CREATE TABLE pasajero(
	id_pasajero int(11) auto_increment primary key,
    nombre varchar(255) not null,
    appelido varchar(255) not null,
    documento_identidad varchar(255) not null
);

CREATE TABLE avion(
	id_avion int(11) auto_increment primary key,
    modelo varchar(255) not null,
    capacidad int(11) not null
);

CREATE TABLE vuelo(
	id_vuelo int(11) auto_increment primary key,
    destino varchar(255) not null,
    fecha_salida date not null,
    hora_salida time not null,
    id_avion int not null,
    constraint fk_id_avion foreign key(id_avion) references avion(id_avion)
);

CREATE TABLE reservacion (
	id_reservacion int(11) auto_increment primary key,
    id_pasajero int(11) not null,
    id_vuelo int(11) not null,
    fecha_reservacion date not null,
    asiento varchar(255) not null,
    constraint fk_id_pasajero foreign key(id_pasajero) references pasajero(id_pasajero),
    constraint fk_id_vuelo foreign key(id_vuelo) references vuelo(id_vuelo)
);