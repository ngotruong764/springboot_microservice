# Generate Docker image

# In order to run application, the very first requirement
# is that we need to install JRE or Java inside any system

# FROM: telling to docker that our Docker image has a dependency on some
# other Docker image
# image_name: tag_name(similar to version)
# Start with a base image containing Java runtime
FROM openjdk:21-jdk-slim

# Tell docker server, there is a jar inside the target folder
# Copy this file to docker image
# This .jar file contains: all the business code, related libs
COPY target/accounts-0.0.1-SNAPSHOT.jar accounts-0.0.1-SNAPSHOT.jar

# Telling whenever some one is trying to generate a container from our image
# please execute the command
# Execute the application
ENTRYPOINT ["java", "-jar", "accounts-0.0.1-SNAPSHOT.jar"]