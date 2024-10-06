# TP : Application Spring-boot

## Description

Ce projet est une application Java utilisant JPA (Java Persistence API) pour la gestion des données et JAX-RS (Java API for RESTful Web Services) pour la création d'API REST.

### Fonctionnalités principales :
- Gestion des entités avec JPA
- Création d'API RESTful avec JAX-RS

## Prérequis

Avant de commencer, assurez-vous d'avoir installé les éléments suivants sur votre machine :

- **JDK 17** : [Installer le JDK](https://www.oracle.com/java/technologies/javase-downloads.html)
- **Maven** : [Télécharger Maven](https://maven.apache.org/download.cgi)
- **Docker** : [Documentation](https://docs.docker.com/)

### 1. Cloner le dépôt

```bash
git clone https://github.com/votre-utilisateur/votre-projet.git
cd votre-projet
```
### 2. Installation des dépendances

Utilisez Maven pour construire le projet et télécharger les dépendances.

```bash
mvn clean install
```
* Launch the application (com.KahootSpringApplication)
* Launch the Keycloack docker server :
    ```shell
    docker run -p 8080:8080 -e KEYCLOAK_ADMIN=admin -e KEYCLOAK_ADMIN_PASSWORD=admin quay.io/keycloak/keycloak:latest start-dev
    ```
* Connect to keycloack [admin console](http://localhost:8080/admin/master/console/) with credentials _admin:admin_
* Import Realm settings from this repository : [realm-export.json](keycloack/realm-export.json) into _Realms settings > Action > Partial import_



## Lancer l'application

### 1. Lancer le serveur de BDD mysql

En ligne de commande :
* Sur linux :
```bash
./run-hsqldb-server.sh
```
* Sur windows :
```bash
./run-hsqldb-server.bat
```

#### 1.1 Visualiser le contenu de la BDD
* Sur linux :
```bash
./show-hsqldb.sh
```
* Sur windows :
```bash
./show-hsqldb.bat
```
* Directement dans intellij dans l'onglet Database, nécessite de compléter les informations suivantes :

![img.png](img.png)

### 2. Via Maven (Ou directement via l'interface intellij)

Exécuter [com.KahootSpringApplication](src/main/java/com/KahootSpringApplication.java)

### 3. Accès à l'API

Une fois l'application démarrée, vous pouvez accéder à votre API via votre navigateur [localhost:8082](http://localhost:8082) ou un outil comme [Postman](https://www.postman.com/). Par exemple :

* [localhost:8082/sessions/](localhost:8082/sessions/) : Permet de visualiser la liste des sessions en BDD.

### 4. Accès au swagger API

Vous pouvez également accéder au swagger API (format openapi) : [localhost:8082/api/swagger](http://localhost:8082/api/swagger) ou un outil comme [Postman](https://www.postman.com/). Par exemple :

## Fonctionnalités API

### Le Swagger
[Link to the swagger](http://localhost:8082/api/swagger)


## Structure du projet
```bash
src/main/java/
├── dao
│   ├── GenericJpaDaoImpl.java
│   ├── IGenericDao.java
│   ├── KahootDAO.java
│   ├── OrganizerDAO.java
│   ├── QCMAnswerDAO.java
│   ├── QuestionDAO.java
│   ├── SessionDAO.java
│   ├── UserAnswerDAO.java
│   └── UserDAO.java
├── entity
│   ├── Kahoot.java
│   ├── Organizer.java
│   ├── QCMAnswer.java
│   ├── QCMQuestion.java
│   ├── QCMUserAnswer.java
│   ├── Question.java
│   ├── Session.java
│   ├── ShortAnswerQuestion.java
│   ├── ShortUserAnswer.java
│   ├── UserAnswer.java
│   └── User.java
├── dto
│   ├── KahootDTO.java
│   ├── OrganizerDTO.java
│   ├── SessionDTO.java
│   ├── SessionMapper.java
│   └── UserDTO.java
├── jpa
│   ├── EntityManagerHelper.java
│   └── JpaTest.java
├── rest
│   ├── KahootResource.java
│   ├── SessionResource.java
│   └── SwaggerResource.java
└── services
└── SessionService.java
```

## Auteur

Pierre TRETON