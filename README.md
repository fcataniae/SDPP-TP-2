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
  
  
