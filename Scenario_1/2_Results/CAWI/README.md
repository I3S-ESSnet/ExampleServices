# CAWI 

A web ui for taking eu weather survey

## Development environment

CAWI - web ui
The first example services is using .Net Core 3.1, and is written in C# for the web api and for front end ReactJS.

Visual Code is used together with Node.js and npm (node package manager)

Visual Code can be downloaded here: <https://code.visualstudio.com/download>

Node.js is found here: <https://nodejs.org/en/download/>

Together with .NET Core 3.1 SDK: <https://dotnet.microsoft.com/download/dotnet-core/3.1>

## Run

To build:

```bash
cd CAWI
dotnet build .
```

Publish:

```bash
dotnet publish .
```

To run:

```bash
cd cawi/bin/debug/netcoreapp3.1/publish
cawi.ui.exe
```

The application will be available at http://localhost:5000/.

### Docker

First build the image:

```
cd /path/to/CAWI/
docker build -t i3s-cawi:latest .
```

Then run:

```
docker run -d -p 5000:80 --name i3s-cawi
```