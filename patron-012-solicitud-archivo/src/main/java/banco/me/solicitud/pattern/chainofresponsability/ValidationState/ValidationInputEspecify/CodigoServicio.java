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
public class CodigoServicio extends UtilitySolicitudImpl implements handlerResponsabilityInter{

    @Override
    public String validationDataStateInputEspecifyRequest(SolicitudArchivoRequestType getDataEspecifyRequest) {
        System.out.println("Validacion del codigo de servicio");
        String codeStatus = "";
        if(getDataEspecifyRequest.getCodigoServicio() == null || getDataEspecifyRequest.getCodigoServicio().length() == 0 || !comparingStringToRegexAlfaNum(getDataEspecifyRequest.getCodigoServicio())){
            codeStatus = "5293";
        }else{
            codeStatus = "0000";
        }
        return codeStatus;
    }

    
}
