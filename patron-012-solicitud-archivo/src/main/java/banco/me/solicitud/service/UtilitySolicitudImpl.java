/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package banco.me.solicitud.service;

import banco.me.generalValidation.domain.GeneralRequest;
import banco.me.generalValidation.domain.GeneralResponse;
import banco.me.solicitud.dto.BitacoraDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.banco.ws.v1.services.ws_tra_solicitud_archivo.GeneralRequestType;
import org.springframework.stereotype.Service;

/**
 *
 * @author mbustillo
 */
@Slf4j
@RequiredArgsConstructor
@Service
public class UtilitySolicitudImpl implements UtilitySolicitudInter {

    @Override
    public GeneralRequest createPartDataGeneralRequest(GeneralRequestType dataGeneralRequestEndPoint) {

        GeneralRequest addDataGeneralRequestValidationGeneral = new GeneralRequest();
        addDataGeneralRequestValidationGeneral.transaccionId = dataGeneralRequestEndPoint.getTransaccionId() == null || dataGeneralRequestEndPoint.getTransaccionId().isEmpty() ? "0" : dataGeneralRequestEndPoint.getTransaccionId();
        addDataGeneralRequestValidationGeneral.aplicacionId = dataGeneralRequestEndPoint.getAplicacionId() == null || dataGeneralRequestEndPoint.getAplicacionId().isEmpty() ? "0" : dataGeneralRequestEndPoint.getAplicacionId();
        addDataGeneralRequestValidationGeneral.canalId = dataGeneralRequestEndPoint.getCanalId();
        addDataGeneralRequestValidationGeneral.paisId = dataGeneralRequestEndPoint.getPaisId();
        addDataGeneralRequestValidationGeneral.regionId = dataGeneralRequestEndPoint.getRegionId();
        addDataGeneralRequestValidationGeneral.institucionId = dataGeneralRequestEndPoint.getInstitucionId();
        addDataGeneralRequestValidationGeneral.Idioma = dataGeneralRequestEndPoint.getIdioma() == null || dataGeneralRequestEndPoint.getIdioma().equals("") ? "ES" : dataGeneralRequestEndPoint.getIdioma();
        addDataGeneralRequestValidationGeneral.version = dataGeneralRequestEndPoint.getVersion();
        addDataGeneralRequestValidationGeneral.llaveSesion = dataGeneralRequestEndPoint.getLlaveSesion();
        addDataGeneralRequestValidationGeneral.clienteCoreId = dataGeneralRequestEndPoint.getClienteCoreId();
        addDataGeneralRequestValidationGeneral.usuarioId = dataGeneralRequestEndPoint.getUsuarioId();
        addDataGeneralRequestValidationGeneral.token = dataGeneralRequestEndPoint.getToken();
        addDataGeneralRequestValidationGeneral.dispositivoId = dataGeneralRequestEndPoint.getDispositivoId();
        addDataGeneralRequestValidationGeneral.motorId = dataGeneralRequestEndPoint.getMotorId();
        addDataGeneralRequestValidationGeneral.ambiente = dataGeneralRequestEndPoint.getAmbiente();
        addDataGeneralRequestValidationGeneral.paso = dataGeneralRequestEndPoint.getPaso();
        addDataGeneralRequestValidationGeneral.numeroTransaccion = dataGeneralRequestEndPoint.getNumeroTransaccion();
        addDataGeneralRequestValidationGeneral.mandante = dataGeneralRequestEndPoint.getMandate();
        addDataGeneralRequestValidationGeneral.tipoTransaccion = "Tra_Solicitud";

        return addDataGeneralRequestValidationGeneral;

    }

    @Override
    public BitacoraDto createDataBitacora(GeneralResponse dataResponseValidationGeneral) {

        BitacoraDto addDataBitacora = new BitacoraDto();
        addDataBitacora.setIdPais(dataResponseValidationGeneral.getCodigoPais());
        addDataBitacora.setIdInstitucion(String.valueOf(dataResponseValidationGeneral.getIdInstitucion()));
        addDataBitacora.setIdMotor(dataResponseValidationGeneral.getCodigoMotor());
        addDataBitacora.setTransaccionId(dataResponseValidationGeneral.getTransaccionId());
        addDataBitacora.setCodigoTransaccion("WS_TRA_Solicitud_Archivo");
        addDataBitacora.setTipoDetalle("Request/XML");
        addDataBitacora.setTransaccionXML("");
        addDataBitacora.setClienteCoreId(dataResponseValidationGeneral.getCodigoBusinessPartner());
        addDataBitacora.setUsuarioId(dataResponseValidationGeneral.codigoBusinessPartner);
        addDataBitacora.setUsuarioInsercion("");
        addDataBitacora.setIdEstadoRegistro("ACT");

        return addDataBitacora;
    }

    @Override
    public Boolean comparingStringToRegexAlfaNum(String stringComparing) {
        String alfanumericRegex = "^[0-9A-Za-z_&#´$ \\/?:().,+\\-\\s]{1,500}$";
        return stringComparing.matches(alfanumericRegex);
    }

    @Override
    public Boolean comparingNumToNumeric(String cadenaComparing) {
        if (cadenaComparing == null) {
            return false;
        }
        try {
            double d = Double.parseDouble(cadenaComparing);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

    @Override
    public char getActionSolicitud(String codeScreenRequest) {
        char actionSolicitud = codeScreenRequest.charAt(0);
        return actionSolicitud;
    }
    
 

}
