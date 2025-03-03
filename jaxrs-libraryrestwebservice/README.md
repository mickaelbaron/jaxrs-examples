# Projet JAXRS-LibraryRESTWebService

Un exemple qui montre comment utiliser la spécification JAX-RS et l'implémentation Jersey pour développer un service web REST avec le langage Java. À ce sujet, nous focalisons sur les types personnalisées, les objets `Response` et les bonnes pratiques pour écrire des tests d'intégration avec le module **test-framework** fourni par Jersey.

## Comment compiler

* À la racine du projet, exécuter la ligne de commande suivante pour compiler les classes et exécuter les tests d'intégration :

```bash
mvn clean package
```

## Comment exécuter

* Toujours depuis la racine du projet, exécuter la ligne de commande suivante pour exécuter le programme principal définie par la classe `LibraryRestWebServiceLauncher` permettant les déploiements des ressources `BookResource`, `BookResponseResource` et `BookContentResource`.

```bash
java -cp "target/classes:target/dependency/*" fr.mickaelbaron.libraryrestwebservice.LibraryRestWebServiceLauncher
```

La sortie console attendue :

```bash
mars 03, 2025 9:00:42 AM org.glassfish.grizzly.http.server.NetworkListener start
INFO: Started listener bound to [localhost:9992]
mars 03, 2025 9:00:42 AM org.glassfish.grizzly.http.server.HttpServer start
INFO: [HttpServer] Started.
Jersey app started with WADL available at http://localhost:9992/libraryrestwebservice/api/application.wadl
Hit enter to stop it...
```

## Tester

Les trois ressources `BookResource`, `BookResponseResource` et `BookContentResource` sont disponibles respectivement via les URI `/books`, `/responsebooks` et `/contentbooks`. Pour tester les différents services proposés par ces ressources, une solution simple est d'utiliser l'outil en ligne de commande **cURL**.

* Depuis la ligne de commande, exécuter l'instruction suivante pour invoquer la méthode `BookContentResource#updateContentBooksWithJSON` permettant de mettre à jour (PUT) les données d'un livre transmis en JSON :

```bash
curl --header "Content-Type: application/json" --request PUT --data '{"book_name":"harry","book_isbn":"1-111111-11"}' http://localhost:9992/libraryrestwebservice/api/contentbooks/json
```
