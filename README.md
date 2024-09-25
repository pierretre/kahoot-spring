# SPRING BOOT PROJECT

## How to retrieve project
## How to run
* Launch the application (KahootSpringApplication)
* Launch the Keycloack docker server : 
    ```shell
    docker run -p 8080:8080 -e KEYCLOAK_ADMIN=admin -e KEYCLOAK_ADMIN_PASSWORD=admin quay.io/keycloak/keycloak:latest start-dev
    ```
* Connect to keycloack [admin console](http://localhost:8080/admin/master/console/) with credentials _admin:admin_
* Import Realm settings from this repository : [realm-export.json](keycloack/realm-export.json) into _Realms settings > Action > Partial import_
## How to test
### Swagger
[Link to the swagger](http://localhost:8082/api/swagger)

