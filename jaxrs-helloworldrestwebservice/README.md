# Projet JAXRS-HelloWorldRESTWebService

Un exemple qui montre comment utiliser la spécification JAX-RS et l'implémentation Jersey pour développer un service web REST avec le langage Java. Nous montrons également comment déployer le service web REST comme une application Java classique par l'intermédiaire du serveur web Grizzly.

## Comment compiler

* À la racine du projet, exécuter la ligne de commande suivante :

```bash
mvn clean package
```

## Comment exécuter

* Toujours depuis la racine du projet, exécuter la ligne de commande suivante :

```bash
$ java -cp "target/classes:target/dependency/*" fr.mickaelbaron.helloworldrestwebservice.HelloWorldRestWebServiceLauncher
oct. 31, 2018 4:02:36 PM org.glassfish.grizzly.http.server.NetworkListener start
INFO: Started listener bound to [localhost:9992]
oct. 31, 2018 4:02:36 PM org.glassfish.grizzly.http.server.HttpServer start
INFO: [HttpServer] Started.
Jersey app started with WADL available at http://localhost:9992/helloworldrestwebservice/api/application.wadl
Hit enter to stop it...
```

## Tester

* Depuis un navigateur web, saisir les URL suivantes :
  * <http://localhost:9992/helloworldrestwebservice/api/hello> pour faire appel à la ressource `/hello` ;
  * <http://localhost:9992/helloworldrestwebservice/api/application.wadl> pour générer le contrat WADL.
  