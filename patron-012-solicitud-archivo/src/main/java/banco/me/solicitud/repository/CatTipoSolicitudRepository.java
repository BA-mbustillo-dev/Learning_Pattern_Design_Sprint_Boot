package banco.me.solicitud.repository;

import banco.me.solicitud.domain.CatTipoArchivo;
import banco.me.solicitud.domain.CatTipoSolicitud;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CatTipoSolicitudRepository extends JpaRepository<CatTipoSolicitud, Integer> {

    @Query(value = "SELECT Id_Tipo_Solicitud, Codigo_Tipo_Solicitud, Id_Estado_Registro FROM CAT_TIPO_SOLICITUD WHERE Id_Motor = :idMotor AND Id_Institucion = :idInstitucion AND Codigo_Tipo_Solicitud = :codigoTipoSolicitud", nativeQuery = true)
    CatTipoSolicitud findByTipoSolicitu(@Param("idMotor") Integer idMotor, @Param("idInstitucion") Integer idInstitucion, @Param("codigoTipoSolicitud") String codigoTipoSolicitud);
}
