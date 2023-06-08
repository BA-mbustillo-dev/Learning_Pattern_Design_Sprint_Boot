package banco.me.solicitud.pattern.TempleteMethod;

import banco.me.solicitud.domain.ArchivoDetalleBeneficiario;
import banco.me.solicitud.domain.ArchivoDetallePlanilla;
import banco.me.solicitud.domain.GenEmpresaSeguroSocial;
import banco.me.solicitud.domain.TraArchivo;
import banco.me.solicitud.domain.fnFormatoArchivoRelacionCliente;
import banco.me.solicitud.repository.TraArchivoRepository;
import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;


/**
 *
 * @author mbustillo
 */

public class TXTFileProcess extends AbstractTempleteSolicitud {

    private String regexCero = "^0+(?!$)";

    private String log = "";

    public TXTFileProcess(File file, String logPath, String movePath, TraArchivoRepository traArchivoRepository) {
        super(file, logPath, movePath, traArchivoRepository);
    }

    @Override
    protected void validateName() throws Exception {
        String fileName = file.getName();
        if (!fileName.endsWith(".txt")) {
            throw new Exception("Nombre el Archivo no valido extension incorrecta");
        }
    }

    @Override
    protected List<Object> processFile() throws Exception {
        FileReader fileReader = new FileReader(file);
        BufferedReader lector = new BufferedReader(fileReader);
        List<Object> detallesArchivos = new ArrayList<Object>();

        try {
            String cadena;
            while ((cadena = lector.readLine()) != null) {
                ArchivoDetalleBeneficiario detalleBeneficiario = new ArchivoDetalleBeneficiario();
                detalleBeneficiario.setLinea(cadena);
                detalleBeneficiario.setAccion(cadena.substring(0, 1).trim().toUpperCase());
                detalleBeneficiario.setCodigoBeneficiario(cadena.substring(1, 17).trim().toUpperCase());
                detalleBeneficiario.setCodigoEmpresa(cadena.substring(17, 22).trim().toUpperCase().replaceAll(regexCero, ""));
                detalleBeneficiario.setCodigoServicio(cadena.substring(22, 25).trim().toUpperCase());
                detalleBeneficiario.setNumeroCuenta(cadena.substring(25, 37).trim().toUpperCase().replaceAll(regexCero, ""));
                detalleBeneficiario.setIdentificacionBeneficiario(cadena.substring(37, 50).trim().toUpperCase());
                detalleBeneficiario.setNombreBeneficiario(cadena.substring(50, 90).trim().toUpperCase());
                detalleBeneficiario.setCodigoBanco(cadena.substring(90, 92).trim().toUpperCase());
                detalleBeneficiario.setCodigoSucursal(cadena.substring(92, 94).trim().toUpperCase());
                detalleBeneficiario.setCuentaExterna(cadena.substring(94).trim().toUpperCase().replaceAll(regexCero, ""));
                
                log+= detalleBeneficiario.getLinea() + " - <Linea Registro Obtenido>\n";
                detallesArchivos.add(detalleBeneficiario);
            }
            return detallesArchivos;
        } finally {
            try {
                lector.close();
                fileReader.close();
            } catch (Exception e) {
            }
        }
    }

    @Override
    protected void createLog() throws Exception {
        FileOutputStream out = null;
        try {
            File outFile = new File(logPath+"/"+file.getName());
            if(!outFile.exists()){
                outFile.createNewFile();
            }
            out = new FileOutputStream(outFile, false);
            out.write(log.getBytes());
            out.flush();
        } finally {
            out.close();
        }
    }
}
