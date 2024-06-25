# Documentation de l'Application Spring Boot: immataeronef

### Les membres du Groupe :
- GOLAM Tamim
- Adewoye Shakir Ramdan OYEOSSI
- Londou Jacques BOYODI
- Chaimaa ARMILAT
- Ludovic MOYO KAMKUIMO

## Contexte du projet
L’annuaire des immatriculations des aéronefs en France est un projet visant à créer une base de données exhaustive et accessible des immatriculations des aéronefs en France.

En France, tous les aéronefs doivent être immatriculés auprès des autorités compétentes, conformément à la réglementation de l'aviation civile. Cette immatriculation est essentielle pour :

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
  La méthode `getNbAppareil` a été ajoutée pour calculer le nombre d'aéronefs par constructeur. Elle itère sur les données CSV, compte les occurrences par constructeur et retourne une liste d'objets `NbAppareilDTO`.

  Le chargement du fichier CSV est maintenant effectué directement dans le constructeur, simplifiant le code.

- **Contrôleur NbAppareilController:**
  Un nouveau contrôleur a été créé pour gérer l'endpoint `/constructeurs`. Il utilise le service `ImmatService` pour récupérer les données et les renvoyer sous forme de réponse JSON.

- **DTO ConstructorDTO:**
  Un nouveau Data Transfer Object (DTO) a été créé pour représenter le résultat du comptage des aéronefs par constructeur. Il contient les champs `constructeur` et `nbAppareil`.

## Structure du projet
- **Main.java:** Point d'entrée de l'application Spring Boot.
- **ImmatCSVReader.java:** Responsable de la lecture et du parsing du fichier CSV.
- **ImmatService.java:** Fournit la logique métier pour accéder et traiter les données des aéronefs.
- **ImmatController.java:** Gère l'endpoint pour récupérer les informations d'un aéronef par son immatriculation.
- **ConstructorController.java:** Gère l'endpoint pour le comptage des aéronefs par constructeur.
- **AeronefDTO.java:** DTO pour représenter les données d'un aéronef.
- **ConstructorDTO.java:** DTO pour représenter le résultat du comptage.
- **AppConf.java:** Classe de configuration pour lire le nom du fichier CSV depuis `application.properties`.
- **Config.java:** Classe de configuration pour créer le bean `ImmatCSVReader`.
- **application.properties:** Fichier de configuration contenant le chemin du fichier CSV.

## Les étapes pour faire fonctionner et tester l'application
1. Ouvrir son invite de commande (terminal) et exécuter la commande :
   ```sh
   git clone https://github.com/Tamim94/immataeronef.git
