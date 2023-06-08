package banco.me.solicitud.service;

import banco.me.solicitud.domain.CatTipoArchivo;
import banco.me.solicitud.domain.CatTipoSolicitud;
import banco.me.solicitud.domain.Tra_Solicitud;
import banco.me.solicitud.domain.fnEmpresaValidacionSolicitud;
import banco.me.solicitud.repository.CatTipoArchivoRepository;
import banco.me.solicitud.repository.CatTipoSolicitudRepository;
import banco.me.solicitud.repository.EmpresaValidacionRepository;
import banco.me.solicitud.repository.TraSolicitudRepository;
import java.time.LocalDateTime;
import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class solicitudValidacionServiceImpl implements solicitudValidacionService{

    @Autowired
    EmpresaValidacionRepository empresaValidacionRepository;

    @Autowired
    CatTipoSolicitudRepository catTipoSolicitudRepository;

    @Autowired
    CatTipoArchivoRepository catTipoArchivoRepository;
    
    @Autowired
    TraSolicitudRepository TraSolicitudRepository;

    @Override
    public fnEmpresaValidacionSolicitud findEmpresaValidacion(Integer idCanal, String codigoMedioCarga, String codigoServicio,
                                                                                   String codigoEmpresa, Integer idMotor, String codigoBanco, Integer idInstitucion, Integer idCliente){
        return empresaValidacionRepository.findEmpresaValidacion(idCanal, codigoMedioCarga, codigoServicio, codigoEmpresa, idMotor, codigoBanco, idInstitucion, idCliente);
    }

    @Override
    public CatTipoSolicitud finByTipoSolicitud(Integer idMotor, Integer idInstitucion, String codigoTipoSolicitud) {
        return catTipoSolicitudRepository.findByTipoSolicitu(idMotor, idInstitucion, codigoTipoSolicitud);
    }

    @Override
    public CatTipoArchivo findByTipoArchivo(Integer idMotor, String codigoTipoArchivo) {
        return catTipoArchivoRepository.findByTipoArchivo(idMotor, codigoTipoArchivo);
    }
    
    @Override
    public Tra_Solicitud saveSolicitudArchivo(Tra_Solicitud solicitud){
        Tra_Solicitud traSolicitud = new Tra_Solicitud();
        try {
            traSolicitud = saveTraSolicitud(solicitud);
            return traSolicitud;
            
        } catch (Exception e) {
            log.info("Error en catch en el insert de la solicitud");
        }
        return null;
    }
    
    public Tra_Solicitud saveTraSolicitud(Tra_Solicitud solicitud){
        Tra_Solicitud traSolicitud = new Tra_Solicitud();
        traSolicitud.setIdInstitucion(solicitud.getIdInstitucion());
        traSolicitud.setIdMotor(solicitud.getIdMotor());
        traSolicitud.setIdCliente(solicitud.getIdCliente());
        traSolicitud.setIdArchivo(solicitud.getIdArchivo());
        traSolicitud.setAccion(solicitud.getAccion());
        traSolicitud.setIdEstadoProceso(solicitud.getIdEstadoProceso());
        traSolicitud.setUsuarioSolicita(solicitud.getUsuarioSolicita());
        traSolicitud.setFechaSolicitud(LocalDateTime.now());
        traSolicitud.setIdEmpresa(solicitud.getIdEmpresa());
        traSolicitud.setNombreArchivo(solicitud.getNombreArchivo());
        traSolicitud.setComentario(solicitud.getComentario());
        traSolicitud.setNumeroCuenta(solicitud.getNumeroCuenta());
        traSolicitud.setMonto(solicitud.getMonto());
        traSolicitud.setIdCanal(solicitud.getIdCanal());
        traSolicitud.setIdTipoArchivo(solicitud.getIdTipoArchivo());
        traSolicitud.setCodigoMoneda(solicitud.getCodigoMoneda());
        traSolicitud.setEstadoArchivo(solicitud.getEstadoArchivo());
        traSolicitud.setIdTipoSolicitud(solicitud.getIdTipoSolicitud());
        traSolicitud.setIdServicio(solicitud.getIdServicio());
        traSolicitud.setPantalla(solicitud.getPantalla());
        traSolicitud.setCodigoServicio(solicitud.getCodigoServicio());
        
        return TraSolicitudRepository.save(traSolicitud);
    }
}
