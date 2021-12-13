# RetoMercado
Reto de mercado libre "X-Men detector de mutantes"

Instrucciones ejecucion de API.
Nota: La api no cuenta con URL puesto aun no pudo ser deployada, pruebas deben hacerse con workspace local.

1- Descargar el proyecto en zip, extraer.

2- Abrir el proyecto extraido con un IDE.

3- Crear la config run de maven 2:
*Directorio base--> proyecto local (xmen)
*Metas --> clean install
*JRE --> por defecto
*Confiracion de usuario --> por defecto

4- Luego de correr la configuracion, correr la aplicacion como java project o spring project.

5- UItiliza base de datos H2 levantada en memoria, utilizando postman (o su preferido), realizar un POST con valores de cadena de ADN en formato Json con 
la siguiente URL http://localhost:8080/mutant, Ejemplo:
{
"secuencia" : ["AGGTTS", "AGGGTS", "AGGGGS"] 
}

debe devolver si es mutante o no.

6- En la URL http://localhost:8080/mutant/stats podemos realizar un GET que debe devolver cuantos mutantes fueron detectados hasta el momento.

debe devolver el numero de mutantes y humanos detectados hasta el momento.

7- Ejecutar los Junit test locales.

