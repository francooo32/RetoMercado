# RetoMercado
Reto de mercado libre "X-Men detector de mutantes"

Instrucciones ejecucion de API.
Nota: La api no cuenta con URL puesto aun no pudo ser deployada, pruebas deben hacerse con workspace local.

1- Descargar el proyecto en zip, extraer.

2- Abrir el proyecto extraido con un IDE o clonar el proyecto en el workspace local desde la git URL:https://github.com/francooo32/RetoMercado.git <br />
Utilizar java 8, maven 2.

3- Crear la config run de maven 2: 
*Directorio base--> proyecto local (xmen) <br />
*Metas --> clean install -Dmaven.test.skip=true <br />
*JRE --> por defecto (recomendado 8 para arriba) <br />
*Confiracion de usuario --> por defecto <br />

4- Revisar el application.properties, debe contener la siguiente config:

**
server.port: 8080 <br />
server.address=0.0.0.0 <br />
spring.h2.console.enabled=true <br />
spring.datasource.url=jdbc:h2:mem:testdb <br />
spring.datasource.driverClassName=org.h2.Driver <br />
spring.datasource.username=sa <br />
spring.datasource.password= <br />
spring.jpa.database-platform=org.hibernate.dialect.H2Dialect <br />
spring.jpa.hibernate.ddl-auto = create <br />

**

si no la tiene, copiar y reemplazar.

5- Luego de correr la configuracion, correr la aplicacion como java project o spring project.

6- Utiliza base de datos H2 levantada en memoria, utilizando postman (o su preferido), realizar un POST con valores de cadena de ADN en formato Json con 
la siguiente URL http://localhost:8080/mutant, Ejemplo:

{
"secuencia" : ["AGGTTC", "AGGGTC", "AGGGGC"] 
}

o

{
"secuencia" : ["ATGCGA","CAGTGC","TTATGT","AGAAGG","CCTCCA","TCACTG"] 
}

debe devolver si es mutante o no, en estos casos especificos devuelve que "es mutante".

{
"secuencia" : ["AGGTTC", "AGGGTC", "AGGCCC"] 
}

el siguiente ejemplo devuelve "403 forbbiden" no es mutante.

NOTA: Es mutante cuando por lo menos se encuentra mas de una secuencia con 4 letras iguales, solo se analizan las letras (A,T,C,G).

7- En la URL http://localhost:8080/mutant/stats podemos realizar un GET que debe devolver cuantos mutantes y humanos fueron detectados hasta el momento.

8- Ejecutar los Junit test locales, se puede correr la clase entera con "click derecho sobre la clase, run as JUnit test" o correr un metodo especifico,
"nos situamos en el metodo de la clase, click derecho y run as JUnit test. 

