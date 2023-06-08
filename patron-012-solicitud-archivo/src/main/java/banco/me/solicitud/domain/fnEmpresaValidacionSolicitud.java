package banco.me.solicitud.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "fn_EmpresaMedioCargaServicioATLASET")
public class fnEmpresaValidacionSolicitud implements Serializable {

    @Id
    @Column(name = "Id_Empresa")
    private Integer idEmpresa;

    @Column(name = "Codigo_Empresa")
    private String codigoEmpresa;

    @Column(name = "Nombre_Empresa")
    private String nombreEmpresa;

    @Column(name = "Id_Estado_Registro_Empresa")
    private String idEstadoRegistroEmpresa;

    @Column(name = "Id_Relacion_Empresa_Canal")
    private Integer idRelacionEmpresaCanal;

    @Column(name = "Id_Estado_Registro_Relacion_Canal")
    private String idEstadoRegistroRelacionCanal;

    @Column(name = "Id_Medio_Carga")
    private Integer idMedioCarga;

    @Column(name = "Id_Estado_Registro_Medio_Carga")
    private String idEstadoRegistroMedioCarga;

    @Column(name = "Id_Relacion_Empresa_Medio_Carga")
    private Integer idRelacionEmpresaMedioCarga;

    @Column(name = "Id_Estado_Registro_Relacion")
    private String idEstadoRegistroRelacion;

    @Column(name = "Id_Servicio")
    private Integer idServicio;

    @Column(name = "Codigo_Moneda")
    private String codigoMoneda;

    @Column(name = "Id_Estado_Registro_Servicio")
    private String idEstadoRegistroServicio;

    @Column(name = "Id_Relacion_Empresa_Servicio")
    private Integer idRelacionEmpresaServicio;

    @Column(name = "Id_Estado_Registro_Relacion_Servicio")
    private String idEstadoRegistroRelacionServicio;

    @Column(name = "Id_Institucion")
    private Integer idInstitucion;

    @Column(name = "Id_Cliente")
    private Integer idCliente;
    
    @Column(name = "Id_Moneda")
    private Integer idMoneda;
    
    @Column(name = "Codigo_Banco")
    private String codigoBanco;
    
    @Column(name = "Codigo_Banco_SAP")
    private String codigoBancoSAP;
    
    @Column(name = "Cod_Pais2")
    private String codigoPais2;
    
    @Column(name = "Id_Banco_Pais")
    private Integer idBancoPais;
}
