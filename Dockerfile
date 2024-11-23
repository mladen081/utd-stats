FROM jelastic/maven:3.9.5-openjdk-21 AS build
####################
COPY . .
####################
RUN mvn clean package -DskipTests
####################
FROM openjdk:21-jdk-slim
####################
COPY --from=build /root/target/match-0.0.1-SNAPSHOT.jar match.jar
####################
EXPOSE 8080
####################
ENTRYPOINT ["java", "-jar", "match.jar"]

#####
#####
#####
#####

#################### Use an official OpenJDK runtime as a parent image
#FROM openjdk:21-jdk-slim

#################### Set the working directory in the container
#WORKDIR /app

#################### Copy the packaged JAR file into the container
#COPY target/match-0.0.1-SNAPSHOT.jar /app/app.jar

#################### Expose the port the application runs on
#EXPOSE 8080

##################### Run the JAR file when the container launches
#CMD ["java", "-jar", "app.jar"]