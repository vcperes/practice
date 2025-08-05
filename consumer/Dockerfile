FROM eclipse-temurin:21-jdk-jammy

WORKDIR /consumer

COPY .mvn/ .mvn
COPY mvnw .
COPY pom.xml .

RUN ./mvnw dependency:go-offline

COPY src ./src

RUN ./mvnw package -DskipTests

CMD ["sh", "-c", "java -jar target/*.jar"]