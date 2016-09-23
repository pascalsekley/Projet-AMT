FROM jboss/wildfly:10.1.0.Final

MAINTAINER Pascal Sekley <pascal.sekley@heig-vd.ch>

ADD Bootcamp1-1.0-SNAPSHOT.war /opt/jboss/wildfly/standalone/deployments/

RUN /opt/jboss/wildfly/bin/add-user.sh admin admin --silent

CMD ["/opt/jboss/wildfly/bin/standalone.sh", "-b", "0.0.0.0", "-bmanagement", "0.0.0.0"]

