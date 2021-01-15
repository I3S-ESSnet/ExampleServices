# ErrorLocalization service

Simple service for error localization (under construction)

To build 

```
mvn clean install
```

To package as a Docker container:

```
docker build -t i3sessnet/errorlocalization .
docker run -p 8081:8081 i3sessnet/errorlocalization 
```

Test service
```
curl -v localhost:8080/validate/
```
