FROM java:8-jdk-alpine
RUN adduser -Dh /home/sprod sprod
WORKDIR /app
COPY target/refapp-2.0.0.war app.war
ENTRYPOINT ["java", "-jar", "app.war"]
CMD ["--spring.profiles.active=dev"]
