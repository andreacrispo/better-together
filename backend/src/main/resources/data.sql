DELETE FROM service;
insert into service(service_id,  name) VALUES
(1, 'Netflix'),
(2, 'Spotify'),
(3, 'Amazon')
;

DELETE FROM participant;
insert into participant(participant_id,  name, email) VALUES
(1, 'Andrea', 'mail'),
(2, 'Luca', 'mail'),
(3, 'Maroo', 'mail')
;

DELETE FROM service_participant;
insert into service_participant(id, service_id, participant_id, price_paid, has_paid, payment_date) VALUES
(1, 1, 1, 2, true, '2018-10-6'),
(2, 1, 2, 2, true, '2018-10-6'),
(3, 1, 3, 2, true, '2018-10-6'),
(4, 2, 3, 2, true, '2018-09-6')
;