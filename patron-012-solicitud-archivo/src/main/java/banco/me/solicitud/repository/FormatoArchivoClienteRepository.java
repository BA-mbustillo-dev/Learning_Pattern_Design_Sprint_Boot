package banco.me.solicitud.repository;

import banco.me.solicitud.domain.fnFormatoArchivoRelacionCliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author mbustillo
 */
public interface FormatoArchivoClienteRepository extends JpaRepository<fnFormatoArchivoRelacionCliente, Integer>{
    
    @Query(value = "SELECT * FROM fn_FormatoArchivoRelacionCliente(:idCliente, :idEmpresa, :idTipoArchivo) WHERE Id_Tipo_Archivo = :idTipoArchivo AND Cantidad_Columnas = :cantidadColumnas"
            + " AND Id_Institucion = :idInstitucion AND Id_Motor = :idMotor AND Extension = :extension", nativeQuery = true)
    public fnFormatoArchivoRelacionCliente findFormatoArchivoXlsxCliente(@Param("idCliente") Integer idCliente, 
            @Param("idEmpresa") Integer idEmpresa, 
            @Param("idTipoArchivo") Integer idTipoArchivo,
            @Param("cantidadColumnas") long cantidadColumnas,
            @Param("idInstitucion")Integer idInstitucion,
            @Param("idMotor") Integer idMotor,
            @Param("extension") String extension);
    
    @Query(value = "SELECT * FROM fn_FormatoArchivoRelacionCliente(:idCliente, :idEmpresa, :idTipoArchivo) WHERE Id_Tipo_Archivo = :idTipoArchivo AND Cantidad_Caracteres = :cantidadCaracteres"
            + " AND Id_Institucion = :idInstitucion AND Id_Motor = :idMotor AND Extension = :extension AND Codigo_Formato_Archivo != 'ANCP_TXT'", nativeQuery = true)
    public fnFormatoArchivoRelacionCliente findFormatoArchivoTxtCliente(@Param("idCliente") Integer idCliente, 
            @Param("idEmpresa") Integer idEmpresa, 
            @Param("idTipoArchivo") Integer idTipoArchivo,
            @Param("cantidadCaracteres") long cantidadCaracteres,
            @Param("idInstitucion")Integer idInstitucion,
            @Param("idMotor") Integer idMotor,
            @Param("extension") String extension);
    
    @Query(value = "SELECT * FROM fn_FormatoArchivoRelacionCliente(:idCliente, :idEmpresa, :idTipoArchivo) WHERE Id_Tipo_Archivo = :idTipoArchivo AND Cantidad_Caracteres = :cantidadCaracteres"
            + " AND Id_Institucion = :idInstitucion AND Id_Motor = :idMotor AND Extension = :extension AND Codigo_Formato_Archivo = 'ANCP_TXT'", nativeQuery = true)
    public fnFormatoArchivoRelacionCliente findFormatoArchivoTxtSeguroSocial(@Param("idCliente") Integer idCliente, 
            @Param("idEmpresa") Integer idEmpresa, 
            @Param("idTipoArchivo") Integer idTipoArchivo,
            @Param("cantidadCaracteres") long cantidadCaracteres,
            @Param("idInstitucion")Integer idInstitucion,
            @Param("idMotor") Integer idMotor,
            @Param("extension") String extension);
    
}
