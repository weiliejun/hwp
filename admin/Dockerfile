FROM tomcat:9.0
COPY target/admin-0.0.1-SNAPSHOT.war /usr/local/tomcat/webapps
ENV JAVA_OPTS -Djava.security.egd=file:/dev/./urandom
USER root
EXPOSE 8080
CMD ["catalina.sh", "run"]