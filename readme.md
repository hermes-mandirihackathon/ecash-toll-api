# Mandiri Ecash Toll API

## Database setup

1. Install MySQL.
2. Buat database dengan nama "etoll"
```
CREATE DATABASE IF NOT EXISTS `etoll`;
```
3. Buat user dengan username "etoll" password "etoll" dengan langkah:
```
CREATE USER 'etoll'@'localhost' IDENTIFIED BY 'etoll';
GRANT USAGE ON *.* TO 'etoll'@'localhost' IDENTIFIED BY 'etoll' REQUIRE NONE WITH MAX_QUERIES_PER_HOUR 0 MAX_CONNECTIONS_PER_HOUR 0 MAX_UPDATES_PER_HOUR 0 MAX_USER_CONNECTIONS 0;
```
4. Grant privileges untuk etoll ke username etoll
```
GRANT ALL PRIVILEGES ON `etoll`.* TO 'etoll'@'localhost' WITH GRANT OPTION;
```
4. import sql pada `TODO add SQL`
5. Untuk konfigurasi database, pastikan anda mempunyai konfigurasi yang sesuai dengan `hibernate.cfg.xml`
6. Lanjut ke langkah via command prompt atau via IDE 

## How to build

`mvn clean compile package`

## How to run

`mvn clean compile tomcat:run`

