# voltaired
## Mettre a jour les hosts
``` 
127.0.0.1 dps.epita.local
```

## Lancer les containers et les APIs

D'abord, on doit démarrer nos containers via le docker-compose :
```shell
docker compose up
```

Avec ce projet, vous pourrez tester :
- L'authentification
- Le web server
- Les API publique et privée
- Le logger redis

## API Publique
Accessible depuis un format d'URL: `https://dps.epita.local/(circles|letters|writers).*`, elle sert d'endpoint pour les utilisateurs non-authentifiés.  
Elle ne renvoie que des informations très basiques.  
Ces spécifications sont définies dans le fichier `python/swagger.yml`.

## API Restreinte
Accessible depuis un format d'URL: `https://dps.epita.local/api/*`, elle sert d'endpoint pour les utilisateurs authentifiés.  
Elle renvoie des informations plus détaillées et offre des possibilités de gestion avancée.  
Elle possède une spec OpenAPI: https://dps.epita.local/api/openapi ainsi qu'un Swagger pour la visualiser : https://dps.epita.local/api/swagger.



## Redis
La partie Redis manquant d'instructions, nous avons mis en place un plugin logger directement sur une fonction, le POST de Letters (voir swagger).
Il permet de logger l'utilisation de cette methode.

A chaque fois qu'une lettre est postée, on envoie un message sous le format JSON suivant dans le channel `Letter`
```json
{
  "id": 0,
  "date": "2022-03-10T12:15:50-04:00",
  "subject": "string",
  "content": "string",
  "circlesIds": [
    0
  ],
  "writerId": 0
}
```

Pour pouvoir vérifier son fonctionnement, il suffit de se connecter au cli docker du Redis container avec la commande

```
docker exec -it voltaired-cache-1 /bin/sh
```

Quand vous serez connecté au Docker, vous pouvez taper ces commandes pour susbscribe au logger redis

```
redis-cli
SUBSCRIBE Letter
```

et vous pouvez voir le resultat du logger dans la console apres POST Letter.
