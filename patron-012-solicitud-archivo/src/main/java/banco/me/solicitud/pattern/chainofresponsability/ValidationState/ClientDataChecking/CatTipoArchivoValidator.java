
package banco.me.solicitud.pattern.chainofresponsability.ValidationState.ClientDataChecking;

import banco.me.solicitud.dto.StructureGeneralClientData;
import banco.me.solicitud.pattern.chainofresponsability.EmpresaValidationResponsabilityInt;

/**
 *
 * @author mbustillo
 */
public class CatTipoArchivoValidator implements EmpresaValidationResponsabilityInt{

    @Override
    public String ClientDataValidation(StructureGeneralClientData clientData) {
        String codeStatus = "";
        if(clientData.getCatTipoArchivo().getIdTipoArchivo() == null){
            codeStatus = "6004";
        }else if(clientData.getCatTipoArchivo().getIdEstadoRegistro().equals("ACT")){
            codeStatus = "5464";
        }else{
            codeStatus = "0000";
        }
        return codeStatus;
    }
    
}
