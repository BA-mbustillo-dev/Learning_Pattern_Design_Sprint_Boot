/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package banco.me.solicitud.service;

import banco.me.solicitud.pattern.TempleteMethod.TXTFileProcess;
import banco.me.solicitud.domain.ArchivoDetalleBeneficiario;
import banco.me.solicitud.domain.ArchivoDetallePlanilla;
import banco.me.solicitud.domain.GenEmpresaSeguroSocial;
import banco.me.solicitud.domain.TraArchivo;
import banco.me.solicitud.domain.Tra_Solicitud;
import banco.me.solicitud.domain.fnFormatoArchivoRelacionCliente;
import banco.me.solicitud.repository.FormatoArchivoClienteRepository;
import banco.me.solicitud.repository.GenEmpresaSSRepository;
import banco.me.solicitud.repository.TraArchivoRepository;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.sl.usermodel.*;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;

/**
 *
 * @author mbustillo
 */
@Slf4j
@RequiredArgsConstructor
@Service
//@Configuration
public class LecturaArchivoServiceImpl implements LecturaArchivoService{

    @Value("${expresion.numero.cero}")
    private String regexCero;
    
    
    private static String PATHS_IN = "C:\\ftp_archivos\\read\\I_Formato_Mantenimiento_Beneficiarios.txt";
    
    private static String PATHS_LOG = "C:\\ftp_archivos\\log";
    
    private static String PATHS_PROCESS = "C:\\ftp_archivos\\process";

    @Autowired
    FormatoArchivoClienteRepository formatoArchivoRepository;

    @Autowired
    TraArchivoRepository traArchivoRepository;

    @Autowired
    FormatoArchivoClienteRepository FormatoArchivoRepository;

    @Autowired
    GenEmpresaSSRepository empresaSSRepository;
    

    @Override
    public GenEmpresaSeguroSocial getEmpresaSS(String codigoParametro, Integer idInstitucion) {
        return empresaSSRepository.getCodigoEmpresaSS(codigoParametro, idInstitucion);
    }

    @Override
    public TraArchivo getNombreArchivo(String nombreArchivoOriginal) {
        return traArchivoRepository.getNombreArchivo(nombreArchivoOriginal);
    }

    @Override
    public fnFormatoArchivoRelacionCliente findFormatoArchivoTxtSeguroSocial(Integer idCliente, Integer idEmpresa, Integer idTipoArchivo, long cantidadCaracteres, Integer idInstitucion,
            Integer idMotor, String extension) {
        return FormatoArchivoRepository.findFormatoArchivoTxtSeguroSocial(idCliente, idEmpresa, idTipoArchivo, cantidadCaracteres, idInstitucion, idMotor, extension);
    }

    @Override
    public fnFormatoArchivoRelacionCliente findFormatoArchivoTxtCliente(Integer idCliente, Integer idEmpresa, Integer idTipoArchivo, long cantidadCaracteres, Integer idInstitucion,
            Integer idMotor, String extension) {
        return FormatoArchivoRepository.findFormatoArchivoTxtCliente(idCliente, idEmpresa, idTipoArchivo, cantidadCaracteres, idInstitucion, idMotor, extension);
    }

    @Override
    public fnFormatoArchivoRelacionCliente findFormatoArchivoXlsxCliente(Integer idCliente, Integer idEmpresa, Integer idTipoArchivo, long cantidadColumnas, Integer idInstitucion,
            Integer idMotor, String extension) {
        return FormatoArchivoRepository.findFormatoArchivoXlsxCliente(idCliente, idEmpresa, idTipoArchivo, cantidadColumnas, idInstitucion, idMotor, extension);
    }

    @Override
    public List<Object> lecturaArchivo(String url, String extencion, long longitud, String accion, String tipoArchivo) {

        System.out.println(PATHS_IN);
        System.out.println(PATHS_LOG);
        System.out.println(PATHS_PROCESS);
        
        File file = new File(PATHS_IN);
        
        if(!file.exists()){
            throw new RuntimeException("El path "+PATHS_IN+" No Existe");
        }
        
        try {
            return new TXTFileProcess(file, PATHS_LOG, PATHS_PROCESS, traArchivoRepository).execute();
        } catch (Exception e) {
            System.out.println("Error en la lectura de archivo> "+e.getMessage());
            return null;
        }
        
//        if (extencion.equals("txt")) {
//            return lecturaArchivoTXT(url, accion, tipoArchivo, longitud);
//        } else if (extencion.equals("xlsx")) {
//            return lecturaArchivoEXCEL(url, accion, tipoArchivo, longitud);
//        }
//        return null;
    }

    private List<Object> lecturaArchivoTXT(String url, String accion, String codigoArchivo, long longitud) {

        BufferedReader lector;
        List<Object> detallesArchivos = new ArrayList<Object>();
        String codigoEstado = "0000";

        try {
            long tam = 0;

            URL ruta = new URL(url);
            lector = new BufferedReader(new InputStreamReader(ruta.openStream()));
            String cadena;

            while ((cadena = lector.readLine()) != null) {
                if (longitud == cadena.length()) {
                    if (accion.equals("B")) {
                        ArchivoDetalleBeneficiario detalleBeneficiario = new ArchivoDetalleBeneficiario();
                        detalleBeneficiario.setLinea(cadena);
                        detalleBeneficiario.setAccion(cadena.substring(0, 1).trim().toUpperCase());
                        detalleBeneficiario.setCodigoBeneficiario(cadena.substring(1, 17).trim().toUpperCase());
                        detalleBeneficiario.setCodigoEmpresa(cadena.substring(17, 22).trim().toUpperCase().replaceAll(regexCero, ""));
                        detalleBeneficiario.setCodigoServicio(cadena.substring(22, 25).trim().toUpperCase());
                        detalleBeneficiario.setNumeroCuenta(cadena.substring(25, 37).trim().toUpperCase().replaceAll(regexCero, ""));
                        detalleBeneficiario.setIdentificacionBeneficiario(cadena.substring(37, 50).trim().toUpperCase());
                        detalleBeneficiario.setNombreBeneficiario(cadena.substring(50, 90).trim().toUpperCase());
                        switch (codigoArchivo) {
                            case "ABM1_TXT":
                                detalleBeneficiario.setCodigoBanco(cadena.substring(90, 92).trim().toUpperCase());
                                detalleBeneficiario.setCodigoSucursal(cadena.substring(92, 94).trim().toUpperCase());
                                detalleBeneficiario.setCuentaExterna(cadena.substring(94).trim().toUpperCase().replaceAll(regexCero, ""));
                                break;
                            case "ABM2_TXT":
                                detalleBeneficiario.setCorreoBeneficiario(cadena.substring(90, 130).trim().toUpperCase());
                                detalleBeneficiario.setTelefonoBeneficiario(cadena.substring(130, 138).trim().toUpperCase());
                                detalleBeneficiario.setCodigoBanco(cadena.substring(138, 140).trim().toUpperCase());
                                detalleBeneficiario.setCodigoSucursal(cadena.substring(140, 142).trim().toUpperCase());
                                detalleBeneficiario.setCuentaExterna(cadena.substring(142, 178).trim().toUpperCase().replaceAll(regexCero, ""));
                                detalleBeneficiario.setTipoNotificacion(cadena.substring(178, 179).trim().toUpperCase());
                                detalleBeneficiario.setTerminadorRegistro(cadena.substring(179).trim().toUpperCase());
                                break;
                            case "ANCP_TXT":
                                detalleBeneficiario.setSegundoIDBeneficiario(cadena.substring(90, 103).trim().toUpperCase());
                                detalleBeneficiario.setSegundoNombreBeneficiario(cadena.substring(103).trim().toUpperCase());
                                break;
                            default:
                                throw new AssertionError();
                        }
                        detallesArchivos.add(detalleBeneficiario);
                    } else if (accion.equals("P")) {
                        ArchivoDetallePlanilla detallePlanilla = new ArchivoDetallePlanilla();
                        detallePlanilla.setLinea(cadena);
                        switch (codigoArchivo) {
                            case "APCPS1_TXT":
                                detallePlanilla.setCodigoBeneficiario(cadena.substring(0, 16).trim().toUpperCase());
                                detallePlanilla.setCodigoBanco(cadena.substring(16, 18).trim().toUpperCase());
                                detallePlanilla.setCodigoEmpresaArchivo(cadena.substring(18, 23).trim().toUpperCase().replaceAll(regexCero, ""));
                                detallePlanilla.setTipoServicio(cadena.substring(23, 26).trim().toUpperCase());
                                detallePlanilla.setPagoNeto(cadena.substring(26, 42).trim().toUpperCase());
                                detallePlanilla.setDescripcionPago(cadena.substring(42).trim().toUpperCase());
                                break;
                            case "APCPS2_TXT":
                                detallePlanilla.setTipoRegistro(cadena.substring(0, 2).trim().toUpperCase());
                                detallePlanilla.setCodigoBeneficiario(cadena.substring(2, 18).trim().toUpperCase());
                                detallePlanilla.setCodigoBanco(cadena.substring(18, 20).trim().toUpperCase());
                                detallePlanilla.setCodigoEmpresaArchivo(cadena.substring(20, 25).trim().toUpperCase().replaceAll(regexCero, ""));
                                detallePlanilla.setTipoServicio(cadena.substring(25, 28).trim().toUpperCase());
                                detallePlanilla.setPagoNeto(cadena.substring(28, 44).trim().toUpperCase());
                                detallePlanilla.setTotalRegistro(cadena.substring(44, 47).trim().toUpperCase());
                                detallePlanilla.setDescripcionPago(cadena.substring(47, 87).trim().toUpperCase());
                                detallePlanilla.setReferenciaPagador(cadena.substring(87, 96).trim().toUpperCase());
                                detallePlanilla.setReferenciaBeneficiario(cadena.substring(96, 105).trim().toUpperCase());
                                detallePlanilla.setCodigoAutorizacion(cadena.substring(105, 107).trim().toUpperCase());
                                detallePlanilla.setProcesoRegistro(cadena.substring(107, 108).trim().toUpperCase());
                                detallePlanilla.setCodigoRespuesta(cadena.substring(108).trim().toUpperCase());
                                break;
                            default:
                                throw new AssertionError();
                        }
                        detallesArchivos.add(detallePlanilla);
                    } else if (accion.equals("R")) {
                        ArchivoDetallePlanilla detallePlanilla = new ArchivoDetallePlanilla();
                        detallePlanilla.setLinea(cadena);
                        switch (codigoArchivo) {
                            case "ACCR_TXT":
                                detallePlanilla.setCodigoBeneficiario(cadena.substring(0, 16).trim().toUpperCase());
                                detallePlanilla.setCodigoBanco(cadena.substring(16, 18).trim().toUpperCase());
                                detallePlanilla.setCodigoEmpresaArchivo(cadena.substring(18, 23).trim().toUpperCase().replaceAll(regexCero, ""));
                                detallePlanilla.setTipoServicio(cadena.substring(23, 26).trim().toUpperCase());
                                detallePlanilla.setPagoNeto(cadena.substring(26, 42).trim().toUpperCase());
                                detallePlanilla.setNumeroOrden(cadena.substring(42).trim().toUpperCase());
                                break;
                            default:
                                throw new AssertionError();
                        }
                        detallesArchivos.add(detallePlanilla);
                    }
                }
            }
            lector.close();
        } catch (Exception e) {
            System.out.println("Error:" + e.getMessage());
        }
        return detallesArchivos;
    }

    private List<Object> lecturaArchivoEXCEL(String url, String accion, String codigoArchivo, long longitud) {

        List<Object> detallesArchivos = new ArrayList<Object>();

        try {
            URL ruta = new URL(url);
            InputStream file = ruta.openStream();
            XSSFWorkbook libro = new XSSFWorkbook(file);
            XSSFSheet sheet = libro.getSheetAt(0);
            long numFilas = sheet.getLastRowNum() + 1;

            if (sheet.rowIterator().hasNext()) {
                for (int i = 5; i < numFilas; i++) {
                    int numColumna = 0;
                    Row fila = sheet.getRow(i);
                    numColumna = fila.getLastCellNum();
                    if (longitud == numColumna) {
                        if (rowEmpty(fila, longitud)) {
                            if (accion.equals("B")) {
                                ArchivoDetalleBeneficiario detalleBeneficiario = new ArchivoDetalleBeneficiario();
                                detalleBeneficiario.setAccion(fila.getCell(0).toString().trim().equals("") ? null : fila.getCell(0).toString().trim().toUpperCase());
                                detalleBeneficiario.setCodigoBeneficiario(fila.getCell(1).toString().trim().equals("") ? null : fila.getCell(1).toString().trim().toUpperCase());
                                detalleBeneficiario.setCodigoEmpresa(fila.getCell(2).toString().trim().equals("") ? null : fila.getCell(2).toString().trim().toUpperCase().replaceAll(regexCero, ""));
                                detalleBeneficiario.setCodigoServicio(fila.getCell(3).toString().trim().equals("") ? null : fila.getCell(3).toString().trim().toUpperCase());
                                detalleBeneficiario.setNumeroCuenta(fila.getCell(4).toString().trim().equals("") ? null : fila.getCell(4).toString().trim().toUpperCase().replaceAll(regexCero, ""));
                                detalleBeneficiario.setIdentificacionBeneficiario(fila.getCell(5).toString().trim().equals("") ? null : fila.getCell(5).toString().trim().toUpperCase());
                                detalleBeneficiario.setNombreBeneficiario(fila.getCell(6).toString().trim().equals("") ? null : fila.getCell(6).toString().trim().toUpperCase());
                                switch (codigoArchivo) {
                                    case "ABM1_EXCEL":
                                        detalleBeneficiario.setCodigoBanco(fila.getCell(7).toString().trim().equals("") ? null : fila.getCell(7).toString().trim().toUpperCase());
                                        detalleBeneficiario.setCodigoSucursal(fila.getCell(8).toString().trim().equals("") ? null : fila.getCell(8).toString().trim().toUpperCase());
                                        detalleBeneficiario.setCuentaExterna(fila.getCell(9).toString().trim().equals("") ? null : fila.getCell(9).toString().trim().toUpperCase().replaceAll(regexCero, ""));
                                        break;
                                    case "ABM2_EXCEL":
                                        detalleBeneficiario.setCorreoBeneficiario(fila.getCell(7).toString().trim().equals("") ? null : fila.getCell(7).toString().trim().toUpperCase());
                                        detalleBeneficiario.setTelefonoBeneficiario(fila.getCell(8).toString().trim().equals("") ? null : fila.getCell(8).toString().trim().toUpperCase());
                                        detalleBeneficiario.setCodigoBanco(fila.getCell(9).toString().trim().equals("") ? null : fila.getCell(9).toString().trim().toUpperCase());
                                        detalleBeneficiario.setCodigoSucursal(fila.getCell(10).toString().trim().equals("") ? null : fila.getCell(10).toString().trim().toUpperCase());
                                        detalleBeneficiario.setCuentaExterna(fila.getCell(11).toString().trim().equals("") ? null : fila.getCell(11).toString().trim().toUpperCase().replaceAll(regexCero, ""));
                                        detalleBeneficiario.setTipoNotificacion(fila.getCell(12).toString().trim().equals("") ? null : fila.getCell(12).toString().trim().toUpperCase());
                                        break;
                                    case "ANCP_EXCEL":
                                        detalleBeneficiario.setSegundoIDBeneficiario(fila.getCell(7).toString().trim().equals("") ? null : fila.getCell(7).toString().trim().toUpperCase());
                                        detalleBeneficiario.setSegundoNombreBeneficiario(fila.getCell(8).toString().trim().equals("") ? null : fila.getCell(8).toString().trim().toUpperCase());
                                        break;
                                    default:
                                        throw new AssertionError();
                                }

                                detallesArchivos.add(detalleBeneficiario);
                            } else if (accion.equals("P")) {
                                ArchivoDetallePlanilla detallePlanilla = new ArchivoDetallePlanilla();
                                switch (codigoArchivo) {
                                    case "APCPS1_EXCEL":
                                        detallePlanilla.setCodigoBeneficiario(fila.getCell(0).toString().trim().equals("") ? null : fila.getCell(0).toString().trim().toUpperCase());
                                        detallePlanilla.setCodigoBanco(fila.getCell(1).toString().trim().equals("") ? null : fila.getCell(1).toString().trim().toUpperCase());
                                        detallePlanilla.setCodigoEmpresaArchivo(fila.getCell(2).toString().trim().equals("") ? null : fila.getCell(2).toString().trim().toUpperCase().replaceAll(regexCero, ""));
                                        detallePlanilla.setTipoServicio(fila.getCell(3).toString().trim().equals("") ? null : fila.getCell(3).toString().trim().toUpperCase());
                                        detallePlanilla.setPagoNeto(fila.getCell(4).toString().trim().equals("") ? null : fila.getCell(4).toString().trim().toUpperCase());
                                        detallePlanilla.setDescripcionPago(fila.getCell(5).toString().trim().equals("") ? null : fila.getCell(5).toString().trim().toUpperCase());
                                        break;
                                    case "APCPS2_EXCEL":
                                        detallePlanilla.setTipoRegistro(fila.getCell(0).toString().trim().equals("") ? null : fila.getCell(0).toString().trim().toUpperCase());
                                        detallePlanilla.setCodigoBeneficiario(fila.getCell(1).toString().trim().equals("") ? null : fila.getCell(1).toString().trim().toUpperCase());
                                        detallePlanilla.setCodigoBanco(fila.getCell(2).toString().trim().equals("") ? null : fila.getCell(2).toString().trim().toUpperCase());
                                        detallePlanilla.setCodigoEmpresaArchivo(fila.getCell(3).toString().trim().equals("") ? null : fila.getCell(3).toString().trim().toUpperCase().replaceAll(regexCero, ""));
                                        detallePlanilla.setTipoServicio(fila.getCell(4).toString().trim().equals("") ? null : fila.getCell(4).toString().trim().toUpperCase());
                                        detallePlanilla.setPagoNeto(fila.getCell(5).toString().trim().equals("") ? null : fila.getCell(5).toString().trim().toUpperCase());
                                        detallePlanilla.setTotalRegistro(fila.getCell(6).toString().trim().equals("") ? null : fila.getCell(6).toString().trim().toUpperCase());
                                        detallePlanilla.setDescripcionPago(fila.getCell(7).toString().trim().equals("") ? null : fila.getCell(7).toString().trim().toUpperCase());
                                        detallePlanilla.setReferenciaPagador(fila.getCell(8).toString().trim().equals("") ? null : fila.getCell(8).toString().trim().toUpperCase());
                                        detallePlanilla.setReferenciaBeneficiario(fila.getCell(9).toString().trim().equals("") ? null : fila.getCell(9).toString().trim().toUpperCase());
                                        break;
                                    default:
                                        throw new AssertionError();
                                }
                                detallesArchivos.add(detallePlanilla);
                            } else if (accion.equals("R")) {
                                ArchivoDetallePlanilla detallePlanilla = new ArchivoDetallePlanilla();
                                switch (codigoArchivo) {
                                    case "ACCR_EXCEL":

                                        detallePlanilla.setCodigoBeneficiario(fila.getCell(0).toString().trim().equals("") ? null : fila.getCell(0).toString().trim().toUpperCase());
                                        detallePlanilla.setCodigoBanco(fila.getCell(1).toString().trim().equals("") ? null : fila.getCell(1).toString().trim().toUpperCase());
                                        detallePlanilla.setCodigoEmpresaArchivo(fila.getCell(2).toString().trim().equals("") ? null : fila.getCell(2).toString().trim().toUpperCase().replaceAll(regexCero, ""));
                                        detallePlanilla.setTipoServicio(fila.getCell(3).toString().trim().equals("") ? null : fila.getCell(3).toString().trim().toUpperCase());
                                        detallePlanilla.setPagoNeto(fila.getCell(4).toString().trim().equals("") ? null : fila.getCell(4).toString().trim().toUpperCase());
                                        detallePlanilla.setNumeroOrden(fila.getCell(5).toString().trim().equals("") ? null : fila.getCell(5).toString().trim().toUpperCase());
                                        break;
                                    default:
                                        throw new AssertionError();
                                }
                                detallesArchivos.add(detallePlanilla);
                            }
                        }
                    }
                }
            }
            libro.close();
            file.close();
        } catch (Exception e) {
            System.out.println("Error:" + e.getMessage());
        }
        return detallesArchivos;
    }

    private boolean rowEmpty(Row fila, long numCol) {
        int cont = 0;
        for (int i = 0; i < numCol; i++) {
            if (fila.getCell(i).toString().trim().equals("") || fila.getCell(i).toString() == null) {
                cont++;
            }
        }
        if (cont == numCol) {
            return false;
        } else {
            return true;
        }
    }
}
