## Building solution with containers

## Build and run as Docker image
1. Open commandprompt and navigate to project folder
2. Use the following commands to build and run your Docker image:
```
$ docker build -t statisticalprograms .
$ docker run -d -p 8080:80 --name mystatisticalprogram statisticalprograms
```
## View web page
* Go to localhost:8080 to access your statisticalprograms app in the browswer