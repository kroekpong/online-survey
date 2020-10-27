FROM registry-vpc.ap-southeast-1.aliyuncs.com/saic-infra/tomcat:8.5.51-jdk8-openjdk
MAINTAINER RuYang

EXPOSE 8080
ENV TZ Asia/Bangkok
ENV JAVA_OPTS="-Xmx2g"
ADD target/survey.war /usr/local/tomcat/webapps/survey.war

