
package com.patron.patron.templete.method;

import com.patron.patron.templete.impl.DrugstoreFileProcess;
import java.io.File;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

/**
 *
 * @author mbustillo
 */
public class PatronTempleteMethod extends TimerTask{

    private static final String[] PATHS = new String[]{"C:/ftp_archivos/read"};
    private static final String LOG_DIR = "C:/ftp_archivos/log";
    private static final String PROCESS_DIR = "C:/ftp_archivos/process";
    
    public static void main(String[] args) {
        new PatronTempleteMethod().start();
    }
    
    public void start(){
        try {
            Timer timer = new Timer();
            timer.schedule(this, new Date(), (long) 1000*10);
            System.in.read();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        System.err.println("> Monitoring start");
        File f = new File(PATHS[0]);
        if(!f.exists()){
            throw new RuntimeException("El path "+PATHS[0]+" no existe");
        }
        
        File[] drugstoreFiles = f.listFiles();
        for(File file: drugstoreFiles){
            try {
                System.err.println("> File found > " + file.getName());
                new DrugstoreFileProcess(file, LOG_DIR, PROCESS_DIR).execute();
                System.out.println("Archivo Procesado> "+file.getName());
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
        }
    }
}
