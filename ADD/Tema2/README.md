Fichero binarios.

Main.java: Punto de entrada de la aplicacion, contiene el bucle infinito y se comunica directamente con Controller.java
Controller.java: Recibe la opcion elegida por el usuario, solicita datos a Repository.java y los pasa a Ui.java
Repository.java: lee(solo al inicio) y escribe el fichero "personas.dat" (cuando la opcion "grabar" es elegida).
Ui.java: muestra la informacion recibida desde Controller.java y recoge el input del usuario.

Puntos de interes:
- Los objetos son guardados uno a uno, pues implementan serializable y son leidos de la misma manera, dentro de un 
while(true), que espera a que salte la excepcion EOF (End of file).

- Las fechas son guardadas utilizando la clase util.Calendar.java. Para ello todas las fechas se instancian con el mismo año (1970) para permitir una comparación más sencilla en el metodo controller.felicitarPersona(). No hay validacion del input del usuario en la fecha, pues no presenta problema utilizar una fecha incorrecta.

- Una nueva id se genera sumando 1 al tamaño previo de ArrayList<Persona>.

- La clase Persona es abstracta e implementa el metodo public abstract String toString(), el cual se usuará a la hora de listar las personas. 
