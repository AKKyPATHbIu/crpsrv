FROM adoptopenjdk/openjdk11

COPY crpsrv-1.0.jar crpsrv.jar

ENTRYPOINT ["java", "-jar", "crpsrv.jar", "--spring.profiles.active=compose"]