FROM mcr.microsoft.com/dotnet/core/aspnet:3.1-buster-slim AS base
WORKDIR /app
EXPOSE 443

FROM mcr.microsoft.com/dotnet/core/sdk:3.1-buster AS build
WORKDIR /src
COPY ["CAWI/CAWI.Ui.csproj", "CAWI/"]
RUN dotnet restore "CAWI/CAWI.Ui.csproj"
COPY . .
WORKDIR /src/CAWI
RUN curl -sL https://deb.nodesource.com/setup_10.x |  bash -
RUN apt-get install -y nodejs
RUN dotnet build "CAWI.Ui.csproj" -c Release -o /app/build

FROM build AS publish
RUN dotnet publish "CAWI.Ui.csproj" -c Release -o /app/publish

FROM base AS final
WORKDIR /app
COPY --from=publish /app/publish .
ENTRYPOINT ["dotnet", "CAWI.Ui.dll"]
EXPOSE 80