# README

## PROYECTO: AGENCIA VUELOS GLOBALES

**Autores:**

* Sebastian Vergara Velasquez
* Fabian Augusto Rodriguez Rojas

### Introducción 

En el mundo actual, el desarrollo de software es fundamental para el funcionamiento eficiente y seguro de una aerolínea. La tecnología ha revolucionado la forma en que las aerolíneas operan, desde la gestión de vuelos y el mantenimiento de aeronaves hasta la experiencia del cliente. En una industria donde la puntualidad, la seguridad y la satisfacción del cliente son cruciales, el software se convierte en un aliado indispensable. 

Las aerolíneas se enfrentan a numerosos desafíos que requieren soluciones tecnológicas avanzadas. Estos desafíos incluyen la gestión de una vasta cantidad de datos, la optimización de rutas de vuelo, el mantenimiento predictivo de aeronaves y la mejora de la experiencia del pasajero. Para abordar estos desafíos, las aerolíneas necesitan sistemas de software robustos, integrados y escalables

### Metodología

La metodología de cascada, también conocida como modelo en cascada, es un enfoque secuencial y lineal para el desarrollo de software. Se caracteriza por dividir el proceso de desarrollo en etapas bien definidas, donde cada fase debe completarse antes de pasar a la siguiente.

En este proyecto, integramos la metodología de cascada con una arquitectura hexagonal y el enfoque de vertical slicing. Esta combinación nos permite seguir un proceso estructurado y lineal, mientras organizamos y gestionamos las carpetas y funcionalidades del sistema de manera eficiente y modular.

### Diseño base de datos

![DER Database agencia](https://github.com/SebastianVergaraVelasquez/agenciavuelosglobales/blob/main/DER_airport.png?raw=true)


### Tecnologías utilizadas

* **Java:**
  * Java es un lenguaje de programación versátil y de uso general, muy empleado en el desarrollo de aplicaciones empresariales, móviles, juegos, entre otros. Su portabilidad, robustez, seguridad y facilidad de uso lo hacen una elección popular entre los desarrolladores.
* **Maven:**
  * Maven es una herramienta de gestión y comprensión de proyectos, utilizada principalmente en proyectos Java. Facilita la construcción, reporte y documentación del proyecto a través de su concepto de POM (Project Object Model). Sus ventajas incluyen la gestión eficiente de dependencias, la capacidad de integrarse con herramientas de construcción y la facilidad de automatización del ciclo de vida del proyecto.
* **Visual Studio Code:**
  * Visual Studio Code es un editor de código fuente ligero y altamente personalizable creado por Microsoft. Es muy utilizado por los desarrolladores gracias a su amplia gama de extensiones, capacidades integradas de depuración, integración con Git y herramientas de productividad.
* **MySQL:**
  * MySQL es un sistema de gestión de bases de datos relacional que administra la información de una plataforma, asegurando su disponibilidad tanto para el servidor como para el cliente. Sus ventajas incluyen la protección de datos, facilidad de instalación y administración, y funciones de recuperación y restauración de datos. Entre sus desventajas están la falta de documentación oficial en algunas utilidades y la necesidad de monitorear el rendimiento de las aplicaciones.
* **Git:**
  * Git es un sistema de control de versiones distribuido, ampliamente utilizado en el desarrollo de software. Permite a los desarrolladores seguir los cambios en el código fuente, colaborar con otros miembros del equipo, y realizar ramificaciones y fusiones de código de manera eficiente.

### Estructura de cada modulo

Cada módulo está estructurado en varias capas, asegurando una clara separación de responsabilidades. A continuación se presenta un ejemplo detallado de la estructura del módulo `airport`:

``` 
 ┣ 📂airport
 ┣ 📂adapters
 ┃ ┣ 📂in
 ┃ ┃ ┗ 📜AirportConsoleAdapter.java -> Maneja la entrada de datos desde la consola y transforma las solicitudes en comandos que pueden ser procesados por la capa de aplicación.
 ┃ ┗ 📂out
 ┃ ┃ ┗ 📜AirportMYSQLRepository.java -> Implementa la interfaz de acceso a datos, interactuando con la base de datos MySQL para realizar operaciones de CRUD (crear, leer, actualizar, eliminar).
 ┃ ┣ 📂application
 ┃ ┃ ┗ 📜AirportService.java -> Contiene la lógica de negocio del módulo, gestionando las operaciones y coordinando las interacciones entre los distintos componentes.
 ┃ ┣ 📂domain
 ┃ ┃ ┗ 📂models
 ┃ ┃ ┃ ┣ 📜Airport.java -> Representa un modelo de datos para un aeropuerto con atributos como ID, nombre y ID de ciudad, facilitando la gestión y manipulación de información dentro del sistema.
 ┃ ┃ ┃ ┗ 📜AirportDTO.java -> Representa un DTO (Data Transfer Object)que encapsula datos de un aeropuerto, facilitando la transferencia de información entre componentes del sistema.
 ┃ ┗ 📂infrastructure
 ┃ ┃ ┗ 📜AirportRepository.java -> Define las interfaces y las implementaciones concretas para el acceso a los datos, asegurando que la lógica de negocio no esté acoplada a la tecnología de persistencia específica.
```

### Creación base de datos

```sql

-- -----------------------------------------------------
-- Database mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Database agencia
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Database agencia
-- -----------------------------------------------------
CREATE DATABASE IF NOT EXISTS agencia;
USE agencia;

-- -----------------------------------------------------
-- Table airline
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS airline (
  id INT NOT NULL AUTO_INCREMENT,
  name VARCHAR(30) NOT NULL,
  PRIMARY KEY (id))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table country
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS country (
  id VARCHAR(5) NOT NULL,
  name VARCHAR(30) NOT NULL,
  PRIMARY KEY (id))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table city
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS city (
  id VARCHAR(5) NOT NULL,
  name VARCHAR(30) NOT NULL,
  id_country VARCHAR(5) NOT NULL,
  PRIMARY KEY (id),
  CONSTRAINT FK_cities_countries
    FOREIGN KEY (id_country)
    REFERENCES country (id))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table airport
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS airport (
  id VARCHAR(5) NOT NULL,
  name VARCHAR(50) NOT NULL,
  id_city VARCHAR(5) NOT NULL,
  PRIMARY KEY (id),
  CONSTRAINT FK_airports_cities
    FOREIGN KEY (id_city)
    REFERENCES city (id))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table manufacturer
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS manufacturer (
  id INT NOT NULL AUTO_INCREMENT,
  name VARCHAR(40) NOT NULL,
  PRIMARY KEY (id))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table model
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS model (
  id INT NOT NULL AUTO_INCREMENT,
  name VARCHAR(30) NOT NULL,
  id_manufacturer INT NOT NULL,
  PRIMARY KEY (id),
  CONSTRAINT FK_models_manufacturer
    FOREIGN KEY (id_manufacturer)
    REFERENCES manufacturer (id))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table status
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS status (
  id INT NOT NULL AUTO_INCREMENT,
  name VARCHAR(30) NOT NULL,
  PRIMARY KEY (id))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table plane
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS plane (
  id INT NOT NULL AUTO_INCREMENT,
  plates VARCHAR(30) NOT NULL,
  capacity INT NOT NULL,
  fabircation_date VARCHAR(30) NOT NULL,
  id_status INT NOT NULL,
  id_airline INT NOT NULL,
  id_model INT NOT NULL,
  PRIMARY KEY (id),
  CONSTRAINT FK_plane_airline
    FOREIGN KEY (id_airline)
    REFERENCES airline (id),
  CONSTRAINT FK_planes_models
    FOREIGN KEY (id_model)
    REFERENCES model (id),
  CONSTRAINT FK_planes_status
    FOREIGN KEY (id_status)
    REFERENCES status (id))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table trip
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS trip (
  id INT NOT NULL AUTO_INCREMENT,
  trip_date DATE NOT NULL,
  price_tripe DOUBLE NOT NULL,
  PRIMARY KEY (id))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table trip_status
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS trip_status (
  id INT NOT NULL AUTO_INCREMENT,
  name VARCHAR(30) NOT NULL,
  PRIMARY KEY (id))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table connection
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS connection (
  id INT NOT NULL AUTO_INCREMENT,
  connection_number VARCHAR(20) NOT NULL,
  id_trip INT NOT NULL,
  id_plane INT NULL,
  id_airport VARCHAR(5) NOT NULL,
  id_trip_status INT NOT NULL,
  PRIMARY KEY (id),
  CONSTRAINT FK_connections_airports
    FOREIGN KEY (id_airport)
    REFERENCES airport (id),
  CONSTRAINT FK_connections_plane
    FOREIGN KEY (id_plane)
    REFERENCES plane (id),
  CONSTRAINT FK_connections_trip
    FOREIGN KEY (id_trip)
    REFERENCES trip (id),
  CONSTRAINT FK_connections_trip_status
    FOREIGN KEY (id_trip_status)
    REFERENCES trip_status (id))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table document_type
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS document_type (
  id INT NOT NULL AUTO_INCREMENT,
  name VARCHAR(40) NOT NULL,
  PRIMARY KEY (id))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table customer
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS customer (
  id VARCHAR(20) NOT NULL,
  name VARCHAR(30) NOT NULL,
  age INT NOT NULL,
  id_document INT NOT NULL,
  PRIMARY KEY (id),
  CONSTRAINT FK_customers_document_types
    FOREIGN KEY (id_document)
    REFERENCES document_type (id))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table tripulation_role
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS tripulation_role (
  id INT NOT NULL AUTO_INCREMENT,
  name VARCHAR(40) NOT NULL,
  PRIMARY KEY (id))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table employee
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS employee (
  id VARCHAR(20) NOT NULL,
  name VARCHAR(40) NOT NULL,
  id_rol INT NOT NULL,
  ingress_date DATE NOT NULL,
  id_airline INT NULL,
  id_airport VARCHAR(5) NOT NULL,
  PRIMARY KEY (id),
  CONSTRAINT FK_employees_airlines
    FOREIGN KEY (id_airline)
    REFERENCES airline (id),
  CONSTRAINT FK_employees_airport
    FOREIGN KEY (id_airport)
    REFERENCES airport (id),
  CONSTRAINT FK_employees_tripulation_roles
    FOREIGN KEY (id_rol)
    REFERENCES tripulation_role (id))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table flight_fare
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS flight_fare (
  id INT NOT NULL AUTO_INCREMENT,
  description VARCHAR(20) NOT NULL,
  detail TEXT NOT NULL,
  value DOUBLE(7,3) NOT NULL,
  PRIMARY KEY (id))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table gate
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS gate (
  id INT NOT NULL AUTO_INCREMENT,
  gatenumber VARCHAR(10) NOT NULL,
  id_airport VARCHAR(5) NOT NULL,
  PRIMARY KEY (id),
  CONSTRAINT FK_gates_airports
    FOREIGN KEY (id_airport)
    REFERENCES airport (id))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table revision
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS revision (
  id INT NOT NULL AUTO_INCREMENT,
  revision_date DATE NOT NULL,
  id_plane INT NOT NULL,
  PRIMARY KEY (id),
  CONSTRAINT FK_revisions_planes
    FOREIGN KEY (id_plane)
    REFERENCES plane (id))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table rev_employee
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS rev_employee (
  id_employee VARCHAR(20) NOT NULL,
  id_revision INT NOT NULL,
  description TEXT NOT NULL,
  PRIMARY KEY (id_employee, id_revision),
  CONSTRAINT FK_rev_employee_employee
    FOREIGN KEY (id_employee)
    REFERENCES employee (id),
  CONSTRAINT FK_rev_employee_revision
    FOREIGN KEY (id_revision)
    REFERENCES revision (id))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table trip_booking
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS trip_booking (
  id INT NOT NULL AUTO_INCREMENT,
  date DATE NOT NULL,
  id_trip INT NOT NULL,
  PRIMARY KEY (id),
  CONSTRAINT FK_trip_booking_trips
    FOREIGN KEY (id_trip)
    REFERENCES trip (id))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table trip_condition
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS trip_condition (
  id INT NOT NULL AUTO_INCREMENT,
  name VARCHAR(45) NOT NULL,
  PRIMARY KEY (id))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table trip_booking_detail
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS trip_booking_detail (
  id INT NOT NULL AUTO_INCREMENT,
  id_trip_booking INT NOT NULL,
  id_customer VARCHAR(20) NOT NULL,
  id_fare INT NOT NULL,
  id_trip_condition INT NOT NULL,
  PRIMARY KEY (id),
  CONSTRAINT FK_trip_booking_details_customers
    FOREIGN KEY (id_customer)
    REFERENCES customer (id),
  CONSTRAINT FK_trip_booking_details_fares
    FOREIGN KEY (id_fare)
    REFERENCES flight_fare (id),
  CONSTRAINT FK_trip_booking_details_trip_booking
    FOREIGN KEY (id_trip_booking)
    REFERENCES trip_booking (id),
  CONSTRAINT fk_trip_booking_detail_trip_condition
    FOREIGN KEY (id_trip_condition)
    REFERENCES trip_condition (id))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table trip_crew
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS trip_crew (
  id_employee VARCHAR(20) NOT NULL,
  id_connection INT NOT NULL,
  PRIMARY KEY (id_employee, id_connection),
  CONSTRAINT FK_trip_crews_connection
    FOREIGN KEY (id_connection)
    REFERENCES connection (id),
  CONSTRAINT FK_trip_crews_employees
    FOREIGN KEY (id_employee)
    REFERENCES employee (id))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table passenger
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS passenger (
  id INT NOT NULL AUTO_INCREMENT,
  nif VARCHAR(20) NOT NULL,
  name VARCHAR(45) NOT NULL,
  age INT NOT NULL,
  seat VARCHAR(3) NOT NULL,
  id_document_type INT NOT NULL,
  id_trip_booking_detail INT NOT NULL,
  PRIMARY KEY (id),
  CONSTRAINT FK_passenger_document_type
    FOREIGN KEY (id_document_type)
    REFERENCES document_type (id),
  CONSTRAINT FK_passenger_trip_booking_detail
    FOREIGN KEY (id_trip_booking_detail)
    REFERENCES trip_booking_detail (id))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table pay_type
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS pay_type (
  id INT NOT NULL AUTO_INCREMENT,
  name VARCHAR(45) NOT NULL,
  PRIMARY KEY (id))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table payment
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS payment (
  id INT NOT NULL AUTO_INCREMENT,
  pay_date VARCHAR(45) NOT NULL,
  id_pay_type INT NOT NULL,
  total_pay DECIMAL(15,2) NOT NULL,
  id_customer VARCHAR(20) NOT NULL,
  PRIMARY KEY (id),
  CONSTRAINT FK_payment_customer
    FOREIGN KEY (id_customer)
    REFERENCES customer (id),
  CONSTRAINT FK_payment_pay_type
    FOREIGN KEY (id_pay_type)
    REFERENCES pay_type (id))
ENGINE = InnoDB;
```



### Inserción de datos

```sql
-- INSERTS PARA TABLA airline
INSERT INTO airline (name) VALUES
('Airline A'),
('Airline B'),
('Airline C');

-- INSERTS PARA TABLA country
INSERT INTO country (id, name) VALUES
('US', 'United States'),
('CA', 'Canada'),
('MX', 'Mexico');

-- INSERTS PARA TABLA city
INSERT INTO city (id, name, id_country) VALUES
('NYC', 'New York City', 'US'),
('TOR', 'Toronto', 'CA'),
('MEX', 'Mexico City', 'MX');

-- INSERTS PARA TABLA airport
INSERT INTO airport (id, name, id_city) VALUES
('JFK', 'John F. Kennedy International Airport', 'NYC'),
('YYZ', 'Toronto Pearson International Airport', 'TOR'),
('MEX', 'Mexico City International Airport', 'MEX');

-- INSERTS PARA TABLA manufacturer
INSERT INTO manufacturer (name) VALUES
('Boeing'),
('Airbus'),
('Embraer');

-- INSERTS PARA TABLA model
INSERT INTO model (name, id_manufacturer) VALUES
('737', 1),  -- Boeing 737
('A320', 2), -- Airbus A320
('E190', 3); -- Embraer E190

-- INSERTS PARA TABLA status
INSERT INTO status (name) VALUES
('Active'),
('Inactive'),
('Maintenance');

-- INSERTS PARA TABLA plane
INSERT INTO plane (plates, capacity, fabircation_date, id_status, id_airline, id_model) VALUES
('ABC123', 180, '2020-01-15', 1, 1, 1),  
('XYZ789', 220, '2018-05-20', 1, 2, 2);  

-- INSERTS PARA TABLA trip
INSERT INTO trip (trip_date, price_tripe) VALUES
('2024-07-10', 350.00),
('2024-07-15', 420.00),
('2024-07-20', 300.00);

-- INSERTS PARA TABLA trip_status
INSERT INTO trip_status (name) VALUES
('Origin'),
('Connection'),
('Destination');

-- INSERTS PARA TABLA connection
INSERT INTO connection (connection_number, id_trip, id_plane, id_airport, id_trip_status) VALUES
('CX001', 1, 1, 'JFK', 1),  
('CX002', 1, 1, 'YYZ', 3);  

-- INSERTS PARA TABLA document_type
INSERT INTO document_type (name) VALUES
('Passport'),
('Driver License'),
('ID Card');

-- INSERTS PARA TABLA customer
INSERT INTO customer (id, name, age, id_document) VALUES
('CUST001', 'John Doe', 30, 1),   
('CUST002', 'Jane Smith', 25, 2); 

-- INSERTS PARA TABLA tripulation_role
INSERT INTO tripulation_role (name) VALUES
('Pilot'),
('Co-pilot'),
('Flight Attendant'),
('Technical Support');

-- INSERTS PARA TABLA employee
INSERT INTO employee (id, name, id_rol, ingress_date, id_airline, id_airport) VALUES
('EMP001', 'Michael Johnson', 1, '2020-03-15', 1, 'JFK'), 
('EMP004', 'Sophia Garcia', 2, '2020-06-05', 2, 'JFK'),
('EMP002', 'Emily White', 3, '2021-01-10', 2, 'YYZ'),
('EMP003', 'David Lee', 4, '2019-11-20', 3, 'YYZ');
    

-- INSERTS PARA TABLA flight_fare
INSERT INTO flight_fare (description, detail, value) VALUES
('Economy', 'Standard seating', 150.00),
('Business', 'Priority seating and services', 350.00);

-- INSERTS PARA TABLA gate
INSERT INTO gate (gatenumber, id_airport) VALUES
('A1', 'JFK'),
('B2', 'YYZ'),
('C3', 'MEX');

-- INSERTS PARA TABLA revision
INSERT INTO revision (revision_date, id_plane) VALUES
('2024-06-20', 1),
('2024-06-25', 2);

-- INSERTS PARA TABLA rev_employee
INSERT INTO rev_employee (id_employee, id_revision, description) VALUES
('EMP001', 1, 'Routine check completed'),
('EMP002', 2, 'Minor repairs conducted');

-- INSERTS PARA TABLA trip_booking
INSERT INTO trip_booking (date, id_trip) VALUES
('2024-06-28', 1),
('2024-07-05', 2);

-- INSERTS PARA TABLA trip_condition
INSERT INTO trip_condition (name) VALUES
('Reservado'),
('Cancelado');

-- INSERTS PARA TABLA trip_booking_detail
INSERT INTO trip_booking_detail (id_trip_booking, id_customer, id_fare, id_trip_condition) VALUES
(1, 'CUST001', 1, 1),
(2, 'CUST002', 2, 2);

-- INSERTS PARA TABLA trip_crew
INSERT INTO trip_crew (id_employee, id_connection) VALUES
('EMP001', 1),
('EMP002', 2);

-- INSERTS PARA TABLA passenger
INSERT INTO passenger (nif, name, age, seat, id_document_type, id_trip_booking_detail) VALUES
('123456789A', 'Alice Brown', 21, '001', 1, 1),  
('987654321B', 'Bob Green', 17, '002', 2, 2);    

-- INSERTS PARA TABLA pay_type
INSERT INTO pay_type (name) VALUES
('Credit Card'),
('Debit Card'),
('Cash');

-- INSERTS PARA TABLA payment
INSERT INTO payment (pay_date, id_pay_type, total_pay, id_customer) VALUES
('2024-06-30', 1, 150.00, 'CUST001'),  
('2024-07-05', 2, 420.00, 'CUST002');  

```

