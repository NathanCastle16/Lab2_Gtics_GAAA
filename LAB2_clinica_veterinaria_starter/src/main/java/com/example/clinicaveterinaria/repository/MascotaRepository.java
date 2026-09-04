package com.example.clinicaveterinaria.repository;

import com.example.clinicaveterinaria.entity.Mascota;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MascotaRepository extends JpaRepository<Mascota, Long> {

    List<Mascota> findByNombreContainingIgnoreCase(String nombre);

    List<Mascota> findByEspecieContainingIgnoreCase(String especie);

    List<Mascota> findByEstado(Boolean estado);

    // TODO P5: agregar aquí la consulta personalizada de actualización solicitada por el laboratorio.
    // TODO P6: agregar aquí consultas personalizadas para MAX, MIN, AVG y COUNT.
}
