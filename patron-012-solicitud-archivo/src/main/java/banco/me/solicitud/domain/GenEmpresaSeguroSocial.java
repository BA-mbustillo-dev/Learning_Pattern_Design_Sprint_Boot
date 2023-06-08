
package banco.me.solicitud.domain;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor 
@Data
@Entity 
@Table(name = "GEN_PARAMETRO")
public class GenEmpresaSeguroSocial implements Serializable{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id_Parametro")
    private Integer idParametro;
    
    @Column(name = "Id_Institucion")
    private Integer idInstitucion;
    
    @Column(name = "Parametro")
    private String parametro;
    
    @Column(name = "Valor_Parametro_String")
    private String codigoEmpresa;
    
}
