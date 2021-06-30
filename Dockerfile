FROM smbq-docker.nexus-ci.corp.dev.vtb/openjdk/openjdk-11-rhel8:latest
COPY target/productprofile.jar /opt/productprofile.jar
COPY run.sh /opt/run.sh
RUN sed -i.bak 's/\r$//' /opt/run.sh
RUN chmod 777 /opt
RUN cp $JAVA_HOME/lib/security/cacerts /opt/cacerts \
  && chmod 777 /opt/cacerts \
  && chmod +x /opt/run.sh
ENTRYPOINT ["bash", "/opt/run.sh"]
