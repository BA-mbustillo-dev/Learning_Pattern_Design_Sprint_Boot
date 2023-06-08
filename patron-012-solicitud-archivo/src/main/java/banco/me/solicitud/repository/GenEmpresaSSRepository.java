
package banco.me.solicitud.repository;

import banco.me.solicitud.domain.GenEmpresaSeguroSocial;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface GenEmpresaSSRepository extends JpaRepository<GenEmpresaSeguroSocial, Integer>{
    
    @Query(value = "SELECT * FROM GEN_PARAMETRO WHERE Parametro = :codigoParametro AND Id_Institucion = :idInstitucion", nativeQuery = true)
    GenEmpresaSeguroSocial getCodigoEmpresaSS(@Param("codigoParametro") String codigoParametro, @Param("idInstitucion") Integer idInstitucion);
    
    
}
