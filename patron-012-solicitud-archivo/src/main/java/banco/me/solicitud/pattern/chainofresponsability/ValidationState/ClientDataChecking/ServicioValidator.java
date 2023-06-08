
package banco.me.solicitud.pattern.chainofresponsability.ValidationState.ClientDataChecking;

import banco.me.solicitud.domain.fnEmpresaValidacionSolicitud;
import banco.me.solicitud.dto.StructureGeneralClientData;
import banco.me.solicitud.pattern.chainofresponsability.EmpresaValidationResponsabilityInt;

/**
 *
 * @author mbustillo
 */
public class ServicioValidator implements EmpresaValidationResponsabilityInt{
    
    private EmpresaValidationResponsabilityInt nextClientHandler;

    public ServicioValidator(EmpresaValidationResponsabilityInt nextClientHandler) {
        this.nextClientHandler = nextClientHandler;
    }

    @Override
    public String ClientDataValidation(StructureGeneralClientData clientData) {
        
        String codeStatus = "";
        
        if(clientData.getFnEmpresaValidacion().getIdServicio() == null){
            codeStatus = "5246";
        }else if(!clientData.getFnEmpresaValidacion().getIdEstadoRegistroServicio().equals("ACT")){
            codeStatus = "5247";
        }else{
            codeStatus = nextClientHandler.ClientDataValidation(clientData);
        }
        return codeStatus;
    }
    
}
