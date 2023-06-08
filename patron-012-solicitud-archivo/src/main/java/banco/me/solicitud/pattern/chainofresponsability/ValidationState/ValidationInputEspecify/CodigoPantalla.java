
package banco.me.solicitud.pattern.chainofresponsability.ValidationState.ValidationInputEspecify;

import banco.me.solicitud.pattern.chainofresponsability.handlerResponsabilityInter;
import banco.me.solicitud.service.UtilitySolicitudImpl;
import banco.me.solicitud.service.UtilitySolicitudInter;
import me.banco.ws.v1.services.ws_tra_solicitud_archivo.SolicitudArchivoRequestType;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author mbustillo
 */
public class CodigoPantalla extends UtilitySolicitudImpl implements handlerResponsabilityInter{
    
    private handlerResponsabilityInter nextHandlerValidation;

    public CodigoPantalla(handlerResponsabilityInter nextHandlerValidation) {
        this.nextHandlerValidation = nextHandlerValidation;
    }

    @Override
    public String validationDataStateInputEspecifyRequest(SolicitudArchivoRequestType getDataEspecifyRequest) {
        System.out.println("Validacion del codigo de pantalla de la solicitud");
        String codeStatus = "";
        if(getDataEspecifyRequest.getPantalla() == null || getDataEspecifyRequest.getPantalla().length() == 0 || !comparingStringToRegexAlfaNum(getDataEspecifyRequest.getPantalla().toString())){
            codeStatus = "5000";
        }else{
            codeStatus = nextHandlerValidation.validationDataStateInputEspecifyRequest(getDataEspecifyRequest);
        }
        return codeStatus;
    }

}
