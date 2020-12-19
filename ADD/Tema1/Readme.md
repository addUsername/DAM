Ficheros de texto.

En la raiz del proyecto se encuentran todos los ficheros que utilizará el programa.
- acceso.txt	Persistencia credenciales usuarios	
- bloqueados.txt	Persistencia usuarios bloquados
- login.log	(punto 7)
__________________
CLASES
./src/dam2/add/p1/

- Main.java	(Contiene main())
- User.java	(puntos 1 y 4)
- Controller.java	(puntos 2, 3, 4, 5 y 8)
- Repository.java	(Accede a los ficheros .txt, puntos 1, 4 y 7)
- Ui.java		(Interactua con el usuario, punto 6)
- Logger.java	(punto 7)
__________________
SIST. BLOQUEO

Este metodo es el equivalente al utilizado para realizar las operaciones CRUD en "acceso.txt", cada linea
almacena el nombre de usuario actualmente bloquado. La razón de esta implementación se debe a mantener
el fichero "acceso.txt" sin modificaciones siguiendo el requisito:

``` Los usuarios con los que se validarán los datos introducidos estarán almacenados en el fichero acceso.txt con el formato user:pass  ```.

Las operaciones de lectura, en ambos ficheros, se utiliza el método: Files.lines(path, charset);
Las operaciones de escritura con la clase FileWriter.java;
