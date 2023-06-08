/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package banco.me.solicitud.pattern.chainofresponsability.ValidationState.ValidationInputEspecify;

import banco.me.generalValidation.domain.GeneralRequest;
import banco.me.generalValidation.domain.GeneralResponse;
import banco.me.solicitud.dto.BitacoraDto;
import banco.me.solicitud.pattern.chainofresponsability.handlerResponsabilityInter;
import banco.me.solicitud.service.UtilitySolicitudImpl;
import banco.me.solicitud.service.UtilitySolicitudInter;
import me.banco.ws.v1.services.ws_tra_solicitud_archivo.GeneralRequestType;
import me.banco.ws.v1.services.ws_tra_solicitud_archivo.SolicitudArchivoRequestType;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author mbustillo
 */
public class CodigoEmpresa extends UtilitySolicitudImpl implements handlerResponsabilityInter{
    

    
    private handlerResponsabilityInter nextHandlerValidation;

    public CodigoEmpresa(handlerResponsabilityInter next) {
        this.nextHandlerValidation = next;
    }

    @Override
    public String validationDataStateInputEspecifyRequest(SolicitudArchivoRequestType getDataEspecifyRequest) {
        System.out.println("Validacion codigo empresa del request");
        String codeStatus = "";
        if(getDataEspecifyRequest.getCodigoEmpresa() == null 
                || getDataEspecifyRequest.getCodigoEmpresa().length() == 0 
                || !comparingStringToRegexAlfaNum(getDataEspecifyRequest.getCodigoEmpresa())){
            codeStatus = "1087";
        }else{
            codeStatus = nextHandlerValidation.validationDataStateInputEspecifyRequest(getDataEspecifyRequest);
        }
        return codeStatus;
    }
}
