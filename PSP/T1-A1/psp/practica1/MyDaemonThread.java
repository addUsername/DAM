
package psp.practica1;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author SERGI
 */
public class MyDaemonThread implements Runnable{

    String fileName1, fileName2, fileName3;
    
    public MyDaemonThread(String fileName1,String fileName2,String fileName3) {
        this.fileName1 = fileName1;
        this.fileName2 = fileName2;
        this.fileName3 = fileName3;
    }

    @Override
    public void run() {
        while(true){
            try {
                Thread.sleep(1000);
                System.out.println("Fichero: "+fileName1+" num lineas: "+Files.lines(Paths.get(fileName1), StandardCharsets.UTF_8).count());
                System.out.println("Fichero: "+fileName2+" num lineas: "+Files.lines( Paths.get(fileName2), StandardCharsets.UTF_8).count());
                System.out.println("Fichero: "+fileName3+" num lineas: "+Files.lines( Paths.get(fileName3), StandardCharsets.UTF_8).count());                
                
            } catch (Exception ex) {
                Logger.getLogger(MyDaemonThread.class.getName()).log(Level.SEVERE, null, ex);
            } 
        }
    }
    
}
