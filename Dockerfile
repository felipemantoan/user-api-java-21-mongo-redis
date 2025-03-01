FROM vegardit/graalvm-maven:21.0.2 AS deps

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

RUN ./mvnw -Pnative native:compile -DskipTests

FROM debian:stable-slim AS production

COPY --from=builder --chmod=775 /app/target/user-api application

CMD ["./application"]