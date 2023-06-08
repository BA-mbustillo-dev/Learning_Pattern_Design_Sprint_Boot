
package banco.me.solicitud.pattern.chainofresponsability.ValidationState.ClientDataChecking;

import banco.me.solicitud.domain.fnEmpresaValidacionSolicitud;
import banco.me.solicitud.dto.StructureGeneralClientData;
import banco.me.solicitud.pattern.chainofresponsability.EmpresaValidationResponsabilityInt;

/**
 *
 * @author mbustillo
 */
public class MedioCargaValidator implements EmpresaValidationResponsabilityInt{
    
    private EmpresaValidationResponsabilityInt nextClienthandler;

    public MedioCargaValidator(EmpresaValidationResponsabilityInt nextClienthandler) {
        this.nextClienthandler = nextClienthandler;
    }

    @Override
    public String ClientDataValidation(StructureGeneralClientData clientData) {
        String codeString = "";
        if(clientData.getFnEmpresaValidacion().getIdMedioCarga() == null){
            codeString = "1116";
        }else if(!clientData.getFnEmpresaValidacion().getIdEstadoRegistroMedioCarga().equals("ACT")){
            codeString = "5188";
        }else{
            codeString = nextClienthandler.ClientDataValidation(clientData);
        }
        return codeString;
    }
    
}
