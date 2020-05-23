# DOMManager

DOMManager
**BETA, Funciona de manera secuencial, una vez escrito no podemos volver a modificarlo.**

Inicializamos el DOMManager, este requerira un modo de uso (Lectura, escritura, añadir, borrar) , despues el archivo donde escribiremos y finalmente el nombre del rootElement.
```
DOMManager domManager = new DOMManager(DOMManager.MODE.NEW, new File("data/test.xml"), "alumnes");
```
el metodo write tiene 2 variantes
La variante con el nombre del elemento y si este va a tener hijos
En caso de indicarle con true de que contendra hijos, el metodo write escribira en este elemento en las siguientes instrucciones.
```
domManager.write("alumne", true);
```
Con writeAttribute podemos escribir atributos
```
domManager.writeAttribute("edat", "18");
domManager.writeAttribute("pro", "true");
```

La variante con el nombre del elemento y su valor.
```
domManager.write("edat", "18");

domManager.write("Nom", "NombrePrueba");
domManager.write("Cognom1", "Cognom1Prueba");
domManager.write("Cognom2", "Cognom2Prueba");
domManager.write("Ciutat", "Santa Coloma de Gramenet");
domManager.write("Asignatura", true);
domManager.write("Assignatura", “M06”);
```
Con setParent volvemos al rootElement alumnes.
```
domManager.setParent(2);
```
Finalmente con close, escribimos los cambios y listo :)
```
domManager.close();
```
