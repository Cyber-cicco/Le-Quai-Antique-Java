CREATE TABLE utilisateur(
                            id INTEGER PRIMARY KEY NOT NULL AUTO_INCREMENT,
                            email VARCHAR(255),
                            is_admin BOOLEAN,
                            mdp VARCHAR(255),
                            nb_convives INTEGER,
                            nom VARCHAR(255) NOT NULL,
                            prenom VARCHAR(255) NOT NULL
);

CREATE TABLE plat (
                      id INTEGER NOT NULL PRIMARY KEY AUTO_INCREMENT,
                      description TEXT,
                      nom_plat VARCHAR(255),
                      photo VARCHAR(255),
                      prix NUMERIC(10,2),
                      type_plat VARCHAR(255) NOT NULL
);

CREATE TABLE restaurant(
                           id INTEGER NOT NULL PRIMARY KEY  AUTO_INCREMENT,
                           description TEXT,
                           max_convives_autorises INTEGER,
                           nom_restaurant VARCHAR(255)
);

CREATE TABLE menu(
    id INTEGER NOT NULL PRIMARY KEY AUTO_INCREMENT,
    description TEXT,
    nom_menu VARCHAR(255)
);

CREATE TABLE allergene(
    id INTEGER NOT NULL PRIMARY KEY AUTO_INCREMENT,
    nom_allergene VARCHAR(255)
);

CREATE TABLE allergie(
    utilisateur_id INTEGER NOT NULL,
    allergene_id INTEGER NOT NULL,
    FOREIGN KEY (utilisateur_id) REFERENCES utilisateur(id),
    FOREIGN KEY (allergene_id) REFERENCES allergene(id)
);

CREATE TABLE formule(
                        id INTEGER NOT NULL PRIMARY KEY AUTO_INCREMENT ,
                        description TEXT,
                        nom_formule VARCHAR(255) NOT NULL ,
                        prix NUMERIC(10,2) NOT NULL,
                        menu_id INTEGER NOT NULL,
                        FOREIGN KEY (menu_id) REFERENCES menu(id)
);

CREATE TABLE composition_formule(
    plat_id INTEGER NOT NULL,
    formule_id INTEGER NOT NULL,
    FOREIGN KEY (formule_id) REFERENCES formule(id),
    FOREIGN KEY (plat_id) REFERENCES plat(id)
);





CREATE TABLE place(
    id INTEGER PRIMARY KEY NOT NULL AUTO_INCREMENT,
    nb_places INTEGER,
    restaurant_id INTEGER NOT NULL,
    FOREIGN KEY (restaurant_id) REFERENCES restaurant(id)
);

CREATE TABLE reservation(
    id INTEGER NOT NULL PRIMARY KEY AUTO_INCREMENT,
    date_reservation DATETIME,
    nom VARCHAR(255),
    prenom VARCHAR(255),
    table_id INTEGER NOT NULL,
    utilisateur_id INTEGER NOT NULL,
    FOREIGN KEY (table_id) REFERENCES place(id),
    FOREIGN KEY (utilisateur_id) REFERENCES utilisateur(id)
);

CREATE TABLE reservation_allergie(
    reservation_id INTEGER NOT NULL,
    allergene_id INTEGER NOT NULL,
    FOREIGN KEY (reservation_id) REFERENCES reservation(id),
    FOREIGN KEY (allergene_id) REFERENCES allergene(id)
);


CREATE TABLE plat_allergene(
    plat_id INTEGER NOT NULL,
    allergene_id INTEGER NOT NULL,
    FOREIGN KEY (plat_id) REFERENCES plat(id),
    FOREIGN KEY (allergene_id) REFERENCES allergene(id)
);


CREATE TABLE horaire(
                        id INTEGER NOT NULL PRIMARY KEY AUTO_INCREMENT,
                        fermeture_dejeuner TIME,
                        fermeture_diner TIME,
                        ouverture_dejeuner TIME,
                        ouverture_diner TIME,
                        ouvert_dejeuner BOOLEAN,
                        ouvert_diner BOOLEAN,
                        jour_semaine VARCHAR(255),
                        restaurant_id INTEGER,
                        FOREIGN KEY (restaurant_id) REFERENCES restaurant(id)
);
