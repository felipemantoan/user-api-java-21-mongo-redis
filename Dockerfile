FROM eclipse-temurin:21-jdk-alpine-3.21 as development

ENV LANGUAGE='en_US:en'

WORKDIR /app

ADD .mvn/ ./.mvn/

COPY --chmod=775 mvnw ./mvnw

COPY pom.xml ./pom.xml

RUN ./mvnw dependency:go-offline

COPY ./src ./src

CMD ["./mvnw", "spring-boot:run"]

EXPOSE 8080
