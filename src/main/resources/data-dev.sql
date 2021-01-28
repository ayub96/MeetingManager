INSERT INTO customer(firstname, surname, email) VALUES
('John', 'Smith', 'john@gmail.com'),
('James', 'Jones', 'james@gmail.com'),
('Rob', 'Jackson', 'mike@gmail.com'),
('Paul', 'Rogers', 'paul@gmail.com'),
('Tim', 'Jones', 'tim@gmail.com');

INSERT INTO appointment(appointment_type, date_placed, customer_id) VALUES 
('Haircut', '2021-01-21 09:00:00', 2),
('Beard trim', NOW(), 1),
('Clean shave', NOW(), 1);