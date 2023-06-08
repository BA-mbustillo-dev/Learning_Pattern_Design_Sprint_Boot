/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package banco.me.solicitud.service;

import banco.me.solicitud.domain.GenEmpresaSeguroSocial;
import banco.me.solicitud.domain.TraArchivo;
import banco.me.solicitud.domain.Tra_Solicitud;
import banco.me.solicitud.domain.fnFormatoArchivoRelacionCliente;
import banco.me.solicitud.repository.GenEmpresaSSRepository;
import java.util.List;


/**
 *
 * @author mbustillo
 */
public interface LecturaArchivoService {
    
    public List<Object> lecturaArchivo(String url, String extencion, long longitud, String accion, String tipoArchivo);
    
    public TraArchivo getNombreArchivo(String nombreArchivoOriginal);
    
    public GenEmpresaSeguroSocial getEmpresaSS(String codigoParametro, Integer idInstitucion);
    
    public fnFormatoArchivoRelacionCliente findFormatoArchivoTxtCliente(Integer idCliente, 
            Integer idEmpresa, 
            Integer idTipoArchivo,
            long cantidadCaracteres,
            Integer idInstitucion,
            Integer idMotor,
            String extension);
    
    public fnFormatoArchivoRelacionCliente findFormatoArchivoTxtSeguroSocial(Integer idCliente, 
            Integer idEmpresa, 
            Integer idTipoArchivo,
            long cantidadCaracteres,
            Integer idInstitucion,
            Integer idMotor,
            String extension);
    
     public fnFormatoArchivoRelacionCliente findFormatoArchivoXlsxCliente(Integer idCliente, 
            Integer idEmpresa, 
            Integer idTipoArchivo,
            long cantidadColumnas,
            Integer idInstitucion,
            Integer idMotor,
            String extension);
    
}
