package banco.me.solicitud.service;

import banco.me.solicitud.domain.CatTipoArchivo;
import banco.me.solicitud.domain.CatTipoSolicitud;
import banco.me.solicitud.domain.Tra_Solicitud;
import banco.me.solicitud.domain.fnEmpresaValidacionSolicitud;

public interface solicitudValidacionService {

    public fnEmpresaValidacionSolicitud findEmpresaValidacion(Integer idCanal, String codigoMedioCarga, String codigoServicio,
                                                                String codigoEmpresa, Integer idMotor, String codigoBanco, Integer idInstitucion, Integer idCliente);

    public CatTipoSolicitud finByTipoSolicitud(Integer idMotor, Integer idInstitucion, String codigoTipoSolicitud);

    public CatTipoArchivo findByTipoArchivo(Integer idMotor, String codigoTipoArchivo);
    
    public Tra_Solicitud saveSolicitudArchivo(Tra_Solicitud solicitud);

}
