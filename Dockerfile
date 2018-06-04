FROM openjdk:8u171-jdk-slim
LABEL MAINTAINER='Maksim Kostormin https://github.com/daggerok'
RUN apt-get update -y \
 && apt-get install -y tree
