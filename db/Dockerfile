FROM postgres:15

COPY init.sql /docker-entrypoint-initdb.d/

ENV LANG en_US.UTF-8

ENV POSTGRES_DB movies_db

MAINTAINER Joanatas

ENV PORT 5432
ENV POSTGRES_DB client_db
ENV POSTGRES_USER client_db_user
ENV POSTGRES_PASSWORD client_db_password
ENV PGDATA: /var/lib/postgresql/data/

ENV PORT 5432
ENV PGDATA: /var/lib/postgresql/data/
ENV TZ=UTC
EXPOSE $PORT

