package banco.me.solicitud.domain;

import java.io.Serializable;
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
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "fn_FormatoArchivoRelacionCliente")
public class fnFormatoArchivoRelacionCliente implements Serializable {

    @Id
    @Column(name = "Id_Formato_Archivo")
    private Integer idFormatoArchivo;
    
    @Column(name = "Id_Institucion")
    private Integer idInstitucion;
    
    @Column(name = "Id_Motor")
    private Integer idMotor;
    
    @Column(name = "Id_Tipo_Archivo")
    private Integer idTipoArchivo;
    
    @Column(name = "Codigo_Formato_Archivo")
    private String codigoFormatoArchivo;
    
    @Column(name = "Descripcion_Formato_Archivo")
    private String descripcionFormatoArchivo;
    
    @Column(name = "Descripcion_Formato_Archivo_En")
    private String descripcionFormatoArchivoEn;
    
    @Column(name = "Extension")
    private String extension;
    
    @Column(name = "Cantidad_Caracteres")
    private Integer cantidadCaracteres;
    
    @Column(name = "Cantidad_Columnas")
    private Integer cantidadColumnas;
    
    @Column(name = "Id_Estado_Formato_Archivo")
    private String idEstadoFormatoArchivo;
    
    @Column(name = "Id_Relacion_Cliente_Archivo_Formato")
    private Integer idRelacionClienteArchivoFormato;
    
    @Column(name = "Id_Estado_Relacion_Formato_Cliente")
    private String idEstadoRelacionFormatoCliente;
    
    @Column(name = "Descripcion_Tipo_Archivo")
    private String descripcionTipoArchivo;
    
    @Column(name = "Descripcion_Tipo_Archivo_En")
    private String descripcionTipoArchivoEn;
}
