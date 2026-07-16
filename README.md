# Olympic Management System

Plateforme numérique de gestion des Jeux Olympiques développée dans le cadre de l'examen du module **Web Services** (Master 1, UVS/Université Numérique Cheikh Hamidou Kane), sous la supervision de Dr. Mouhamadou Lamine DIAKHAME.

## Contexte

Dans le cadre des JO de Dakar, le CIO souhaite mettre à disposition des données en temps réel pour deux types de clients :
- Des applications web et mobiles consommant une **API REST**
- Un système d'information historique utilisant des **Web Services SOAP**

## Technologies utilisées

| Composant | Technologie |
|---|---|
| Langage | Java 17 |
| Framework | Spring Boot 3.3.4 |
| API REST | Spring Web (Spring MVC) |
| Web Service SOAP | Spring-WS (contract-first, XSD → WSDL) |
| Persistance | Spring Data JPA / Hibernate |
| Base de données | MySQL (via XAMPP) |
| Documentation API | Swagger / OpenAPI (springdoc-openapi) |
| Génération de code SOAP | JAXB (plugin Maven jaxb2-maven-plugin) |
| Build | Maven |
| Réduction de code boilerplate | Lombok |
| Tests manuels | Postman (collection fournie) |

## Architecture du projet

Le projet suit une architecture en couches classique, avec une séparation nette entre les deux modes d'exposition (REST et SOAP), qui réutilisent la même couche métier :

```
com.cio.olympic
├── model/              Entités JPA (Athlete, Discipline, Epreuve, Resultat, Pays, Medaille)
├── repository/         Interfaces Spring Data JPA (CRUD + requêtes dérivées)
├── service/            Logique métier (attribution automatique des médailles,
│                       calcul du tableau des médailles, statistiques)
├── dto/                Objets de transfert pour les données calculées
│                       (tableau des médailles, statistiques du tableau de bord)
├── rest/
│   └── controller/     Contrôleurs REST (exposés sous /api/**)
├── soap/                Endpoints SOAP (contract-first)
├── config/              Configuration Spring-WS (WSDL, schémas XSD)
├── exception/           Gestion centralisée des erreurs (404, etc.)
└── OlympicManagementApplication.java
```

Les fichiers de contrat XSD (`athlete.xsd`, `resultat.xsd`) se trouvent dans `src/main/resources/xsd/`. Les classes Java correspondantes sont générées automatiquement au moment du build par le plugin `jaxb2-maven-plugin` (approche *contract-first*), garantissant que le contrat XML fait foi.

## Modèle de données

Cinq entités couvrent l'ensemble des fonctionnalités demandées :

- **Pays** : nom, code ISO
- **Discipline** : nom, description
- **Athlete** : identité, sexe, date de naissance, taille, poids, rattaché à un pays et une discipline
- **Epreuve** : nom, date, genre, rattachée à une discipline
- **Resultat** : position, performance, médaille (attribuée automatiquement), rattaché à une épreuve et un athlète

La médaille (`OR`, `ARGENT`, `BRONZE`, `AUCUNE`) est calculée automatiquement à partir de la position lors de l'enregistrement d'un résultat — aucune saisie manuelle n'est nécessaire.

## Fonctionnalités

### API REST (`/api/**`)

- **Athlètes** : CRUD complet, modification partielle (PATCH), recherche multicritère (nom/prénom), filtrage par discipline ou pays
- **Disciplines** : CRUD complet, consultation des athlètes d'une discipline
- **Épreuves** : CRUD complet, recherche par date et par discipline
- **Résultats** : enregistrement avec attribution automatique des médailles, consultation du podium
- **Tableau des médailles** : classement des nations (or → argent → bronze en cas d'égalité)
- **Tableau de bord** : statistiques générales, classement par points (or = 7, argent = 4, bronze = 1), médailles par pays

Documentation interactive disponible via Swagger UI : `/swagger-ui/index.html`

### Web Service SOAP (`/ws`)

Deux services distincts, chacun avec son propre contrat XSD et WSDL :

- **Service Athlètes** (`/ws/athletes.wsdl`) : consultation d'un athlète par id, liste complète des athlètes
- **Service Résultats** (`/ws/resultats.wsdl`) : consultation d'un résultat par id, résultats d'un athlète, tableau des médailles

## Installation et démarrage

### Prérequis
- Java 17
- MySQL (via XAMPP ou installation standalone)
- Maven (fourni via le wrapper `mvnw`)

### Configuration

Le fichier `src/main/resources/application.properties` configure la connexion à la base :

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/olympic_db?createDatabaseIfNotExist=true&useSSL=false&serverTimezone=UTC
spring.datasource.username=root
spring.datasource.password=
```

La base `olympic_db` et ses tables sont créées automatiquement au démarrage (`spring.jpa.hibernate.ddl-auto=update`).

### Lancement

```bash
./mvnw spring-boot:run
```

L'application démarre sur `http://localhost:8081`.

## Tests

Une collection Postman complète (`Olympic-Management-API.postman_collection.json`) est fournie, couvrant l'ensemble des endpoints REST et SOAP avec des exemples de requêtes prêts à l'emploi.

## Auteur

Zeyna — Master 1, UVS/Université Numérique Cheikh Hamidou Kane
