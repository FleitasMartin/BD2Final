# Proyecto final Base de Datos 2

### Motivación del proyecto:
Desarrollar una herramienta que permita definir las pautas de aprobación de una materia en particular, permitiendo definir los detalles de los exámenes que ésta presentaría.
De cada evaluación parcial se puede detallar si es práctica o teórica, si su calificación es letrada o numérica, y también cada tema abarcado teniendo la posibilidad de detallar si cada uno lleva un peso particular sobre el total de la nota.
A su vez es posible detallar la nota mínima para aprobar; si existe la posibilidad de promoción y en tal caso si el parcial aplica para la promoción; además marcar la existencia de un examen flotante donde se pueda recuperar alguno de las evaluaciones ya detalladas.


### Alcance del proyecto:
Dado que este desarrollo se presenta como una prueba de concepto, el alcance del mismo se reduce a un alta, baja y modificación concurrente de la pauta de aprobación de una materia en particular.


### Tecnologías utilizadas:
El proyecto en si abarca dos módulos independientes:

- Backend:
Se implementó en java 8, utilizando Spring Boot con server embebido, Spring para inyección de dependencias y repositorio DAO, Jackson para serialización de json, Mapstruct para mapeo
de objeto request a objeto de modelo, y un Cassandra para la persistencia NoSQL, respecto a la persistencia se utilizó una columna de versionado para controlar las
escrituras concurrentes de los objetos.

- Frontend:
Se implementó utilizando Angular CLI 7.3.8, haciendo uso de Angular Components, HTTP Client, Angular Routing, y Services. Para los estilos se utilizó Material Design.


### Prerrequisitos:

- Instalar NodeJS (https://nodejs.org/en/) versión 10.9+

- Instalar Angular CLI (https://cli.angular.io/) haciendo uso del siguiente comando
npm install -g @angular/cli

- Instalar maven (https://maven.apache.org/)

- Instalar Apache Cassandra 3.11.x (http://cassandra.apache.org/download/)

### Pasos para correr la aplicación:

- Tener corriendo Cassandra en el puerto por default (9042) sin autenticación.

- Tener liberados los puertos que requiere el front para correr (4200) y el back (8080)

- Clonar el repositorio.

- Instalar los módulos de npm en la carpeta client, de la siguiente
    ```bash
    cd client
    npm install
    ```

- Volver a al carpeta padre para realizar un build del paquete de la siguiente manera:
    ```bash
    cd ..
    mvn clean package
    ```

- Entrar a la carpeta server para levantar el backend, de la siguiente manera:
    ```bash
    cd server
    java -jar target/server-0.0.1-SNAPSHOT.jar
    ```

- En otra terminal entrar a la carpeta client para levantar el frontend, de la siguiente manera:
    ```bash
    cd client
    npm start
    ```
