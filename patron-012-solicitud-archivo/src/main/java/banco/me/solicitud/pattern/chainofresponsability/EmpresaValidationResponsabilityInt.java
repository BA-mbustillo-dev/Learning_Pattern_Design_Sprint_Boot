
package banco.me.solicitud.pattern.chainofresponsability;

import banco.me.solicitud.domain.fnEmpresaValidacionSolicitud;
import banco.me.solicitud.dto.StructureGeneralClientData;

/**
 *
 * @author mbustillo
 */
public interface EmpresaValidationResponsabilityInt {
    
    public String ClientDataValidation(StructureGeneralClientData clientData);
    
}
