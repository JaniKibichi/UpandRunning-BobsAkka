FROM openjdk:8

#Install SBT
RUN echo "deb https://dl.bintray.com/sbt/debian /" | sudo tee -a /etc/apt/sources.list.d/sbt.list
RUN apt-key adv --keyserver hkp://keyserver.ubuntu.com:80 --recv 2EE0EA64E40A89B84B2DF73499E82A75642AC823
RUN apt-get update -y && apt-get install -y sbt

#Install Node and npm
RUN apt-get install -y curl python-software-properties
RUN curl -sL https://deb.nodesource.com/setup_8.x | sudo -E bash -
RUN apt-get install nodejs
RUN node -v && npm -v

#Install Heroku ToolBelt
RUN snap install heroku --classic

#Build
# docker built -t JavaAndHerokuToolBelt:1.0 .

#Run Interactive TTY
# docker run -IT JavaAndHerokuToolBelt