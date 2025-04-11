# Getting Started

#### Instructions having resulted in this mini-app are confidential

To start the application, follow these steps:

1. Make sure you have [Java 21](https://www.oracle.com/java/technologies/javase/jdk21-archive-downloads.html) or higher installed on your machine.

2. Clone the repository using `git clone https://github.com/anasmost/MMSBE_JAVA.git`.

3. Go to the project's root directory using `cd back_java`.

4. **Option 1: Start without packaging**

   - Run the application using `./mvnw spring-boot:run`.

   - Alternatively, you can use your IDE to run the application by running the `BackJavaApplication` class.

5. **Option 2: Start after packaging**

   - Build the application using `./mvnw clean package`.

   - Start the application using `java -jar target/back_java-0.0.1-SNAPSHOT.jar`.

6. The application will start on port `3333` by default. You can change this by setting the `server.port` property in the `application.properties` file.

7. Once the application is started, you can access the api endpoints by following the instructions of the other confidential readme task to [http://localhost:3333](http://localhost:3333) in your HTTP client.

8. The Swagger UI is available at [http://localhost:3333/swagger-ui/index.html](http://localhost:3333/swagger-ui/index.html)

# Démarrer

#### Les instructions ayant abouti à cette mini-application sont confidentielles

Pour lancer l'application, suivez ces tapes:

1. Assurez-vous d'avoir [Java 21](https://www.oracle.com/java/technologies/javase/jdk21-archive-downloads.html) ou supérieur install sur votre machine.

2. Clonez en utilisant `git clone https://github.com/anasmost/MMSBE_JAVA.git`.

3. Allez dans le répertoire racine du projet en utilisant `cd back_java`.

4. **Option 1: Lancer sans packaging**

   - Exécutez l'application en utilisant `./mvnw spring-boot:run`.

   - Alternativement, vous pouvez utiliser votre IDE pour exécuter l'application en démarrant la classe `BackJavaApplication`.

5. **Option 2: Lancer après packaging**

   - Compilez l'application en utilisant `./mvnw clean package`.

   - Lancer l'application en utilisant `java -jar target/back_java-0.0.1-SNAPSHOT.jar`.

6. L'application va se lancer sur le port `3333` par d faut. Vous pouvez modifier ce paramètre en configurant la propriété `server.port` dans le fichier `application.properties`.

7. Une fois l'application lancée, vous pouvez accéder aux endpoints de l'api sur [http://localhost:3333](http://localhost:3333) dans votre client HTTP, en suivant les instructions du readme confidentiel.

8. L'interface utilisateur Swagger est disponible [http://localhost:3333/swagger-ui/index.html](http://localhost:3333/swagger-ui/index.html)
