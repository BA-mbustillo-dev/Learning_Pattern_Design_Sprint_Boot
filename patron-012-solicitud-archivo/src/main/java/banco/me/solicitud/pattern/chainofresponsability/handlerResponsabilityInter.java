
package banco.me.solicitud.pattern.chainofresponsability;

import me.banco.ws.v1.services.ws_tra_solicitud_archivo.SolicitudArchivoRequestType;

/**
 *
 * @author mbustillo
 */
public interface handlerResponsabilityInter {
    
    public String validationDataStateInputEspecifyRequest(SolicitudArchivoRequestType getDataEspecifyRequest);
    
}
