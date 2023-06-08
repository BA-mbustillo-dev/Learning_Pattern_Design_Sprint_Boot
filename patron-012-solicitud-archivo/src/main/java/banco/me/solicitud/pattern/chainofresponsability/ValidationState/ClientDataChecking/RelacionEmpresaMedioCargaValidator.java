
package banco.me.solicitud.pattern.chainofresponsability.ValidationState.ClientDataChecking;

import banco.me.solicitud.domain.fnEmpresaValidacionSolicitud;
import banco.me.solicitud.dto.StructureGeneralClientData;
import banco.me.solicitud.pattern.chainofresponsability.EmpresaValidationResponsabilityInt;

/**
 *
 * @author mbustillo
 */
public class RelacionEmpresaMedioCargaValidator implements EmpresaValidationResponsabilityInt{
    
    private EmpresaValidationResponsabilityInt nextClientHandler;

    public RelacionEmpresaMedioCargaValidator(EmpresaValidationResponsabilityInt nextClientHandler) {
        this.nextClientHandler = nextClientHandler;
    }

    @Override
    public String ClientDataValidation(StructureGeneralClientData clientData) {
        String codeState = "";
        
        if(clientData.getFnEmpresaValidacion().getIdRelacionEmpresaMedioCarga() == null){
            codeState = "5448";
        }else if(!clientData.getFnEmpresaValidacion().getIdEstadoRegistroMedioCarga().equals("ACT")){
            codeState = "5189";
        }else{
            codeState = nextClientHandler.ClientDataValidation(clientData);
        }
        return codeState;
    }
    
    
}
