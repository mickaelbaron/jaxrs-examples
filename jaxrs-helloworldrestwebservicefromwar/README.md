# Projet JAXRS-HelloWorldRESTWebServiceFromWar

Un exemple qui montre comment utiliser la spécification JAX-RS et l'implémentation Jersey pour développer un service web REST avec le langage Java pour un déploiement sur Tomcat. Nous présentons également les différentes configurations afin de déclarer les classes de ressources dans une application web Java (war). Finalement nous expliquons comment déployer le fichier war avec un conteneur Docker basé sur une image Tomcat.

## Générer un WAR et déployer ce WAR vers un serveur d'application

### Comment compiler

Quatre configuration sont proposées :

* en utilisant le fichier _web.xml_ et en précisant le package qui contient les ressources ;
* en utilisant le fichier _web.xml_ et en listant les classes ressources ;
* en utilisant le fichier _web.xml_ et en précisant une classe hériant `ResourceConfig` ;
* sans fichier _web.xml_ (serveur d'application doit être compatible Servlet 3).

Choisissez l'une des quatre configurations pour la compilation.

#### Déclaration des packages contenant les ressources depuis _web.xml_

* À la racine du projet, exécuter la ligne de commande suivante pour générer le fichier _.war_ :

```bash
mvn clean package -P war-onlywebxmlpackages
```

#### Déclaration des ressources depuis _web.xml_

* À la racine du projet, exécuter la ligne de commande suivante pour générer le fichier _.war_ :

```bash
mvn clean package -P war-onlywebxmlclasses
```

#### Déclaration de ressources avec `ResourceConfig` depuis _web.xml_

* À la racine du projet, exécuter la ligne de commande suivante pour générer le fichier _.war_ :

```bash
mvn clean package -P war-webapplication
```

#### Déclaration de ressources avec `ResourceConfig` sans _web.xml_

* À la racine du projet, exécuter la ligne de commande suivante pour générer le fichier _.war_ :

```bash
mvn clean package -P war-withoutweb
```

### Comment déployer

* Exécuter la ligne de commande suivante pour télécharger l'image Docker correspondant à la version 9 de Tomcat s'exécutant sous un JRE 11

```bash
docker pull tomcat:9-jre11-slim
```

* Exécuter la ligne de commande suivante permettant de créer un conteneur Docker

```bash
docker run --rm --name helloworldrestservice-tomcat -v $(pwd)/target/helloWorldrestwebservicefromwar.war:/usr/local/tomcat/webapps/helloworldrestwebservicefromwar.war -it -p 8080:8080 tomcat:9-jre11-slim
```

## Tester

* Depuis un navigateur web, saisir l'URL `http://localhost:8080/helloworldrestwebservicefromwar/api/hello` pour faire appel à la ressource `/hello`. Pour la quatrième configuration, l'URL sera `http://localhost:8080/helloworldrestwebservicefromwar/api2/hello`.
