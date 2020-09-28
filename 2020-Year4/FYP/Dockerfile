#See https://aka.ms/containerfastmode to understand how Visual Studio uses this Dockerfile to build your images for faster debugging.

#Depending on the operating system of the host machines(s) that will build or run the containers, the image specified in the FROM statement may need to be changed.
#For more information, please see https://aka.ms/containercompat

FROM mcr.microsoft.com/dotnet/core/aspnet:3.1-nanoserver-1903 AS base
WORKDIR /app
EXPOSE 80
EXPOSE 443

FROM mcr.microsoft.com/dotnet/core/sdk:3.1-nanoserver-1903 AS build
WORKDIR /src
COPY ["HD-University/2020-Year4/FYP/FYP.csproj", "HD-University/2020-Year4/FYP/"]
RUN dotnet restore "HD-University/2020-Year4/FYP/FYP.csproj"
COPY . .
WORKDIR "/src/HD-University/2020-Year4/FYP"
RUN dotnet build "FYP.csproj" -c Release -o /app/build

FROM build AS publish
RUN dotnet publish "FYP.csproj" -c Release -o /app/publish

FROM base AS final
WORKDIR /app
COPY --from=publish /app/publish .
ENTRYPOINT ["dotnet", "FYP.dll"]