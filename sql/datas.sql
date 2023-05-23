INSERT INTO restaurant(description, nom_restaurant, max_convives_autorises) VALUES
('Restaurant gastronomique abordable pour toutes et tous', 'Le Quai Antique Chamberry', 20);

INSERT INTO horaire(fermeture_dejeuner, fermeture_diner, jour_semaine, ouverture_dejeuner, ouverture_diner, ouvert_dejeuner, ouvert_diner, restaurant_id) VALUES
('14:00:00', '22:00:00', 'LUNDI', '11:30:00', '19:00:00', true, true, 1),
('14:00:00', '22:00:00', 'MARDI', '11:30:00', '19:00:00', true, true, 1),
('14:00:00', '22:00:00', 'MERCREDI', '11:30:00', '19:00:00', true, true, 1),
('14:00:00', '22:00:00', 'JEUDI', '11:30:00', '19:00:00', true, true, 1),
('14:00:00', '23:00:00', 'VENDREDI', '11:30:00', '19:00:00', true, true, 1),
('14:00:00', '23:00:00', 'SAMEDI', '11:30:00', '19:00:00', true, true, 1),
('15:00:00', '23:00:00', 'DIMANCHE', '12:00:00', '19:00:00', true, false, 1);

INSERT INTO place(nb_places, restaurant_id) VALUES
(8,1),
(6,1),
(2,1),
(4,1),
(8,1),
(2,1),
(6,1),
(12,1),
(2,1);

INSERT INTO utilisateur(email, is_admin, mdp, nb_convives, nom, prenom) VALUES
('vincent@vincent.fr', false, '$2a$10$41Ekuu/63Kwt.jWCMD2OY.FL0/kHYx3.wA4t2P7vgXW/dBcaNIf1y', 0, 'Vincent', 'Vincent'),
('abelcoli@outlook.fr', true, '$2a$10$41Ekuu/63Kwt.jWCMD2OY.FL0/kHYx3.wA4t2P7vgXW/dBcaNIf1y', 0, 'Abel', 'Abel'),
('vincent@antoine.fr', false, '$2a$10$41Ekuu/63Kwt.jWCMD2OY.FL0/kHYx3.wA4t2P7vgXW/dBcaNIf1y', 0, 'Toine', 'Vincent');

INSERT INTO allergene(nom_allergene) VALUES
                                         ('Gluten'),
                                         ('Crustacés'),
                                         ('Oeufs'),
                                         ('Poissons'),
                                         ('Arachides'),
                                         ('Soja'),
                                         ('Lait'),
                                         ('Fruits à coque'),
                                         ('Moutarde'),
                                         ('Céleri'),
                                         ('Graines de sésame'),
                                         ('Anhydrides sulfureux'),
                                         ('Lupin'),
                                         ('Mollusques');


INSERT INTO allergie(utilisateur_id, allergene_id) VALUES
(1,1),
(1,2),
(2,3),
(2,4),
(3,5),
(3,6);

INSERT INTO menu(description, nom_menu) VALUES
('bon menu', 'menu1'),
('bon menu', 'menu2'),
('bon menu', 'menu3'),
('bon menu', 'menu4');

INSERT INTO formule(description, nom_formule, prix, menu_id) VALUES
('vroom', 'formule1', 10, 1),
('une bonne formule', 'formule2', 10, 1),
('une bonne formule', 'formule3', 10, 1),
('une bonne formule', 'formule4', 10, 2),
('une bonne formule', 'formule5', 10, 2),
('une bonne formule', 'formule6', 10, 3),
('une bonne formule', 'formule7', 10, 3),
('une bonne formule', 'formule8', 10, 4),
('une bonne formule', 'formule9', 10, 4);

INSERT INTO plat(description, nom_plat, type_plat, prix, photo) VALUES
('un bon petit plat', 'plat1', 'ENTREE', 5, 'plat1.png'),
('un bon petit plat', 'plat2', 'ENTREE', 5, 'plat2.png'),
('un bon petit plat', 'plat3', 'ENTREE', 5, 'plat3.png'),
('un bon petit plat', 'plat4', 'PRINCIPAL', 5, 'plat4.png'),
('un bon petit plat', 'plat5', 'PRINCIPAL', 5, 'plat5.png'),
('un bon petit plat', 'plat6', 'PRINCIPAL', 5, 'plat5.png'),
('un bon petit plat', 'plat7', 'DESSERT', 5, 'plat6.png'),
('un bon petit plat', 'plat8', 'DESSERT', 5, 'plat7.png'),
('un bon petit plat', 'plat9', 'DESSERT', 5, 'plat8.png');

INSERT INTO composition_formule(plat_id, formule_id) VALUES
                                                         (1, 1),
                                                         (4, 1),
                                                         (7, 1),
                                                         (2, 2),
                                                         (5, 2),
                                                         (8, 2),
                                                         (3, 3),
                                                         (6, 3),
                                                         (9, 3),
                                                         (4, 4),
                                                         (5, 4),
                                                         (6, 4),
                                                         (7, 5),
                                                         (8, 5),
                                                         (9, 5),
                                                         (1, 6),
                                                         (7, 6),
                                                         (9, 6),
                                                         (2, 7),
                                                         (3, 7),
                                                         (8, 7),
                                                         (1, 8),
                                                         (2, 8),
                                                         (3, 8),
                                                         (4, 9),
                                                         (5, 9),
                                                         (6, 9);


INSERT INTO plat_allergene(plat_id, allergene_id) VALUES
(1,1),
(2,2),
(3,3),
(4,4),
(5,5),
(6,6),
(7,7),
(8,8),
(9,9);