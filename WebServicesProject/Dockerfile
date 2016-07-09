FROM maven:3-jdk-8


ADD . /

RUN apt-get -y update && apt-get install -y python-pip && pip install awscli


RUN mvn package

CMD aws s3 cp $CONFIG_URL/application.properties target/application.properties && java -jar target/Id1-1.0-SNAPSHOT.jar --spring.config.location=./target/application.properties
