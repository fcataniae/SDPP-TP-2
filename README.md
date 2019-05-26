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
  
## Ejecutar el punto 3

Dentro de la carpeta punto3 se encuentran los archivos punto3.jar y rabbitmq-properties.json, al momento de ejecucion ambos archivos deben encontrarse en el mismo directorio. (previamente debe configurarse en el rabbitmq-properties.json la ip y puerto correspondiente al master que se va a iniciar y usuario y pass de rabit)

### para iniciar el Server 

sobre una terminal ejecutamos el siguiente comando 

java -jar punto3.jar -S -p {puerto}
  
donde {puerto} debe ser un numero, y si no se indica el parametro -p se toma por defecto el puerto 8000
  
### para iniciar un Dispatcher

sobre una terminal ejecutamos el siguiente comando

java -jar punto3.jar -D
  
El dispatcher se encarga del balanceo de carga y de iniciar los nodos cuando este los necesita (dependiendo del estado de los mismos)

## para iniciar la interfaz de usuario

sobre una terminal ejecutamos el siguiente comando 

java -jar punto3.jar -U -h {host-server} -p {port-server}

donde {host-server} es la ip donde se esta ejecutando el servidor y {port-server} es el puerto donde escucha el servidor.

Aqui seleccionamos el metodo a utilizar y ingresamos un mensaje a enviar, tambien podemos seleccionar la cantidad de veces que se enviara el mensaje a modo de prueba para el balanceo de carga del dispatcher.

* Falta el proceso que va limpiando los nodos ociosos
* Se agrego un pequeño sleep en el thread node para que se pueda observar el balanceo de carga
* Lo metodos solo devuelven un mensaje distinto
  
