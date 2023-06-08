
package banco.me.solicitud.pattern.chainofresponsability.ValidationState.ClientDataChecking;

import banco.me.solicitud.domain.fnEmpresaValidacionSolicitud;
import banco.me.solicitud.dto.StructureGeneralClientData;
import banco.me.solicitud.pattern.chainofresponsability.EmpresaValidationResponsabilityInt;

/**
 *
 * @author mbustillo
 */

public class CatTipoSolicitudValidator implements EmpresaValidationResponsabilityInt{
    private EmpresaValidationResponsabilityInt nextClientHandler;

    public CatTipoSolicitudValidator(EmpresaValidationResponsabilityInt nextClientHandler) {
        this.nextClientHandler = nextClientHandler;
    }

    @Override
    public String ClientDataValidation(StructureGeneralClientData clientData) {
        
        String codeStatus = "";
        if(clientData.getCatTipoSolicitud().getIdTipoSolicitud() == null){
            codeStatus = "8015";
        }else if(!clientData.getCatTipoSolicitud().getIdEstadoRegistro().equals("ACT")){
            codeStatus = "5463";
        }else{
            codeStatus = nextClientHandler.ClientDataValidation(clientData);
        }
        return codeStatus;
    }
    
    
    
}
