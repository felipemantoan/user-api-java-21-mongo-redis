FROM eclipse-temurin:21.0.6_7-jdk AS deps

ENV LANGUAGE='en_US:en'

WORKDIR /app

ADD .mvn/ ./.mvn/

COPY --chmod=775 mvnw ./mvnw

COPY pom.xml ./pom.xml

RUN ./mvnw dependency:go-offline

FROM deps AS development

COPY ./src ./src

CMD ["./mvnw", "spring-boot:run"]

EXPOSE 8080

FROM development AS builder

RUN ./mvnw package -DskipTests

FROM eclipse-temurin:21.0.6_7-jre AS production

COPY --from=builder --chmod=775 /app/target/*.jar application.jar

CMD ["java", "-jar", "/application.jar"]