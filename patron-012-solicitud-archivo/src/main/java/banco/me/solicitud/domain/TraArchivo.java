
package banco.me.solicitud.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author mbustillo
 */

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Data
@Table(name = "TRA_ARCHIVO")
public class TraArchivo {
    
    @Id
    @Column(name = "Id_Planilla")
    private Integer idPlanilla;
    
    @Column(name = "Nombre_Archivo_Original")
    private String nombreArchivoOriginal;
    
}
