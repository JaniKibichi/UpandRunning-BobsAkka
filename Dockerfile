FROM openjdk:8

MAINTAINER JaniKibichi <janikibichi@gmail.com>

#set up git & sbt
RUN curl -L -o sbt-0.13.15.deb https://dl.bintray.com/sbt/debian/sbt-0.13.15.deb
RUN dpkg -i sbt-0.13.15.deb
RUN rm sbt-0.13.15.deb
RUN apt-get update && apt-get install -y wget

#Install Node and npm
RUN apt-get install -y curl software-properties-common
RUN curl -sL https://deb.nodesource.com/setup_8.x | bash -
RUN apt-get install -y nodejs
RUN node -v && npm -v

#Install Heroku ToolBelt
RUN wget -qO- https://toolbelt.heroku.com/install-ubuntu.sh | sh

#Make App Run as Daemon with SBT
RUN mkdir /upandrunning
COPY . /upandrunning
WORKDIR /upandrunning
RUN setsid nohup sbt run &

#Build
# sudo docker build -t javaandherokutoolbelt:1.0 .

#Push to Docker Hub
# docker login
# docker tag javaandherokutoolbelt grahamingokho/javaandherokutoolbelt
# docker push grahamingokho/javaandherokutoolbelt

#Run Interactive TTY
# sudo docker run -it javaandherokutoolbelt:1.0