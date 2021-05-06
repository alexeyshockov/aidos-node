FROM maven:3.6-openjdk-16 AS builder
WORKDIR /build
COPY pom.xml .
RUN mvn dependency:go-offline
COPY . .
# Tests are separate from the image build itself
RUN mvn package -DskipTests

FROM openjdk:16-slim
# To store database files outside of the container
VOLUME /app
COPY --from=builder /build/target/ari.jar /
WORKDIR /app
EXPOSE 14265
ENTRYPOINT ["java", "-jar", "/ari.jar"]
CMD ["-r", "14265", "-p"]
