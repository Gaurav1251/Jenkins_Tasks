# # Use Tomcat base image with JDK 11
# FROM tomcat:9.0-jdk11

# # Copy the WAR file to Tomcat's webapps directory
# COPY ./ticketbooking2.war /usr/local/tomcat/webapps/app.war
# COPY context.xml /usr/local/tomcat/conf/
# # Expose the default Tomcat port
# EXPOSE 8080

# # Start Tomcat server
# CMD ["catalina.sh", "run"]
# Use Tomcat base image with JDK 17
# Use Tomcat base image with JDK 18
# Use JDK 18 base image
FROM eclipse-temurin:18-jdk

# Install wget and Tomcat manually
RUN apt-get update && apt-get install -y wget \
    && wget https://downloads.apache.org/tomcat/tomcat-9/v9.0.96/bin/apache-tomcat-9.0.96.tar.gz \
    && tar -xvzf apache-tomcat-9.0.96.tar.gz \
    && mv apache-tomcat-9.0.96 /usr/local/tomcat

# Copy the WAR file to Tomcat's webapps directory
COPY ./ticketbooking2.war /usr/local/tomcat/webapps/app.war
COPY context.xml /usr/local/tomcat/conf/

# Expose the default Tomcat port
EXPOSE 8080

# Start Tomcat server
CMD ["/usr/local/tomcat/bin/catalina.sh", "run"]


