FROM eclipse-temurin:21-jdk-jammy

WORKDIR /producer

COPY .mvn/ .mvn
COPY mvnw .
COPY pom.xml .

RUN ./mvnw dependency:go-offline -B

COPY src ./src

RUN ./mvnw package -DskipTests

CMD ["sh", "-c", "java -jar target/*.jar"]