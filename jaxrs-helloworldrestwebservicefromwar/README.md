# Projet JAXRS-HelloWorldRESTWebServiceFromWar

Un exemple qui montre comment utiliser la spécification JAX-RS et l'implémentation Jersey pour développer un service web REST avec le langage Java pour un déploiement sur Tomcat. Nous présentons également les différentes configurations afin de déclarer les classes de ressources dans une application web Java (WAR). Finalement nous expliquons comment déployer le fichier war avec un conteneur Docker basé sur une image Tomcat.

## Générer un WAR et déployer ce WAR vers un serveur d'application

### Comment compiler

Quatre configuration sont proposées :

- en utilisant le fichier _web.xml_ et en précisant le package qui contient les ressources ;
- en utilisant le fichier _web.xml_ et en listant les classes ressources ;
- en utilisant le fichier _web.xml_ et en précisant une classe hériant `ResourceConfig` ;
- sans fichier _web.xml_ (serveur d'application doit être compatible Servlet 3).

Choisissez l'une des quatre configurations pour la compilation.

#### Déclaration des packages contenant les ressources depuis _web.xml_

- À la racine du projet, exécuter la ligne de commande suivante pour générer le fichier _.war_ :

```bash
mvn clean package -P war-onlywebxmlpackages
```

#### Déclaration des ressources depuis _web.xml_

- À la racine du projet, exécuter la ligne de commande suivante pour générer le fichier _.war_ :

```bash
mvn clean package -P war-onlywebxmlclasses
```

#### Déclaration de ressources avec `ResourceConfig` depuis _web.xml_

- À la racine du projet, exécuter la ligne de commande suivante pour générer le fichier _.war_ :

```bash
mvn clean package -P war-webapplication
```

#### Déclaration de ressources avec `ResourceConfig` sans _web.xml_

- À la racine du projet, exécuter la ligne de commande suivante pour générer le fichier _.war_ :

```bash
mvn clean package -P war-withoutweb
```

### Comment déployer

- Exécuter la ligne de commande suivante pour télécharger l'image Docker correspondant à la version 10 de Tomcat s'exécutant sous un JRE 11

```bash
docker pull tomcat:jre11-openjdk-slim
```

- Exécuter la ligne de commande suivante permettant de créer un conteneur Docker

```bash
docker run --rm --name helloworldrestservice-tomcat -v $(pwd)/target/helloWorldrestwebservicefromwar.war:/usr/local/tomcat/webapps/helloworldrestwebservicefromwar.war -it -p 8080:8080 tomcat:jre11-openjdk-slim
```

La sortie console attendue :

```bash
Using CATALINA_BASE:   /usr/local/tomcat
Using CATALINA_HOME:   /usr/local/tomcat
Using CATALINA_TMPDIR: /usr/local/tomcat/temp
Using JRE_HOME:        /usr/local/openjdk-11
Using CLASSPATH:       /usr/local/tomcat/bin/bootstrap.jar:/usr/local/tomcat/bin/tomcat-juli.jar
Using CATALINA_OPTS:
NOTE: Picked up JDK_JAVA_OPTIONS:  --add-opens=java.base/java.lang=ALL-UNNAMED --add-opens=java.base/java.io=ALL-UNNAMED --add-opens=java.base/java.util=ALL-UNNAMED --add-opens=java.base/java.util.concurrent=ALL-UNNAMED --add-opens=java.rmi/sun.rmi.transport=ALL-UNNAMED
03-Mar-2025 07:14:18.147 INFO [main] org.apache.catalina.startup.VersionLoggerListener.log Server version name:   Apache Tomcat/10.0.23
03-Mar-2025 07:14:18.150 INFO [main] org.apache.catalina.startup.VersionLoggerListener.log Server built:          Jul 14 2022 08:16:11 UTC
03-Mar-2025 07:14:18.150 INFO [main] org.apache.catalina.startup.VersionLoggerListener.log Server version number: 10.0.23.0
03-Mar-2025 07:14:18.150 INFO [main] org.apache.catalina.startup.VersionLoggerListener.log OS Name:               Linux
03-Mar-2025 07:14:18.150 INFO [main] org.apache.catalina.startup.VersionLoggerListener.log OS Version:            6.10.14-linuxkit
03-Mar-2025 07:14:18.151 INFO [main] org.apache.catalina.startup.VersionLoggerListener.log Architecture:          amd64
03-Mar-2025 07:14:18.151 INFO [main] org.apache.catalina.startup.VersionLoggerListener.log Java Home:             /usr/local/openjdk-11
03-Mar-2025 07:14:18.151 INFO [main] org.apache.catalina.startup.VersionLoggerListener.log JVM Version:           11.0.16+8
03-Mar-2025 07:14:18.151 INFO [main] org.apache.catalina.startup.VersionLoggerListener.log JVM Vendor:            Oracle Corporation
03-Mar-2025 07:14:18.152 INFO [main] org.apache.catalina.startup.VersionLoggerListener.log CATALINA_BASE:         /usr/local/tomcat
03-Mar-2025 07:14:18.152 INFO [main] org.apache.catalina.startup.VersionLoggerListener.log CATALINA_HOME:         /usr/local/tomcat
...
03-Mar-2025 07:14:18.200 INFO [main] org.apache.catalina.core.AprLifecycleListener.lifecycleEvent Loaded Apache Tomcat Native library [1.2.35] using APR version [1.7.0].
03-Mar-2025 07:14:18.200 INFO [main] org.apache.catalina.core.AprLifecycleListener.lifecycleEvent APR capabilities: IPv6 [true], sendfile [true], accept filters [false], random [true], UDS [true].
03-Mar-2025 07:14:18.212 INFO [main] org.apache.catalina.core.AprLifecycleListener.initializeSSL OpenSSL successfully initialized [OpenSSL 1.1.1n  15 Mar 2022]
03-Mar-2025 07:14:18.578 INFO [main] org.apache.coyote.AbstractProtocol.init Initializing ProtocolHandler ["http-nio-8080"]
03-Mar-2025 07:14:18.616 INFO [main] org.apache.catalina.startup.Catalina.load Server initialization in [761] milliseconds
03-Mar-2025 07:14:18.695 INFO [main] org.apache.catalina.core.StandardService.startInternal Starting service [Catalina]
03-Mar-2025 07:14:18.696 INFO [main] org.apache.catalina.core.StandardEngine.startInternal Starting Servlet engine: [Apache Tomcat/10.0.23]
03-Mar-2025 07:14:18.719 INFO [main] org.apache.catalina.startup.HostConfig.deployWAR Deploying web application archive [/usr/local/tomcat/webapps/helloworldrestwebservicefromwar.war]
03-Mar-2025 07:14:19.435 INFO [main] org.apache.jasper.servlet.TldScanner.scanJars At least one JAR was scanned for TLDs yet contained no TLDs. Enable debug logging for this logger for a complete list of JARs that were scanned but no TLDs were found in them. Skipping unneeded JARs during scanning can improve startup time and JSP compilation time.
03-Mar-2025 07:14:19.780 WARNING [main] org.glassfish.jersey.message.internal.MessagingBinders$EnabledProvidersBinder.bindToBinder A class jakarta.activation.DataSource for a default provider MessageBodyWriter<jakarta.activation.DataSource> was not found. The provider is not available.
03-Mar-2025 07:14:19.804 WARNING [main] org.glassfish.jersey.server.wadl.WadlFeature.configure JAX-B API not found . WADL feature is disabled.
03-Mar-2025 07:14:20.043 WARNING [main] org.glassfish.jersey.server.wadl.WadlFeature.configure JAX-B API not found . WADL feature is disabled.
03-Mar-2025 07:14:20.077 INFO [main] org.apache.catalina.startup.HostConfig.deployWAR Deployment of web application archive [/usr/local/tomcat/webapps/helloworldrestwebservicefromwar.war] has finished in [1,358] ms
03-Mar-2025 07:14:20.085 INFO [main] org.apache.coyote.AbstractProtocol.start Starting ProtocolHandler ["http-nio-8080"]
03-Mar-2025 07:14:20.103 INFO [main] org.apache.catalina.startup.Catalina.start Server startup in [1487] milliseconds
```

## Tester

- Depuis un navigateur web, saisir l'URL `http://localhost:8080/helloworldrestwebservicefromwar/api/hello` pour faire appel à la ressource `/hello`. Pour la quatrième configuration, l'URL sera `http://localhost:8080/helloworldrestwebservicefromwar/api2/hello`.
