package banco.me.solicitud.repository;

import banco.me.solicitud.domain.CatTipoArchivo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CatTipoArchivoRepository extends JpaRepository<CatTipoArchivo, Long> {

    @Query(value = "SELECT Id_Tipo_Archivo, Codigo_Tipo_Archivo, Descripcion_Tipo_Archivo, Descripcion_Tipo_Archivo_En, Id_Estado_Registro FROM CAT_TIPO_ARCHIVO where Id_Motor = :idMotor and Codigo_Tipo_Archivo = :codigoTipoArchivo", nativeQuery = true)
    CatTipoArchivo findByTipoArchivo(@Param("idMotor") Integer idMotor, @Param("codigoTipoArchivo") String codigoTipoArchivo);
}
