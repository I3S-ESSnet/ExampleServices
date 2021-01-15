# ErrorLocalization service

Simple service for error localization (under construction)

To build the application

```
mvn clean install
```

To package and build the application as a Docker container:

```
docker build -t i3sessnet/errorlocalization .
docker run i3sessnet/errorlocalization -p 8080:8080
```

Test service
```
curl -v localhost:8080/api/validate/
```
