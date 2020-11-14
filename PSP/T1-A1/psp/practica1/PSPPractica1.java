
package psp.practica1;

/**
 * "Inherit less, interface more."
 *
 * @author SERGI
 */
public class PSPPractica1 {

    public static void main(String[] args) {
        Thread thread1 = new Thread(new MyThread("Fichero1.txt"),"Thread-1" );
        Thread thread2 = new Thread(new MyThread("Fichero2.txt"),"Thread-2" );
        Thread thread3 = new Thread(new MyThread("Fichero3.txt"),"Thread-3" );
        Thread daemonThread = new Thread(new MyDaemonThread("Fichero1.txt", "Fichero2.txt", "Fichero3.txt"),"DaemonThread" );

        thread1.setPriority(10);
        thread2.setPriority(5);
        thread3.setPriority(1);
        daemonThread.setDaemon(true);

        //check if priority works in this case
        thread2.start();
        thread3.start();
        thread1.start();

        daemonThread.start();
    }
}
