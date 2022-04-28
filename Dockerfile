FROM openjdk:17-jdk-slim AS build

WORKDIR /app
# Install gradle
COPY gradlew ./
COPY gradle ./gradle/

# Retrieve all dependencies prior to copying source to avoid re-downloading every time source changes
ARG VERSION
COPY build.gradle.kts settings.gradle.kts ./
COPY ./config/detekt/detekt.yml ./config/detekt/detekt.yml

# Copy the source code and perform the final build
COPY src ./src/
RUN ./gradlew build bootJar

# **************
# * Deployment *
# **************
FROM gcr.io/distroless/java17-debian11

COPY --from=build /app/build/libs/spring-localstack.jar /app.jar

CMD ["app.jar"]
