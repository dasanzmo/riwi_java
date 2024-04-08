/*CREO LA BASE DE DATOS*/
CREATE DATABASE demoda_outlet;

/*USO LA BASE DE DATOS*/
use demoda_outlet;

/*CREO LA TABLA TIENDA*/
CREATE TABLE tienda (
	id_tienda int(11) auto_increment primary key,
    nombre varchar(255),
    ubicacion varchar(255)
);

/*AGREGO VALORES A LA TABLA TIENDA*/
INSERT INTO tienda(nombre, ubicacion) VALUES 
("D'moda Outlet Guayabal","Guayabal"),
("D'moda Outlet Poblado", "Poblado");

/*HAGO UNA CONSULTA Y VERIFICO LA TABLA TIENDA*/
SELECT *FROM tienda;


/*CREO LA TABLA PRODUCTO*/
CREATE TABLE producto(
	id_producto int auto_increment primary key,
    nombre varchar(255),
    precio decimal (10,2),
    id_tienda int(11),
	constraint fk_id_tienda foreign key(id_tienda) references tienda(id_tienda) 
    ON DELETE CASCADE
);

/*INSERTO VALORES A LA TABLA PRODUCTO*/
INSERT INTO producto(nombre, precio, id_tienda,stock) VALUES 
("Chaqueta Superdry",200.500),
("Tenis Diesel Blancos",120.500);

/*HAGO UNA CONSULTA Y VERIFICO LA TABLA PRODUCTO*/
SELECT * from producto;

/*MODIFICO LA TABLA PRODUCTO Y AGREGO UN CAMPO STOCK NUMÉRICO*/
ALTER TABLE producto
ADD COLUMN stock int(11);

/*ACTUALIZO VALORES EN LA TABLA PRODUCTO EN LA COLUMNA STOCK*/
UPDATE producto SET stock = 100 WHERE 'id_producto' = '1';
UPDATE producto SET stock = 50 WHERE 'id_producto' = '2';

/*CREO LA TABLA CLIENTE*/
CREATE TABLE cliente(
	id_cliente int auto_increment primary key,
    nombre varchar(255),
    apellido varchar(255),
    email varchar(255)
);

/*INSERTO VALORES EN LA TABLA CLIENTE*/
INSERT INTO cliente(nombre, apellido, email) VALUES 
("Daniel","Sanchez","danielsanchezm92@gmail.com"),
("Susana","Duque","susanaduque@gmail.com");

/*HAGO UNA CONSULTA Y VERIFICO LA TABLA CLIENTE*/
SELECT * from cliente;

/*CREO LA TABLA COMPRA*/
CREATE TABLE compra(
	id_compra int(11) auto_increment primary key,
	id_cliente int(11),
	id_producto int(11),
    fecha_compra timestamp,
    cantidad int(11),
	constraint fk_id_cliente foreign key(id_cliente) references cliente(id_cliente) 
    ON DELETE CASCADE,
	constraint fk_id_producto foreign key(id_producto) references producto(id_producto) 
    ON DELETE CASCADE
);

/*AGREGO VALORES A LA TABLA COMPRA*/
INSERT INTO compra(id_cliente, id_producto,cantidad) VALUES 
(1,2,50),
(2,1,100);

/*HAGO UNA CONSULTA Y VERIFICO LA TABLA TIENDA*/
SELECT * from compra INNER JOIN cliente on compra.id_cliente = cliente.id_cliente;