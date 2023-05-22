# Le-Quai-Antique-Java

Projet de site avec Java en Backend et Angular en front servant à démontrer mes connaissances dans ces technologies

## Installation

--- 

### Prérequis

* Java 17 ou supérieur
* node js
* npm
* http-server
* mysql / mariadb

### Déploiement en local
Assurez vous d'avoir installé sur votre machine node JS et java 17 (minimum). Si vous n'avez pas déjà installé http server en
local, effectuez la commande suivante dans un terminal bash
```bash
npm i http-server -g
```

Ensuite, téléchargez la release sur Github. Utilisez votre gestionnaire d'archive pour dézipper la release, et ouvrez
votre dossier dans un terminal. Ensuite, tappez les commandes suivantes:
```bash
http-server dist/quai-antique-front/ -p 4200
```
Il est important de bien préciser le port 4200, sans quoi le backend refusera les connexions à l'API.
  
Ensuite, connectez-vous à votre base de données Mariadb / MySQL, et exécutez les commandes suivantes:

```sql
CREATE DATABASE quai_antique;
CREATE USER 'user2'@'localhost' IDENTIFIED BY 'root';
GRANT ALL PRIVILEGES ON quai_antique.* TO 'user2'@'localhost';
```

Copiez les fichiers présents dans le dossier SQL de ce dépôt sur votre PC. Ouvrez un terminal, et exécutez les commandes suivantes:


```bash
sudo mysql -u root -p quai_antique < create-database.sql
sudo mysql -u root -p quai_antique < datas.sql
```

  Ensuite, dans un autre terminal dans le même dossier, tappez la commande suivante:
```bash
java -jar dev-0.0.1-SNAPSHOT-spring-boot.jar 
```

Rendez-vous à l'adresse http://localhost:4200/ pour visiter le site

  

### Créer un utilisateur avec des droits d'administrateur:
---
Créez un compte via l'interface de création de compte de l'application.
Connectez-vous à votre base de données, puis changez la valeur de "is_admin" à true dans la table utilisateur grâce à la requête utilisateur suivante:


```sql
UPDATE utilisateur SET is_admin = true WHERE utilisateur.id = 4;
```

Il n'y a pas d'autre moyen de créer un utilisateur disposant de droits d'administration.

### Accéder au panel d'administration:
---
Pour des soucis de sécurité, le panel d'administration est accessible à l'URL suivante:
  
  http://localhost:4200/9fc561fbf4c7028b2c2ab3662b2602e78658eecba8a6bfacf50c3de57f66202a

  Vous la trouverez dans app-routing.module.ts du projet Angular.