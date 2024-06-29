
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


