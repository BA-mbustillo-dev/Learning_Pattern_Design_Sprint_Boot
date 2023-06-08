/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package banco.me.solicitud.repository;

import banco.me.solicitud.domain.Tra_Solicitud;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author mbustillo
 */
public interface TraSolicitudRepository extends JpaRepository<Tra_Solicitud, Integer>{
    
}
