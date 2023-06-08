package banco.me.solicitud.dto;

import java.util.Date;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class BitacoraDto {

    String idPais;
    String idInstitucion;
    String idMotor;
    String transaccionId;
    String codigoTransaccion;
    String tipoDetalle;
    Object transaccionXML;
    String clienteCoreId;
    String usuarioId;
    String usuarioInsercion;
    Date fechaInsercion;
    String idEstadoRegistro;

}
