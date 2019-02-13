FROM openjdk:8-jre-alpine

ENV SPRING_OUTPUT_ANSI_ENABLED=ALWAYS \
    APP_SLEEP=0 \
    JAVA_OPTS=""

RUN adduser -D -s /bin/sh spprod
WORKDIR /home/spprod

ADD entrypoint.sh entrypoint.sh
RUN chmod 755 entrypoint.sh && chown spprod:spprod entrypoint.sh
USER spprod

ADD *.war refapp.war

ENTRYPOINT ["./entrypoint.sh"]

EXPOSE 8080