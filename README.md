# Quarkus Swagger Issue
This is a reproducer of the Quarkus issue #4006 (https://github.com/quarkusio/quarkus/issues/40006).

The project exposes a simple REST API that CRUDs customers in an H2 database. 
The unit tests run as expected but, trying to test with the Swagger UI, the 
following exception is raised:

    Caused by: org.hibernate.PersistentObjectException: detached entity passed to persist: fr.simplex_software.quarkus_swagger_issue.domain.Customer
        at org.hibernate.event.internal.DefaultPersistEventListener.persist(DefaultPersistEventListener.java:88)
        at org.hibernate.event.internal.DefaultPersistEventListener.onPersist(DefaultPersistEventListener.java:77)
        at org.hibernate.event.internal.DefaultPersistEventListener.onPersist(DefaultPersistEventListener.java:54)
        at org.hibernate.event.service.internal.EventListenerGroupImpl.fireEventOnEachListener(EventListenerGroupImpl.java:127)
        at org.hibernate.internal.SessionImpl.firePersist(SessionImpl.java:757

To reproduce:

    $ git https://github.com/nicolasduminil/quarkus-swagger-issue.git
    $ cd quarkus-swagger-issue
    $ mvn package

The unit tests should be executed successfully, as shown below:

    [INFO] 
    [INFO] Results:
    [INFO]
    [INFO] Tests run: 5, Failures: 0, Errors: 0, Skipped: 0
    [INFO]
    [INFO]
    [INFO] --- jar:3.3.0:jar (default-jar) @ quarkus-swagger-issue ---
    [INFO] Building jar: /home/nicolas/quarkus-swagger-issue/target/quarkus-swagger-issue.jar
    [INFO]
    [INFO] --- quarkus:3.11.0:build (default) @ quarkus-swagger-issue ---
    [INFO] [io.quarkus.deployment.QuarkusAugmentor] Quarkus augmentation completed in 2562ms
    [INFO] ------------------------------------------------------------------------
    [INFO] BUILD SUCCESS
    [INFO] ------------------------------------------------------------------------
    [INFO] Total time:  16.510 s
    [INFO] Finished at: 2024-09-12T16:15:43+02:00
    [INFO] ------------------------------------------------------------------------

Start the application:

    $ java -jar target/quarkus-app/quarkus-run.jar

Now start the Swagger UI interface at `http://localhost:8080/q/swagger-ui`, 
select the `POST` request, click on the `Try it out` button and then on `Execute`.

The mentioned exception will be displayed.