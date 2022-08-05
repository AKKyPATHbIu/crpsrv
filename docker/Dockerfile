FROM adoptopenjdk/openjdk11

COPY crpsrv-1.0.jar recsrv.jar

ENTRYPOINT ["java", "-jar", "crpsrv.jar", "--spring.profiles.active=compose"]