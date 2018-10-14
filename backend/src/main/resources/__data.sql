DELETE FROM service;
insert into service(service_id,  name, monthly_price) VALUES
(1, 'Netflix', 14.5),
(2, 'Spotify', 15),
(3, 'Amazon', 13.3)
;

DELETE FROM participant;
insert into participant(participant_id,  name, email) VALUES
(1, 'Andrea', 'mail'),
(2, 'Luca', 'mail'),
(3, 'Maroo', 'mail')
;

DELETE FROM service_participant;
insert into service_participant(id, service_id, participant_id, price_paid, has_paid, payment_date) VALUES
(1, 1, 1, 2.5, true, '2018-10-1'),
(2, 1, 2, 2.5, true, '2018-10-1'),
(3, 1, 3, 2.5, false, '2018-10-1'),
(4, 2, 3, 3.5, true, '2018-09-1')
;