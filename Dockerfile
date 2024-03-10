FROM amazoncorretto:21-alpine-jdk
WORKDIR /app

COPY build.gradle .
COPY gradlew .
COPY gradle/ ./gradle/
COPY src/ ./src/

RUN apk add --no-cache dos2unix && \
    dos2unix gradlew && \
    chmod +x gradlew

RUN ./gradlew assemble
CMD ["java", "-jar", "build/libs/saray-0.0.1-SNAPSHOT.jar"]