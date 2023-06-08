package banco.me.solicitud.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "CAT_TIPO_SOLICITUD")
public class CatTipoSolicitud implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id_Tipo_Solicitud")
    private Long idTipoSolicitud;

    @Column(name = "Codigo_Tipo_Solicitud")
    private String codigoTipoSolicitud;

    @Column(name = "Id_Estado_Registro")
    private String idEstadoRegistro;

}
