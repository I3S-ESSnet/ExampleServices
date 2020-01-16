#See https://aka.ms/containerfastmode to understand how Visual Studio uses this Dockerfile to build your images for faster debugging.

FROM mcr.microsoft.com/dotnet/core/aspnet:3.1-buster-slim AS base
WORKDIR /app
EXPOSE 80
# RUN curl -sL https://deb.nodesource.com/setup_10.x |  bash -
# RUN apt-get install -y nodejs

FROM mcr.microsoft.com/dotnet/core/sdk:3.1-buster AS build
WORKDIR /src
COPY ["StatisticalPrograms/StatisticalPrograms.csproj", "StatisticalPrograms/"]
COPY ["StatisticalPrograms.Data/StatisticalPrograms.Data.csproj", "StatisticalPrograms.Data/"]
RUN dotnet restore "StatisticalPrograms/StatisticalPrograms.csproj"
COPY . .
WORKDIR /src/StatisticalPrograms
RUN curl -sL https://deb.nodesource.com/setup_10.x |  bash -
RUN apt-get install -y nodejs
RUN dotnet build "StatisticalPrograms.csproj" -c Release -o /app/build

FROM build AS publish
RUN dotnet publish "StatisticalPrograms.csproj" -c Release -o /app/publish

FROM base AS final
WORKDIR /app
COPY --from=publish /app/publish .
ENTRYPOINT ["dotnet", "StatisticalPrograms.dll"]