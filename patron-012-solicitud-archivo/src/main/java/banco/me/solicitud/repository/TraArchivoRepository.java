/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package banco.me.solicitud.repository;

import banco.me.solicitud.domain.TraArchivo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author mbustillo
 */
public interface TraArchivoRepository extends JpaRepository<TraArchivo, Integer>{
    
    @Query(value = "SELECT TOP(1) * FROM TRA_ARCHIVO WHERE CAST(Fecha_Insercion AS date)= CAST(getdate() AS date) AND Nombre_Archivo_Original = :nombreArchivoOriginal", nativeQuery = true)
    TraArchivo getNombreArchivo(@Param("nombreArchivoOriginal") String nombreArchivoOriginal);
}
