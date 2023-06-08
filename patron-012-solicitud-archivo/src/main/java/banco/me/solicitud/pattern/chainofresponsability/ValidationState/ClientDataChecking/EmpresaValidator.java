/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package banco.me.solicitud.pattern.chainofresponsability.ValidationState.ClientDataChecking;

import banco.me.solicitud.domain.fnEmpresaValidacionSolicitud;
import banco.me.solicitud.dto.StructureGeneralClientData;
import banco.me.solicitud.pattern.chainofresponsability.handlerResponsabilityInter;
import me.banco.ws.v1.services.ws_tra_solicitud_archivo.SolicitudArchivoRequestType;
import banco.me.solicitud.pattern.chainofresponsability.EmpresaValidationResponsabilityInt;

/**
 *
 * @author mbustillo
 */
public class EmpresaValidator implements EmpresaValidationResponsabilityInt {

    private EmpresaValidationResponsabilityInt nextClientHandler;

    public EmpresaValidator(EmpresaValidationResponsabilityInt nextClientHandler) {
        this.nextClientHandler = nextClientHandler;
    }

    @Override
    public String ClientDataValidation(StructureGeneralClientData clientData) {
        String codeStatus = "";
        if (clientData.getFnEmpresaValidacion().getCodigoEmpresa() == null) {
            codeStatus = "6002";
        } else if (!clientData.getFnEmpresaValidacion().getIdEstadoRegistroEmpresa().equals("ACT")) {
            codeStatus = "5190";
        } else {
            codeStatus = nextClientHandler.ClientDataValidation(clientData);
        }
        return codeStatus;
    }
}
