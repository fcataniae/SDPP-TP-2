# SDPP-TP-2

## Ejecutar el punto 1

Dentro de la carpeta punto1 se encuentran los archivos Punto.jar y masters.json, al momento de ejecucion ambos archivos deben encontrarse en el mismo directorio. (previamente debe configurarse en el masters.json la ip y puerto correspondiente al master que se va a iniciar)

### para iniciar el Master

sobre una terminal ejecutamos el siguiente comando

java -jar Punto1.jar -M -p {puerto}

donde {puerto} debe ser un numero, y si no se indica el parametro -p se toma por defecto el puerto 8000

### para iniciar un Peer

sobre una terminal ejecutamos el siguiente comando

java -jar Punto1.jar -P -sf {ruta-a-carpeta-compartida} -p {puerto}

donde {ruta-a-carpeta-compartida} es la ruta completa a la carpeta que desea compartir el peer y debe indicarse de manera obligatoria y {puerto} es el puerto donde va escuchar las conexiones de otros peer para la descarga de archivos, que de no indicarse por comando se toma por defecto 8001

Para realizar la prueba deberia iniciarse al menos 2 peer, para verificar que la descarga de archivos funciona correctamente

## Ejecutar el punto 2*

Dentro de la carpeta punto2 se encuentran los archivos punto2.jar y cuentas.json,  al momento de ejecucion ambos archivos deben encontrarse en el mismo directorio.

### iniciar server

sobre una terminal ejecutar el siguiente comando

java -jar punto2.jar -S -p {puerto}

si no se indica {puerto} se toma por defecto el 8000. Este quedara a la espera de recibir peticiones

### iniciar interfaz

sobre una terminal ejecutar el siguiente comando

java -jar punto2.jar -P -h {host-server} -p {port-server}

donde {host-server} es la ip donde se esta ejecutando el servidor y {port-server} es el puerto donde escucha el servidor.
En esta interfaz tendremos dos opciones:
* prueba unitaria: donde ingresamos el monto y el tipo de operacion a realizar y lo ejecuta 1 vez contra el server, y
* n transaccion: donde ingresamos el monto y la cantidad de veces a ejecutar la operacion (aleatoriamente se genera el tipo de operacion)

##### *para ejecutarlo sin sincronizacion  se puede editar el archivo TransactionThread.java borrando las lineas 47 y 66 y volviendo a generar el archivo .jar con mvn package y ejecutando como se indica anteriormente

## Ejecutar el punto 3

Dentro de la carpeta punto3 se encuentran los archivos punto3.jar y rabbitmq-properties.json, al momento de ejecucion ambos archivos deben encontrarse en el mismo directorio. (previamente debe configurarse en el rabbitmq-properties.json la ip y puerto correspondiente al master que se va a iniciar y usuario y pass de rabit)

### para iniciar el Balancer

sobre una terminal ejecutamos el siguiente comando

java -jar punto3.jar -B -p {puerto}

donde {puerto} debe ser un numero, y si no se indica el parametro -p se toma por defecto el puerto 8000

### para iniciar el server

sobre una terminal ejecutamos el siguiente comando

java -jar punto3.jar -S

El seler se encarga del balanceo de carga y de iniciar los nodos cuando este los necesita (dependiendo del estado de los mismos)

### para iniciar la interfaz de usuario

sobre una terminal ejecutamos el siguiente comando

java -jar punto3.jar -U -h {host-balancer} -p {port-balancer}

donde {host-balancer} es la ip donde se esta ejecutando el balancer y {port-balancer} es el puerto donde escucha el balancer.

Aqui seleccionamos el metodo a utilizar y ingresamos un mensaje a enviar, tambien podemos seleccionar la cantidad de veces que se enviara el mensaje a modo de prueba para el balanceo de carga del dispatcher.

* Falta el proceso que va limpiando los nodos ociosos
* Se agrego un pequeño sleep en el thread node para que se pueda observar el balanceo de carga
* Lo metodos solo devuelven un mensaje distinto

## Ejecutar el punto4

Se debe descargar la carpeta punto4 y luego importar, desde Eclipse, como “Existing Maven Proyects”.

### para realizar el proceso centralizado

Se debe ejecutar la clase Principal dentro del paquete centralizado.

### para realizar el proceso distribuido.

Se debe ejecutar en primer lugar la clase Server dentro del paquete servidor. Luego se debera ejecutar la clase Cliente dentro del paquete cliente.

### para realizar el proceso distribuido mejorado.

Se debe ejecutar en primer lugar la clase ServerM dentro del paquete servidorM. Luego se debera ejecutar la clase ClienteM dentro del paquete clienteM.


###### para poder visualizar correctamentes los puntos 1,2 y 3 desde el IDE se necesita instalar el plugin de lombok que se utiliza para el slf4j (https://projectlombok.org/setup/intellij para intellij idea)
