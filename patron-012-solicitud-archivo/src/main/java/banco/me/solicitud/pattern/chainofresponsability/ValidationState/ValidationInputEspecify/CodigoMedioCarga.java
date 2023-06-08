/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
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
public class CodigoMedioCarga extends UtilitySolicitudImpl implements handlerResponsabilityInter{
    
    private handlerResponsabilityInter nextHandlerValidation;

    public CodigoMedioCarga(handlerResponsabilityInter nextHandlerValidation) {
        this.nextHandlerValidation = nextHandlerValidation;
    }

    @Override
    public String validationDataStateInputEspecifyRequest(SolicitudArchivoRequestType getDataEspecifyRequest) {
        System.out.println("Validacion del codigo de medio de carga");
        String codeStatus = "";
        Boolean prueba = !comparingStringToRegexAlfaNum(getDataEspecifyRequest.getCodigoMedioCarga());
        if(getDataEspecifyRequest.getCodigoMedioCarga() == null || getDataEspecifyRequest.getCodigoMedioCarga().length() == 0 || !comparingStringToRegexAlfaNum(getDataEspecifyRequest.getCodigoMedioCarga())){
            codeStatus = "5462";
        }else{
            codeStatus = nextHandlerValidation.validationDataStateInputEspecifyRequest(getDataEspecifyRequest);
        }
        return codeStatus;
    }    
}
