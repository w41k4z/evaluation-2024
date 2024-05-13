-- AUTH --
INSERT INTO groups VALUES
(1, 'BTP'),
(2, 'CLIENT');

INSERT INTO users VALUES
(1, 'admin@gmail.com', '$2a$10$e1ox32O1ARezRy6lXlMQJOZHqKQ77g/lX7mT940HKNO6DUMkdGqGC', 1),
(2, '032 55 063 38', '$2a$10$e1ox32O1ARezRy6lXlMQJOZHqKQ77g/lX7mT940HKNO6DUMkdGqGC', 2);

-- TABLE
INSERT INTO units VALUES
(1, 'm2'),
(2, 'm3'),
(3, 'fft');

INSERT INTO house_types VALUES
(1, 'Villa', 15, 1000000); 

INSERT INTO house_type_details VALUES
(1, 1, 1, 'Salon'),
(2, 1, 1, 'Cuisine'),
(3, 1, 2, 'Salles de bain'),
(4, 1, 3, 'Chambres');

INSERT INTO finition_types VALUES
(1, 'Standard', 0),
(2, 'Gold', 5),
(3, 'Premium', 15),
(4, 'VIP', 30);


INSERT INTO works VALUES
(1, 'Travaux preparatoire'),
(2, 'Travaux de terrassement'),
(3, 'Travaux en infrastructure');

INSERT INTO work_details VALUES
(1, 1, 'mur de soutenant et demi Cloture ht 1m', 2, 190000),
(2, 2, 'Decapage des terrains meubles', 1, 3072.87),
(3, 2, 'Dressage du plateforme', 1, 3736.26),
(4, 2, 'Fouille d ouvrage terrain ferme', 2, 9390.93),
(5, 2, 'Remblai d ouvrage', 2, 37563.26),
(6, 2, 'Travaux d implantation', 3, 152656),
(7, 3, 'Maçonnerie de moellons, ep=35cm', 2, 172114.40),
(8, 3, 'beton armee dosee à 350kg/m3: - semelles/isolee', 2, 573215.80),
(9, 3, 'beton armee dosee à 350kg/m3: - amorces poteaux', 2, 573215.80),
(10, 3, 'beton armee dosee à 350kg/m3: - chainage base de 20x20', 2, 573215.80),
(11, 3, 'remblai technique', 2, 37563.26),
(12, 3, 'herrissonage ep=10', 2, 73245.40),
(13, 3, 'beton ordinaire dosee à 300kg/m3', 2, 487815.80),
(14, 3, 'chape de 2cm', 2, 487815.80);

INSERT INTO house_construction_details VALUES
(DEFAULT, 1, 1, 26.98),
(DEFAULT, 1, 2, 101.36),
(DEFAULT, 1, 3, 101.36),
(DEFAULT, 1, 4, 24.44),
(DEFAULT, 1, 5, 15.59),
(DEFAULT, 1, 6, 1),
(DEFAULT, 1, 7, 9.62),
(DEFAULT, 1, 8, 0.53),
(DEFAULT, 1, 9, 0.56),
(DEFAULT, 1, 10, 2.44),
(DEFAULT, 1, 11, 15.59),
(DEFAULT, 1, 12, 7.80),
(DEFAULT, 1, 13, 5.46),
(DEFAULT, 1, 14, 77.97);