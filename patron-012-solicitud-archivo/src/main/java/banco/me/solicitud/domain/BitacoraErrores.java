
package banco.me.solicitud.domain;

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
public class BitacoraErrores {
    
    private String idInstitucion;
    private String idMotor;
    private String idCanal;
    private String idUsuario;
    private String tablaPrograma;
    private String ubicacion;
    private String observacion;
    private String idEstadoRegistro;
}
