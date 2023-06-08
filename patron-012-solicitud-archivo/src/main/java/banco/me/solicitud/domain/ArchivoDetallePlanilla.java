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
public class ArchivoDetallePlanilla {
    
    private String linea;
    private String tipoRegistro;
    private String codigoBeneficiario;
    private String codigoBanco;
    private String codigoEmpresaArchivo;
    private String tipoServicio;
    private String pagoNeto;
    private String totalRegistro;
    private String descripcionPago;
    private String referenciaPagador;
    private String referenciaBeneficiario;
    private String codigoAutorizacion;
    private String procesoRegistro;
    private String codigoRespuesta;
    private String numeroOrden;
    
}
