package com.patron.patron.templete.impl;

import java.io.File;
import com.patron.patron.templete.util.OnMemoryDataBase;

/**
 *
 * @author mbustillo
 */
public abstract class AbstractFileProcessTemplete {
    
    protected File file;
    protected String lonPath;
    protected String movePath;

    public AbstractFileProcessTemplete(File file, String lonPath, String movePath) {
        this.file = file;
        this.lonPath = lonPath;
        this.movePath = movePath;
    }
    
    public final void execute()throws Exception{
        validateName();
        validateProcess();
        processFile();
        createLog();
        moveDocument();
        markAsProcessFile();
    }
    
    protected abstract void validateName() throws Exception;
    
    protected void validateProcess() throws Exception{
        String fileStatus = OnMemoryDataBase.getFileStatus(file.getName());
        if(fileStatus != null && fileStatus.equals("Procesado")){
            throw new Exception("El Archivo "+ file.getName() + " ya fue procesado");
        }
        
    }
    
    protected abstract void processFile() throws Exception;
    
    protected abstract void createLog() throws Exception;
    
    private void moveDocument() throws Exception{
        String newPath = movePath+"/"+file.getName();
        boolean change = file.renameTo(new File(newPath));
    }
    
    protected void markAsProcessFile() throws Exception{
        OnMemoryDataBase.setProcessFile(file.getName());
    }
}
