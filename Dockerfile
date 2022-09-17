FROM ubuntu
ADD target/renovation_address_checker-1.0-SNAPSHOT-jar-with-dependencies.jar /opt/address_checker/renovation_address_checker-1.0-SNAPSHOT-jar-with-dependencies.jar
ADD target/addresses /opt/address_checker/addresses
RUN apt-get update
RUN apt-get install wget -y
RUN wget -q https://dl.google.com/linux/direct/google-chrome-stable_current_amd64.deb
RUN apt-get install ./google-chrome-stable_current_amd64.deb -y
RUN apt-get install openjdk-11-jdk -y
# Set the locale
RUN apt-get install locales -y
RUN sed -i '/ru_RU.UTF-8/s/^# //g' /etc/locale.gen && \
    locale-gen
ENV LANG ru_RU.UTF-8
ENV LANGUAGE ru_RU:ru  
ENV LC_ALL ru_RU.UTF-8  
CMD ["java","-jar","/opt/address_checker/renovation_address_checker-1.0-SNAPSHOT-jar-with-dependencies.jar"]
