
package banco.me.solicitud.pattern.TempleteMethod;

import banco.me.solicitud.domain.TraArchivo;
import banco.me.solicitud.repository.TraArchivoRepository;
import com.google.gson.Gson;
import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

/**
 *
 * @author mbustillo
 */
public abstract class AbstractTempleteSolicitud{
    
    protected File file;
    protected String logPath;
    protected String movePath;
    private TraArchivoRepository traArchivoRepository;
   

    public AbstractTempleteSolicitud(File file, String logPath, String movePath, TraArchivoRepository traArchivoRepository) {
        this.file = file;
        this.logPath = logPath;
        this.movePath = movePath;
        this.traArchivoRepository = traArchivoRepository;
    }
    
    public final List<Object> execute() throws Exception{
        validateName();
        validateProcess();
        processFile();
        createLog();
        //moveDocument();
        return processFile();
    }
    
    protected abstract void validateName() throws Exception;
    
    protected void validateProcess() throws Exception{
        TraArchivo getNameFileProcessedArchivo = traArchivoRepository.getNombreArchivo(file.getName());
        if(getNameFileProcessedArchivo != null){
            throw new Exception("Nombre del archivo procesado");
        }
    }
    
    protected abstract List<Object> processFile() throws Exception;
    
    protected abstract void createLog() throws Exception;
    
    private void  moveDocument() throws Exception{
        DateTimeFormatter dateTimeFormat = DateTimeFormatter.ofPattern("yyMMddHHmmss");
        String newPath = movePath+"\\"+file.getName()+ "###" + LocalDateTime.now().format(dateTimeFormat);
        boolean change = file.renameTo(new File(newPath));
    }
    
}
