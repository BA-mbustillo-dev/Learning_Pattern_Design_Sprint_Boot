/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package banco.me.solicitud.pattern.chainofresponsability.ValidationState.ValidationInputEspecify;

import banco.me.solicitud.pattern.chainofresponsability.handlerResponsabilityInter;
import banco.me.solicitud.service.UtilitySolicitudImpl;
import banco.me.solicitud.service.UtilitySolicitudInter;
import java.math.BigDecimal;
import me.banco.ws.v1.services.ws_tra_solicitud_archivo.SolicitudArchivoRequestType;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author mbustillo
 */
public class NumeroCuentaOrigenDebito extends UtilitySolicitudImpl implements handlerResponsabilityInter{
    
    private handlerResponsabilityInter nextHandlerValidation;

    public NumeroCuentaOrigenDebito(handlerResponsabilityInter nextHandlerValidation) {
        this.nextHandlerValidation = nextHandlerValidation;
    }

    @Override
    public String validationDataStateInputEspecifyRequest(SolicitudArchivoRequestType getDataEspecifyRequest) {
        System.out.println("Validacion del Numero Cuenta Origen Debito");
        String codeStatus = "";
        if(getDataEspecifyRequest.getPantalla().charAt(0) != 'B' && (getDataEspecifyRequest.getCuentaOrigenDebito() == null 
                || getDataEspecifyRequest.getCuentaOrigenDebito().length() == 0 
                || !comparingNumToNumeric(getDataEspecifyRequest.getCuentaOrigenDebito()))){
            codeStatus = "5264";
        }else{
//            getDataEspecifyRequest.setCuentaOrigenDebito(null);
//            getDataEspecifyRequest.setMonto(BigDecimal.valueOf(0));
            codeStatus = nextHandlerValidation.validationDataStateInputEspecifyRequest(getDataEspecifyRequest);
        }
        return codeStatus;
    }
    
}
