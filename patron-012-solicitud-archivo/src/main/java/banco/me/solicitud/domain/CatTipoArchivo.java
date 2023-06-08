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
@Table(name = "CAT_TIPO_ARCHIVO")
public class CatTipoArchivo implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id_Tipo_Archivo")
    private Long idTipoArchivo;
    
    @Column(name = "Codigo_Tipo_Archivo")
    private String codigoTipoArchivo;
    
    @Column(name = "Descripcion_Tipo_Archivo")
    private String descripcionTipoArchivo;
    
    @Column(name = "Descripcion_Tipo_Archivo_En")
    private String descripcionTipoArchivoEn;

    @Column(name = "Id_Estado_Registro")
    private String idEstadoRegistro;

}
