
package banco.me.solicitud.dto;

import banco.me.solicitud.domain.CatTipoArchivo;
import banco.me.solicitud.domain.CatTipoSolicitud;
import banco.me.solicitud.domain.fnEmpresaValidacionSolicitud;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author mbustillo
 */

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class StructureGeneralClientData {
    
    fnEmpresaValidacionSolicitud fnEmpresaValidacion;
    CatTipoArchivo catTipoArchivo;
    CatTipoSolicitud catTipoSolicitud;
}
