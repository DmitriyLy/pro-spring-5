FROM postgres:14
ENV POSTGRES_USER pro_spring
ENV POSTGRES_PASSWORD pro_spring
ENV POSTGRES_DB pro_spring

COPY init.sql /docker-entrypoint-initdb.d/init.sql
RUN mkdir /scripts
COPY drop_tables.sql /scripts/drop_tables.sql

EXPOSE 5432
