
package banco.me.solicitud.pattern.chainofresponsability.ValidationState.ClientDataChecking;

import banco.me.solicitud.domain.fnEmpresaValidacionSolicitud;
import banco.me.solicitud.dto.StructureGeneralClientData;
import banco.me.solicitud.pattern.chainofresponsability.EmpresaValidationResponsabilityInt;

/**
 *
 * @author mbustillo
 */
public class RelacionEmpresaServicioValidator implements EmpresaValidationResponsabilityInt{
    
    private EmpresaValidationResponsabilityInt nextClientHandler;

    public RelacionEmpresaServicioValidator(EmpresaValidationResponsabilityInt nextClientHandler) {
        this.nextClientHandler = nextClientHandler;
    }

    @Override
    public String ClientDataValidation(StructureGeneralClientData clientData) {
        String codeStatus = "";
        
        if(clientData.getFnEmpresaValidacion().getIdRelacionEmpresaServicio() == null){
            codeStatus = "5165";
        }else if(!clientData.getFnEmpresaValidacion().getIdEstadoRegistroRelacionServicio().equals("ACT")){
            codeStatus = "5249";
        }else{
            codeStatus = nextClientHandler.ClientDataValidation(clientData);
        }
        return codeStatus;
    }
}
