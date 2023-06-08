package banco.me.solicitud.repository;

import banco.me.solicitud.domain.fnEmpresaValidacionSolicitud;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface EmpresaValidacionRepository extends JpaRepository<fnEmpresaValidacionSolicitud, Integer> {

    @Query(value = "SELECT TOP(1) * FROM fn_EmpresaMedioCargaServicioATLASET(:idCanal, :codigoMedioCarga, :codigoServicio, :codigoEmpresa, :idMotor, :codigoBanco) "
            + "WHERE Id_Institucion = :idInstitucion and Id_Cliente = :idCliente and Codigo_Empresa = :codigoEmpresa", nativeQuery = true)
    fnEmpresaValidacionSolicitud findEmpresaValidacion(@Param("idCanal") Integer idCanal, 
            @Param("codigoMedioCarga") String codigoMedioCarga,
            @Param("codigoServicio") String codigoServicio, 
            @Param("codigoEmpresa") String codigoEmpresa,
            @Param("idMotor") Integer idMotor, 
            @Param("codigoBanco") String codigoBanco,
            @Param("idInstitucion") Integer idInstitucion,
            @Param("idCliente") Integer idCliente);
            
}
