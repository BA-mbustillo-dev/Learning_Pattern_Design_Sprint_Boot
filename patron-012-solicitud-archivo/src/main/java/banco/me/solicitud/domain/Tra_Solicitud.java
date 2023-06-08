package banco.me.solicitud.domain;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name = "TRA_SOLICITUD")
@Entity
public class Tra_Solicitud implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id_Solicitud")
    private Integer idSolicitud;

    @Column(name = "Id_Institucion")
    private Integer idInstitucion;

    @Column(name = "Id_Motor")
    private Integer idMotor;

    @Column(name = "Id_Cliente")
    private Integer idCliente;

    @Column(name = "Id_Archivo")
    private Integer idArchivo;

    @Column(name = "Accion")
    private String accion;

    @Column(name = "Id_Estado_Proceso")
    private Integer idEstadoProceso;

    @Column(name = "Usuario_Solicita")
    private String usuarioSolicita;

    @Column(name = "Fecha_Solicitud")
    private LocalDateTime fechaSolicitud;

    @Column(name = "Usuario_Rechazo")
    private String usuarioRechazo;

    @Column(name = "Fecha_Rechazo")
    private LocalDateTime fechaRechazo;

    @Column(name = "Id_Empresa")
    private Integer idEmpresa;

    @Column(name = "Nombre_Archivo")
    private String nombreArchivo;

    @Column(name = "Comentario")
    private String comentario;

    @Column(name = "Numero_Cuenta")
    private String numeroCuenta;

    @Column(name = "Monto")
    private Double monto;

    @Column(name = "Id_Canal")
    private Integer idCanal;

    @Column(name = "Id_Tipo_Archivo")
    private Integer idTipoArchivo;

    @Column(name = "Codigo_Moneda")
    private String codigoMoneda;

    @Column(name = "Estado_Archivo")
    private String estadoArchivo;

    @Column(name = "Id_Tipo_Solicitud")
    private Integer idTipoSolicitud;

    @Column(name = "Id_Servicio")
    private Integer idServicio;

    @Column(name = "Pantalla")
    private String pantalla;

    @Column(name = "Codigo_Servicio")
    private String codigoServicio;
}
