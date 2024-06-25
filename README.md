# Documentation de l'Application Spring Boot: immataeronef

### Les membres du Groupe :
- GOLAM Tamim
- Adewoye Shakir Ramdan OYEOSSI
- Londou Jacques BOYODI
- Chaimaa ARMILAT
- Ludovic MOYO KAMKUIMO

## Contexte du projet
L’annuaire des immatriculations des aéronefs en France est un projet visant à créer une base de données exhaustive et accessible des immatriculations des aéronefs.

En France, tous les aéronefs doivent être immatriculés auprès des autorités compétentes, conformément à la réglementation de l'aviation civile. Cette immatriculation est essentielle pour:

- Assurer la traçabilité des aéronefs
- Garantir la responsabilité des propriétaires et opérateurs
- Maintenir des standards de sécurité aérienne élevés

## Objectif de l'application
Cette application fournit une API REST pour accéder aux informations sur les aéronefs (avions et hélicoptères) immatriculés en France. Les données sont extraites d'un fichier CSV et exposées via des endpoints REST.

## Fonctionnalités
### 1. Récupération des informations d'un aéronef par son immatriculation:
**Endpoint:** `/aeronef/{immat}` (GET)

**Paramètre:** `{immat}` (immatriculation de l'aéronef)

**Réponse:**

- **Succès (200 OK):** Un objet JSON contenant les détails de l'aéronef (immatriculation, constructeur, modèle, aérodrome d'attache).
- **Non trouvé (204 No Content):** Si l'aéronef n'est pas trouvé dans le fichier CSV.

### 2. Comptage du nombre d'aéronefs par constructeur:
**Endpoint:** `/constructeurs` (GET)

**Réponse:**

- **Succès (200 OK):** Une liste d'objets JSON, chaque objet contenant le nom d'un constructeur et le nombre d'aéronefs correspondant.

## Modifications et Améliorations
- **Lecture du fichier CSV:**
  La classe `ImmatCSVReader` a été modifiée pour retourner directement le dictionnaire `entries` contenant les données des aéronefs après le chargement du fichier CSV. Cela améliore la modularité et la réutilisation du code.

- **Service ImmatService:**
  La méthode `getAeronefCountByConstructor` a été ajoutée pour calculer le nombre d'aéronefs par constructeur. Elle itère sur les données CSV, compte les occurrences par constructeur et retourne une liste d'objets `ConstructorDTO`.

  Le chargement du fichier CSV est maintenant effectué directement dans le constructeur, simplifiant le code.

- **Contrôleur ConstructorController:**
  Un nouveau contrôleur a été créé pour gérer l'endpoint `/constructeurs`. Il utilise le service `ImmatService` pour récupérer les données et les renvoyer sous forme de réponse JSON.

- **DTO ConstructorDTO:**
  Un nouveau Data Transfer Object (DTO) a été créé pour représenter le résultat du comptage des aéronefs par constructeur. Il contient les champs `name` pour le nom du constructeur et `count_aeronef` pour le nombre d'aeronefs du constructeur.

## Structure du projet
- **Main.java:** Point d'entrée de l'application Spring Boot.
- **ImmatCSVReader.java:** Responsable de la lecture et du parsing du fichier CSV.
- **ImmatService.java:** Fournit la logique métier pour accéder et traiter les données des aéronefs.
- **ImmatController.java:** Gère l'endpoint pour récupérer les informations d'un aéronef par son immatriculation.
- **ConstructorController.java:** Gère l'endpoint pour le comptage des aéronefs par constructeur.
- **AeronefDTO.java:** Record pour représenter les données d'un aéronef.
- **ConstructorDTO.java:** Record pour représenter le résultat du comptage.
- **AppConf.java:** Classe de configuration pour lire le nom du fichier CSV depuis `application.properties`.
- **Config.java:** Classe de configuration pour créer le bean `ImmatCSVReader`.
- **application.properties:** Fichier de configuration contenant le chemin du fichier CSV.

## Les étapes pour faire fonctionner et tester l'application
1. Ouvrir son invite de commande (terminal) et exécuter la commande :
   ```sh
   git clone https://github.com/Tamim94/immataeronef.git


2. Ouvrir le projet dans son IDE et lancer l'application.
3. Ouvrir le navigateur pour tester les APIs suivantes:
-  /constructeurs, qui renvoie une liste avec le nombre d’appareils par constructeurs, seulement s’il y a plus d’un appareil par constructeur.
    ![image](https://github.com/Tamim94/immataeronef/assets/88946894/91cc802e-fe3a-411d-865f-4bdd135cbf56)
-  /aeronef/{immat}, qui renvoie l'information sur l'aéronef en fonction du numéro matricule ({immat}). Remplacer {immat} par un numéro matricule, par exemple "F-GHDD".
  ![image (4)](https://github.com/Tamim94/immataeronef/assets/88946894/d9704368-ec24-4fb8-a687-972325acaadc)
3. Exécuter les tests dans le repertoire test à la racine du projet
    ![image (3)](https://github.com/Tamim94/immataeronef/assets/88946894/cf066dfe-172f-42e9-8797-7f16f3865d51)

