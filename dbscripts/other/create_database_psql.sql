# sudo -u postgres bash
# psql -U postgres
> CREATE USER spitter WITH PASSWORD 'spitter';
> ALTER USER spitter WITH CREATEDB CREATEROLE CREATEUSER;
> \q
# exit
# sudo useradd -m -p spitter spitter
# sudo -u spitter bash
# psql -U spitter -d postgres
> CREATE DATABASE spitterdb WITH OWNER spitter;
> \q
# psql -U spitter -d spitter
> CREATE USER dbunit WITH PASSWORD 'dbunit';
> \q
# sudo useradd -m -p dbunit dbunit
# psql -U spitter -d spitterdb
> CREATE SCHEMA dbunit AUTHORIZATION dbunit;
> GRANT ALL PRIVILEGES ON DATABASE spitterdb to dbunit;
