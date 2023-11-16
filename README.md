# PersonApp Hexa Spring Boot

Este repositorio contiene el código fuente para la aplicación PersonApp, desarrollada siguiendo los principios de Arquitectura Limpia (Clean Architecture).

## Ejecución

### Requisitos previos
Antes de ejecutar la aplicación, asegúrate de tener instalados y configurados los siguientes componentes:

- MariaDB en el puerto 3307
- MongoDB en el puerto 27017

correr los scripts de las bases de datos


### Paso 1: Construir el Proyecto
Ejecuta el siguiente comando en PowerShell para construir el proyecto usando Maven:

```powershell
mvn clean install
```

### Paso 2: Ejecutar el Adaptador REST
Para ejecutar el adaptador REST, utiliza el siguiente comando:

```powershell
java -jar .\rest-input-adapter\target\rest-input-adapter-0.0.1-SNAPSHOT.jar
```

El adaptador REST estará disponible en el puerto 3000.

### Paso 3: Ejecutar el Adaptador CLI
Para ejecutar el adaptador CLI, utiliza el siguiente comando:

```powershell
java -jar .\cli-input-adapter\target\cli-input-adapter-0.0.1-SNAPSHOT.jar
```


## Informacion

El adaptador REST estará disponible en el puerto 3000, y la documentación Swagger estará disponible en http://localhost:3000/swagger-ui.html.
