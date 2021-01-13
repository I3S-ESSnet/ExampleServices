# Errorlocalization service

Simple service for error localization (under construction)

To build 

```
mvn clean install
```

To package as a Docker container:

```
docker build -t i3sessnet/errorlocalization .
docker run i3sessnet/errorlocalization -p 8080:8080
```
