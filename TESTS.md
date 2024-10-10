### 1. Tests cURL pour UserController

#### a) Obtenir tous les utilisateurs

```bash
curl -o - -i -X GET http://localhost:8082/api/users
```

#### b) Obtenir un utilisateur spécifique par ID

```bash
curl -o - -i -X GET http://localhost:8082/api/users/{id}
```

Remplacez `{id}` par l'ID de l'utilisateur (par exemple, `1`).

#### c) Supprimer un utilisateur spécifique par ID (Requiert un rôle ADMIN)

```bash
curl -o - -i -X DELETE http://localhost:8082/api/users/{id} \
-H "Authorization: Bearer $TOKEN"
```

Remplacez `{id}` par l'ID de l'utilisateur.

#### d) Ajouter un nouvel utilisateur (Requiert un rôle ADMIN)

```bash
curl -o - -i -X POST http://localhost:8082/api/users \
-H "Content-Type: application/json" \
-H "Authorization: Bearer $TOKEN" \
-d '{
    "username": "nouvelutilisateur",
    "sessionId": 123
}'
```

Remplacez `$TOKEN` par votre token JWT valide et personnalisez le `username` et le `sessionId` dans le payload si nécessaire.

#### e) Mettre à jour un utilisateur existant (Requiert un rôle ADMIN)

```bash
curl -o - -i -X PUT http://localhost:8082/api/users/{id} \
-H "Content-Type: application/json" \
-H "Authorization: Bearer $TOKEN" \
-d '{
    "username": "utilisateurmodifié",
    "sessionId": 123
}'
```

Remplacez `{id}` par l'ID de l'utilisateur, `$TOKEN` par votre token JWT valide, et modifiez les détails de l'utilisateur selon vos besoins.

---

### 2. Tests cURL pour SessionController

#### a) Obtenir toutes les sessions

```bash
curl -o - -i -X GET http://localhost:8082/api/sessions
```

#### b) Créer une session "dummy"

Pour générer une session fictive :

```bash
curl -o - -i -X POST "http://localhost:8082/api/sessions/dummy" \
-H "Content-Type: application/json"
```

#### c) Obtenir une session spécifique par ID

```bash
curl -o - -i -X GET http://localhost:8082/api/sessions/{id}
```

Remplacez `{id}` par l'ID de la session (par exemple, `1`).

#### d) Supprimer une session par ID (Requiert un rôle ADMIN)

```bash
curl -o - -i -X DELETE http://localhost:8082/api/sessions/{id} \
-H "Authorization: Bearer $TOKEN"
```

Remplacez `{id}` par l'ID de la session.

#### e) Obtenir toutes les réponses d'utilisateurs associées à une session (Requiert un rôle ADMIN)

```bash
curl -o - -i -X GET http://localhost:8082/api/sessions/{id}/useranswers \
-H "Authorization: Bearer $TOKEN"
```

Remplacez `{id}` par l'ID de la session.

#### f) Obtenir tous les utilisateurs classés par score pour une session (Requiert un rôle ADMIN)

```bash
curl -o - -i -X GET http://localhost:8082/api/sessions/{id}/scores \
-H "Authorization: Bearer $TOKEN"
```

Remplacez `{id}` par l'ID de la session.

---

### Remarques :
- Vérifiez que `$TOKEN` n'est pas null : Avant de faire des requêtes nécessitant le rôle **ADMIN**, authentifiez-vous et obtenez un token **JWT** valide.
- Testez avec des IDs d'utilisateur ou de session valides.
