Para instalar um driver:

http://wildfly.org/news/2014/02/06/GlassFish-to-WildFly-migration/

module add --name=org.postgresql --resources=postgresql-9.4-1200-jdbc41.jar --dependencies=javax.api,javax.transaction.api

/subsystem=datasources/jdbc-driver=postgresql:add(driver-name=postgresql,driver-module-name=org.postgresql,driver-class-name=org.postgresql.Driver