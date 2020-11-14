package psp.practica1;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author SERGI
 */
public class MyThread implements Runnable{

    private final String fileName;
    private File file;
    private String threadName;
    private String threadPriority;
    private FileWriter fw;

    public MyThread(String fileName){
        this.fileName = fileName;
        createFile();
    }

    private void createFile(){

        this.file = new File(fileName);
        try {
            file.createNewFile();
            //fw = new FileWriter(file, true);
        } catch (IOException ex) {
            Logger.getLogger(MyThread.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void writeLine(FileWriter fw){

        try {
            fw.write("Soy el hilo "+ threadName +", mi prioridad es " + threadPriority + " y estoy escribiendo el fichero " + fileName + "\n");
        } catch (IOException ex) {
            Logger.getLogger(MyThread.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    @Override
    public void run() {

        this.threadName = Thread.currentThread().getName();
        this.threadPriority = "" + Thread.currentThread().getPriority();
        int i;
        FileWriter fw = null;

        for(i = 0; i<100; i++){
            try {
                Thread.sleep(100);
                fw = new FileWriter(file, true);
                writeLine(fw);
                fw.close();
            } catch (Exception ex) {
                Logger.getLogger(MyThread.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        System.out.println(threadName+": "+fileName+" completo");
    }
}
