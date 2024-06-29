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
