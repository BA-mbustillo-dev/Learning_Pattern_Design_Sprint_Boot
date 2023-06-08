/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
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
public class ArchivoDetalleBeneficiario {
    
    private String linea;
    private String accion;
    private String codigoBeneficiario;
    private String codigoEmpresa;
    private String codigoServicio;
    private String numeroCuenta;
    private String identificacionBeneficiario;
    private String nombreBeneficiario;
    private String codigoBanco;
    private String codigoSucursal;
    private String cuentaExterna;
    private String correoBeneficiario;
    private String telefonoBeneficiario;
    private String tipoNotificacion;
    private String terminadorRegistro;
    private String segundoNombreBeneficiario;
    private String segundoIDBeneficiario;
    
}
