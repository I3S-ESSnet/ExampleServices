# Codelist service

## Run

### Poetry

We use Poetry for package management (dependencies, run, etc.).

So once installed, run this command in order to launch the service:

```
cd CodeListService/codelistservice
poetry run python app.py
```

The application will be available at http://0.0.0.0:5010/.

For now, the only real endpoint is http://0.0.0.0:5010/countries

### Docker

First build the image:

```
cd /path/to/CodeListService/
docker build -t codelistservice:latest .
```

Then run:

```
docker run -d -p 5010:5010 codelistservice
```
