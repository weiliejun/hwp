FROM tomcat
#VOLUME /tmp
ADD /admin-0.0.1-SNAPSHOT.war app.war
#RUN sh -c 'touch /app.war'
#ENV JAVA_OPTS=""
#ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /app.war" ]
EXPOSE 8080
ENTRYPOINT ["java","-jar","/app.war"]