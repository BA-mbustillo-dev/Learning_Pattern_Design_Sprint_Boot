
package banco.me.solicitud.pattern.chainofresponsability.ValidationState.ClientDataChecking;

import banco.me.solicitud.domain.fnEmpresaValidacionSolicitud;
import banco.me.solicitud.dto.StructureGeneralClientData;
import banco.me.solicitud.pattern.chainofresponsability.EmpresaValidationResponsabilityInt;

/**
 *
 * @author mbustillo
 */
public class RelacitionEmpresaCanalValidator implements EmpresaValidationResponsabilityInt{
    
    private EmpresaValidationResponsabilityInt nextClientHandler;

    public RelacitionEmpresaCanalValidator(EmpresaValidationResponsabilityInt nextClientHandler) {
        this.nextClientHandler = nextClientHandler;
    }

    @Override
    public String ClientDataValidation(StructureGeneralClientData clientData) {
        
        String codeStatus = "";
        if(clientData.getFnEmpresaValidacion().getIdRelacionEmpresaCanal() == null){
            codeStatus = "5186";
        }else if(!clientData.getFnEmpresaValidacion().getIdRelacionEmpresaCanal().equals("ACT")){
            codeStatus = "5187";
        }else{
            codeStatus = nextClientHandler.ClientDataValidation(clientData);
        }
        return codeStatus;
    }
    
}
