/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package banco.me.solicitud.service;

import banco.me.generalValidation.domain.GeneralRequest;
import banco.me.generalValidation.domain.GeneralResponse;
import banco.me.solicitud.dto.BitacoraDto;
import me.banco.ws.v1.services.ws_tra_solicitud_archivo.GeneralRequestType;

/**
 *
 * @author mbustillo
 */
public interface UtilitySolicitudInter {
    
    public GeneralRequest createPartDataGeneralRequest(GeneralRequestType dataGeneralRequestEndPoint);
    
    public BitacoraDto createDataBitacora(GeneralResponse dataResponseValidationGeneral);
    
    public Boolean comparingStringToRegexAlfaNum(String cadenaComparing);
    
    public Boolean comparingNumToNumeric(String stringComparing);
    
    public char getActionSolicitud(String codeScreenRequest);
}
