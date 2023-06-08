
package banco.me.solicitud.domain;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ArchivoEncabezado {
    
    private String codigoBusinessPartner;
    private String codigoTipoArchivo;
    private String extensionArchivo;
    private String nombreArchivoB2B;
    private String nombreArchivoOriginal;
    private String nombreArchivoFinal;
    private String eventId;
    private Integer idSolicitud;
    private Integer idInstitucion;
    private Integer idMotor;
    private Integer idCliente;
    private Integer idEmpresa;
    private Integer idCanal;
    private String numeroCuenta;
    private Double montoSolicitud;
    private Integer idServicio;
    private String codigoMoneda;
    private String codigoServicio;
    private String codigoCanal;
    private Integer idMoneda;
    private String codigoBancoSAP;
    private String codigoPais;
    private Integer idTipoArchivo;
    private Integer idFormatoArchivo;
    private String codigoFormatoArchivo;
    private Integer indTipoProcesamiento;
    private String indPagoInmediato;
    private String codigoEmpresa;
    private String nombreEmpresa;
    private String descripcionTipoArchivo;
    private String codigoIdioma;
    private Integer idPais;
    private Integer idBancoPais;
    private String codigoBanco;
    private String descripcionFormatoExtension;
    private String usuarioInsercion;
    List<Object> detalle;
}
