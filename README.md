  
# EsteticloudBackend

Este proyecto se generó con [Spring Tools 4] (https://spring.io/projects/spring-boot) versión 4.0.0.

## Preparar el ambiente

Primero se debe instalar Spring Tools 4, se puede encontrar en el sitio oficial:
https://spring.io/tools

## Instalar dependencias 

Luego se necesita importar la carpeta a la aplicacion de Spring Tools 4, para esto debe entrar a File => Open Proyects => y elegir la ubicación de los archivos.

Además se necesita un servidor apache y un servidor MySql, se recomienda instalar y descargar Xampp desde el sitio oficial.

https://www.apachefriends.org/es/download.html

## Ejecutar la aplicacion 

Finalmente para ejecutar la aplicación debe buscar la opción "Run as => Spring boot app" para generar el servidor de desarrollo. 

Las peticiones se deben hacer a "http://localhost:8080/". 

La aplicación se volverá a cargar automáticamente si modifica y guarda alguno de los archivos de origen.

## Compilar

Si quiere montar la aplicación en un servidor debe crear un archivo war, esto se logra buscando la opción "Run as => Maven install" para compilar el proyecto.  La compilación quedara en formato .WAR en la carpeta de destino.

